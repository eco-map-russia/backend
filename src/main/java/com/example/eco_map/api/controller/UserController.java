package com.example.eco_map.api.controller;

import com.example.eco_map.api.UsersApi;
import com.example.eco_map.security.CurrentUserService;
import com.example.eco_map.usecases.UserService;
import com.example.eco_map.usecases.dto.UserDto;
import com.example.eco_map.usecases.dto.UserUpdateRequestDto;
import com.example.eco_map.usecases.dto.UserUpdateResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/me")
public class UserController implements UsersApi {
    private final UserService userService;
    private final CurrentUserService currentUserService;

    @Override
    @GetMapping
    public Mono<ResponseEntity<UserDto>> getCurrentUserProfile(ServerWebExchange exchange) {
        return currentUserService.getCurrentUser()
                .flatMap(user -> userService.getCurrentUser(user.getId()))
                .map(ResponseEntity::ok);
    }

    @PatchMapping
    @Override
    public Mono<ResponseEntity<UserUpdateResponseDto>> updateCurrentUserProfile(
            @RequestBody @Valid Mono<UserUpdateRequestDto> userUpdateRequestDto,
            ServerWebExchange exchange) {
        return currentUserService.getCurrentUser()
                .zipWith(userUpdateRequestDto)
                .flatMap(tuple -> {
                    var user = tuple.getT1();
                    var userDto = tuple.getT2();
                    return userService.updateUser(user.getId(), userDto);
                })
                .map(ResponseEntity::ok);
    }
}
