package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.CleanupEventDetailsDto;
import com.example.eco_map.usecases.dto.CleanupEventMapDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CleanupService {
    Flux<CleanupEventMapDto> getAllCleanupEventsForMap();

    Mono<CleanupEventDetailsDto> getDetails(UUID id);
}
