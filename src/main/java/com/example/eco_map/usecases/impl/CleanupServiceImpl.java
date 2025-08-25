package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.CleanupEventNotFoundException;
import com.example.eco_map.persistence.model.CleanupEvent;
import com.example.eco_map.persistence.repository.CleanupEventRepository;
import com.example.eco_map.usecases.CleanupService;
import com.example.eco_map.usecases.dto.CleanupEventDetailsDto;
import com.example.eco_map.usecases.dto.CleanupEventMapDto;
import com.example.eco_map.usecases.mapper.CleanupEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CleanupServiceImpl implements CleanupService {
    private final CleanupEventRepository cleanupEventRepository;
    private final Scheduler jdbcScheduler;
    private final CleanupEventMapper cleanupEventMapper;

    @Override
    public Flux<CleanupEventMapDto> getAllCleanupEventsForMap() {
        return Mono.fromCallable(cleanupEventRepository::findAllForMap)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler)
                .map(cleanupEventMapper::toDto);
    }

    @Override
    public Mono<CleanupEventDetailsDto> getDetails(UUID id) {
        return Mono.fromCallable(() -> {
                    CleanupEvent cleanupEvent = cleanupEventRepository.findById(id)
                            .orElseThrow(() -> new CleanupEventNotFoundException("CleanupEvent not found: " + id));
                    return cleanupEvent;
                })
                .map(cleanupEventMapper::toDto);
    }
}
