package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.CleanupEventCreateRequestDto;
import com.example.eco_map.usecases.dto.CleanupEventDetailsDto;
import com.example.eco_map.usecases.dto.CleanupEventMapDto;
import com.example.eco_map.usecases.dto.CleanupEventResponseDto;
import com.example.eco_map.usecases.dto.CleanupEventUpdateDto;
import com.example.eco_map.usecases.dto.PageCleanupEventResponseDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CleanupService {
    Flux<CleanupEventMapDto> getAllCleanupEventsForMap();

    Mono<CleanupEventDetailsDto> getDetails(UUID id);

    Mono<CleanupEventResponseDto> addCleanupEvent(CleanupEventCreateRequestDto dto);

    Mono<PageCleanupEventResponseDto> getAllCleanupEvents(Pageable pageable);

    Mono<CleanupEventResponseDto> updateCleanupEvent(UUID id, CleanupEventUpdateDto dto);

    Mono<Void> deleteCleanupEvent(UUID id);
}
