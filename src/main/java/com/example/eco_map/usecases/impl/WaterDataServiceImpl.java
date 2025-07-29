package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.WaterData;
import com.example.eco_map.persistence.repository.WaterDataRepository;
import com.example.eco_map.usecases.WaterDataService;
import com.example.eco_map.usecases.dto.WaterMapDto;
import com.example.eco_map.usecases.mapper.WaterDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@RequiredArgsConstructor
public class WaterDataServiceImpl implements WaterDataService {
    private final WaterDataRepository waterDataRepository;
    private final Scheduler jdbcScheduler;
    private final WaterDataMapper waterDataMapper;

    @Override
    public Mono<WaterData> getLatestByRegion(Region region) {
        return Mono.fromCallable(() -> waterDataRepository.findFirstByRegionOrderByCreatedAt(region))
                .flatMap(optional -> Mono.justOrEmpty(optional))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<WaterMapDto> getAllWaterDataForMap() {
        return Mono.fromCallable(() -> waterDataRepository.findLatestWaterDataWithRegion())
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler)
                .map(waterDataMapper::toMapDto);
    }
}
