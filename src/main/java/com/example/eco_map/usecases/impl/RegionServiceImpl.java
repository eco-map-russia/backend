package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.RegionNotFoundException;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.usecases.RegionDetailsAggregator;
import com.example.eco_map.usecases.RegionService;
import com.example.eco_map.usecases.dto.RegionDetailsDto;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import com.example.eco_map.usecases.mapper.RegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;
    private final Scheduler jdbcScheduler;
    private final RegionDetailsAggregator regionDetailsAggregator;


    @Override
    public Flux<RegionResponseDto> getAllRegions() {
        return Mono.fromCallable(regionRepository::findAll)
                .subscribeOn(jdbcScheduler)
                .flatMapMany(Flux::fromIterable)
                .map(regionMapper::regionToRegionResponseDto);
    }

    @Override
    public Mono<RegionDetailsDto> getRegionById(UUID id) {
        return Mono.fromCallable(() -> regionRepository.findById(id))
                .flatMap(optionalRegion -> optionalRegion
                        .map(regionDetailsAggregator::buildDetails)
                        .orElseGet(() -> Mono.error(new RegionNotFoundException("Region not found"))))
                .subscribeOn(jdbcScheduler);
    }
}
