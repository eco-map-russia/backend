package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.AuthRequestDto;
import com.example.eco_map.usecases.dto.AuthResponseDto;
import com.example.eco_map.usecases.dto.RegistrationRequestDto;
import com.example.eco_map.usecases.dto.RegistrationResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
    Mono<RegistrationResponseDto> registerUser(RegistrationRequestDto registrationRequestDto);

    Mono<UserDetails> authenticate(AuthRequestDto authRequest);

    AuthResponseDto generateToken(UserDetails userDetails);

    Mono<UserDetails> validateToken(String token);

}
