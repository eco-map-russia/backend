package com.example.eco_map.usecases;

import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface JwtService {
    String generateToken(String subject);

    Mono<UserDetails> validateToken(String token);
}
