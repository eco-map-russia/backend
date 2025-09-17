package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.CityNotFoundException;
import com.example.eco_map.api.exception.CleanupEventNotFoundException;
import com.example.eco_map.api.exception.CleanupEventUpdateException;
import com.example.eco_map.persistence.model.City;
import com.example.eco_map.persistence.model.CleanupEvent;
import com.example.eco_map.persistence.repository.CityRepository;
import com.example.eco_map.persistence.repository.CleanupEventRepository;
import com.example.eco_map.usecases.CleanupService;
import com.example.eco_map.usecases.dto.CleanupEventCreateRequestDto;
import com.example.eco_map.usecases.dto.CleanupEventMapDto;
import com.example.eco_map.usecases.dto.CleanupEventResponseDto;
import com.example.eco_map.usecases.dto.CleanupEventUpdateDto;
import com.example.eco_map.usecases.dto.PageCleanupEventResponseDto;
import com.example.eco_map.usecases.mapper.CleanupEventMapper;
import com.example.eco_map.usecases.mapper.PageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
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
    private final TransactionTemplate transactionTemplate;
    private final CityRepository cityRepository;
    private final PageMapper pageMapper;

    @Override
    public Flux<CleanupEventMapDto> getAllCleanupEventsForMap() {
        return Mono.fromCallable(cleanupEventRepository::findAllForMap)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler)
                .map(cleanupEventMapper::toDto);
    }

    @Override
    public Mono<CleanupEventResponseDto> addCleanupEvent(CleanupEventCreateRequestDto dto) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    City city = cityRepository.findById(dto.getCityId()).orElseThrow(
                            () -> new CityNotFoundException("City not found: " + dto.getCityId()));

                    CleanupEvent cleanupEvent = cleanupEventMapper.toEntity(dto);
                    cleanupEvent.setCity(city);
                    return cleanupEventRepository.save(cleanupEvent);
                })).subscribeOn(jdbcScheduler)
                .map(cleanupEventMapper::toResponseDto);
    }

    @Override
    public Mono<PageCleanupEventResponseDto> getAllCleanupEvents(Pageable pageable) {
        return Mono.fromCallable(() -> cleanupEventRepository.findAll(pageable))
                .subscribeOn(jdbcScheduler)
                .map(page -> {
                            Page<CleanupEventResponseDto> dtoPage = page.map(cleanupEventMapper::toResponseDto);
                            return pageMapper.mapToPageCleanupEventsResponse(dtoPage);
                        }
                );
    }

    @Override
    public Mono<CleanupEventResponseDto> updateCleanupEvent(UUID id, CleanupEventUpdateDto dto) {
        if (!id.equals(dto.getId())) {
            throw new CleanupEventNotFoundException("CleanupEvent not found: " + id);
        }
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    CleanupEvent existing = cleanupEventRepository.findByIdWithCity(id)
                            .orElseThrow(() -> new CleanupEventNotFoundException("Cleanup event not found: " + id));

                    if (!existing.getCity().getId().equals(dto.getCityId())) {
                        throw new CleanupEventUpdateException("City cannot be changed");
                    }

                    existing.setOrganizer(dto.getOrganizer());
                    existing.setDate(dto.getDate());
                    existing.setLocation(dto.getLocation());
                    existing.setDescription(dto.getDescription());
                    existing.setParticipantsExpected(dto.getParticipantsExpected());

                    return cleanupEventRepository.save(existing);
                })).subscribeOn(jdbcScheduler)
                .map(cleanupEventMapper::toResponseDto);
    }

    @Override
    public Mono<Void> deleteCleanupEvent(UUID id) {
        return Mono.fromCallable(() -> {
            transactionTemplate.executeWithoutResult(status ->
                    cleanupEventRepository.findById(id)
                            .ifPresent(cleanupEventRepository::delete)
            );
            return null;
        }).subscribeOn(jdbcScheduler).then();
    }
}
