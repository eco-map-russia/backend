package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.RadiationData;
import com.example.eco_map.persistence.repository.RadiationDataRepository;
import com.example.eco_map.usecases.RadiationDataService;
import com.example.eco_map.usecases.dto.RadiationMapDto;
import com.example.eco_map.usecases.mapper.RadiationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@RequiredArgsConstructor
public class RadiationDataServiceImpl implements RadiationDataService {
    private final RadiationDataRepository radiationDataRepository;
    private final Scheduler jdbcScheduler;
    private final RadiationMapper radiationMapper;

    @Override
    public Mono<RadiationData> findRadiationData(ObservationPoint point) {
        return Mono.fromCallable(() -> radiationDataRepository.findTopByObservationPointOrderByCreatedAtDesc(point))
                .flatMap(Mono::justOrEmpty)
                .subscribeOn(jdbcScheduler);

    }

    @Override
    public Flux<RadiationMapDto> getAllRadiationDataForMap() {
        return Mono.fromCallable(radiationDataRepository::findLatestRadiationDataWithPoint)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler)
                .map(radiationMapper::toMapDto);
    }
}
