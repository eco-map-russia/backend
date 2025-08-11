package com.example.eco_map.security;


import com.example.eco_map.persistence.model.User;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CurrentUserService {
    public Mono<CustomUserDetails> getCurrentUserDetails() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> (CustomUserDetails) ctx.getAuthentication().getPrincipal());
    }

    public Mono<User> getCurrentUser() {
        return getCurrentUserDetails()
                .map(CustomUserDetails::getUser);
    }
}

