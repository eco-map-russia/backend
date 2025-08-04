package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.UserDto;
import com.example.eco_map.usecases.dto.UserUpdateRequestDto;
import com.example.eco_map.usecases.dto.UserUpdateResponseDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
    Mono<UserDto> getCurrentUser(UUID id);

    Mono<UserUpdateResponseDto> updateUser(UUID id, UserUpdateRequestDto dto);
}
