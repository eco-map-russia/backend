package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.usecases.RegionService;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import com.example.eco_map.usecases.mapper.RegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;
    private final Scheduler jdbcScheduler;

    @Override
    public Flux<RegionResponseDto> getAllRegions() {
        return Mono.fromCallable(regionRepository::findAll)
                .subscribeOn(jdbcScheduler)
                .flatMapMany(Flux::fromIterable)
                .map(regionMapper::regionToRegionResponseDto);
    }

}
