package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.SoilData;
import com.example.eco_map.persistence.repository.SoilDataRepository;
import com.example.eco_map.usecases.SoilDataService;
import com.example.eco_map.usecases.dto.SoilMapDto;
import com.example.eco_map.usecases.mapper.SoilDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@RequiredArgsConstructor
public class SoilDataServiceImpl implements SoilDataService {
    private final SoilDataRepository soilDataRepository;
    private final Scheduler jdbcScheduler;
    private final SoilDataMapper soilDataMapper;

    @Override
    public Mono<SoilData> getLatestByRegion(Region region) {
        return Mono.fromCallable(() -> soilDataRepository.findFirstByRegionOrderByCreatedAtDesc(region))
                .flatMap(Mono::justOrEmpty)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<SoilMapDto> getAllSoilDataForMap() {
        return Mono.fromCallable(soilDataRepository::findLatestSoilDataWithRegion)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler)
                .map(soilDataMapper::toMapDto);
    }

}
