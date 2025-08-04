package com.example.eco_map.api.controller;

import com.example.eco_map.security.CustomUserDetails;
import com.example.eco_map.usecases.UserService;
import com.example.eco_map.usecases.dto.UserDto;
import com.example.eco_map.usecases.dto.UserUpdateRequestDto;
import com.example.eco_map.usecases.dto.UserUpdateResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/me")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Mono<ResponseEntity<UserDto>> getMe(@AuthenticationPrincipal CustomUserDetails user) {
        return userService.getCurrentUser(user.getUser().getId()).map(ResponseEntity::ok);
    }

    @PatchMapping
    public Mono<ResponseEntity<UserUpdateResponseDto>> updateMe(@AuthenticationPrincipal CustomUserDetails user,
                                                                @RequestBody @Valid UserUpdateRequestDto dto) {
        return userService.updateUser(user.getUser().getId(), dto).map(ResponseEntity::ok);
    }

}
