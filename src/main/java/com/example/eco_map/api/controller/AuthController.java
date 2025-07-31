package com.example.eco_map.api.controller;


import com.example.eco_map.api.AuthApi;
import com.example.eco_map.usecases.AuthenticationService;
import com.example.eco_map.usecases.dto.AuthRequestDto;
import com.example.eco_map.usecases.dto.AuthResponseDto;
import com.example.eco_map.usecases.dto.RegistrationRequestDto;
import com.example.eco_map.usecases.dto.RegistrationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController implements AuthApi {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Override
    public Mono<ResponseEntity<AuthResponseDto>> loginUser(
            @RequestBody Mono<AuthRequestDto> authRequestDtoMono,
            ServerWebExchange exchange) {

        return authRequestDtoMono
                .flatMap(authenticationService::authenticate)
                .map(authenticationService::generateToken)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/register")
    @Override
    public Mono<ResponseEntity<RegistrationResponseDto>> registerUser(
            @RequestBody Mono<RegistrationRequestDto> registrationRequestDto,
            ServerWebExchange exchange) {

        return registrationRequestDto
                .flatMap(authenticationService::registerUser)
                .map(ResponseEntity::ok);
    }
}

