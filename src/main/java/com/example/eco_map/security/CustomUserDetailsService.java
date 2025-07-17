package com.example.eco_map.security;

import com.example.eco_map.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {
    private final UserRepository userRepository;
    private final Scheduler jdbcScheduler;

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        return Mono.fromCallable(() -> userRepository.findByEmail(email))
                .subscribeOn(jdbcScheduler)
                .flatMap(optionalUser ->
                        Mono.justOrEmpty(optionalUser.map(CustomUserDetails::new))
                );
    }


}
