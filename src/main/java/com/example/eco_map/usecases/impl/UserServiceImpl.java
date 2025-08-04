package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.UserNotFoundException;
import com.example.eco_map.persistence.model.User;
import com.example.eco_map.persistence.repository.UserRepository;
import com.example.eco_map.usecases.UserService;
import com.example.eco_map.usecases.dto.UserDto;
import com.example.eco_map.usecases.dto.UserUpdateRequestDto;
import com.example.eco_map.usecases.dto.UserUpdateResponseDto;
import com.example.eco_map.usecases.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Scheduler jdbcScheduler;
    private final TransactionTemplate transactionTemplate;
    private final UserMapper userMapper;

    @Override
    public Mono<UserDto> getCurrentUser(UUID id) {
        return Mono.fromCallable(() -> {
                    User user = userRepository.findById(id)
                            .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
                    return user;
                })
                .subscribeOn(jdbcScheduler)
                .map(userMapper::userToUserDto);
    }

    @Override
    public Mono<UserUpdateResponseDto> updateUser(UUID id, UserUpdateRequestDto dto) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    User user = userRepository.findById(id)
                            .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
                    if (dto.getFirstName() != null) {
                        user.setFirstName(dto.getFirstName());
                    }
                    if (dto.getLastName() != null) {
                        user.setLastName(dto.getLastName());
                    }
                    if (dto.getPhone() != null) {
                        user.setPhone(dto.getPhone());
                    }
                    return userRepository.save(user);
                }))
                .subscribeOn(jdbcScheduler)
                .map(userMapper::userToUserUpdateResponseDto);
    }
}
