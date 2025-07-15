package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.RoleNotFoundException;
import com.example.eco_map.api.exception.UserAlreadyExistsException;
import com.example.eco_map.persistence.model.Role;
import com.example.eco_map.persistence.model.RoleType;
import com.example.eco_map.persistence.model.User;
import com.example.eco_map.persistence.model.UserRole;
import com.example.eco_map.persistence.repository.RoleRepository;
import com.example.eco_map.persistence.repository.UserRepository;
import com.example.eco_map.usecases.AuthenticationService;
import com.example.eco_map.usecases.JwtService;
import com.example.eco_map.usecases.dto.AuthRequestDto;
import com.example.eco_map.usecases.dto.AuthResponseDto;
import com.example.eco_map.usecases.dto.RegistrationRequestDto;
import com.example.eco_map.usecases.dto.RegistrationResponseDto;
import com.example.eco_map.usecases.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final Scheduler jdbcScheduler;
    private final RoleRepository roleRepository;
    private final TransactionTemplate transactionTemplate;
    private final JwtService jwtService;
    private final ReactiveAuthenticationManager authenticationManager;

    @Override
    public Mono<RegistrationResponseDto> registerUser(RegistrationRequestDto requestDto) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
            Optional<User> existing = userRepository.findByEmail(requestDto.email());
            if (existing.isPresent()) {
                throw new UserAlreadyExistsException("User already exists");
            }

            Role role = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException("Default role not found"));

            User user = authMapper.toEntity(requestDto);
            user.setPassword(passwordEncoder.encode(requestDto.password()));

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            user.setUserRoles(Set.of(userRole));

            User saved = userRepository.save(user);
            return authMapper.entityToRegistrationResponseDto(saved);
        })).subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<UserDetails> authenticate(AuthRequestDto authRequest) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password());
        return authenticationManager.authenticate(authToken)
                .filter(Authentication::isAuthenticated)
                .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid username or password")))
                .flatMap(auth -> Mono.just((UserDetails) auth.getPrincipal()));
    }

    @Override
    public AuthResponseDto generateToken(UserDetails userDetails) {
        String token = jwtService.generateToken(userDetails.getUsername());
        return new AuthResponseDto(token);
    }

    @Override
    public Mono<UserDetails> validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
