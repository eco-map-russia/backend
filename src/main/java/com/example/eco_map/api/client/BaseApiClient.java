package com.example.eco_map.api.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class BaseApiClient {

    protected final WebClient webClient;
    protected final String baseUrl;

    protected <T> Mono<T> get(String formattedPath, Class<T> responseType) {
        String fullUrl = baseUrl + formattedPath;
        return webClient.get()
                .uri(fullUrl)
                .retrieve()
                .bodyToMono(responseType);
    }
}