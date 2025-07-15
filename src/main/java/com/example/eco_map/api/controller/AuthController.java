package com.example.eco_map.api.controller;

import com.example.eco_map.usecases.AuthenticationService;
import com.example.eco_map.usecases.dto.AuthRequestDto;
import com.example.eco_map.usecases.dto.AuthResponseDto;
import com.example.eco_map.usecases.dto.RegistrationRequestDto;
import com.example.eco_map.usecases.dto.RegistrationResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public Mono<ResponseEntity<RegistrationResponseDto>> register(@RequestBody @Valid RegistrationRequestDto request) {
        return authenticationService.registerUser(request)
                .flatMap(registrationResponseDto -> Mono.just(ResponseEntity.ok(registrationResponseDto)));
    }

    @PostMapping("/login")
    public Mono<AuthResponseDto> login(@RequestBody @Valid AuthRequestDto authRequest) {
        return authenticationService.authenticate(authRequest)
                .map(authenticationService::generateToken);
    }
}
