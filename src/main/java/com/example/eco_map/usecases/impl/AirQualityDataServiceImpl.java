package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.AirQualityDataRepository;
import com.example.eco_map.usecases.AirQualityDataService;
import com.example.eco_map.usecases.dto.AirMapDto;
import com.example.eco_map.usecases.dto.AirQualityHistoricalResponseDto;
import com.example.eco_map.usecases.mapper.AirQualityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirQualityDataServiceImpl implements AirQualityDataService {

    private final AirQualityDataRepository airQualityDataRepository;
    private final Scheduler jdbcScheduler;
    private final AirQualityMapper airQualityMapper;

    @Transactional
    public List<AirQualityData> saveAllAirQualityData(List<AirQualityData> dataList) {
        return airQualityDataRepository.saveAll(dataList);

    }

    @Override
    public Mono<AirQualityData> getLatestByCoordinates(ObservationPoint point) {
        return Mono.fromCallable(() -> airQualityDataRepository.findTopByObservationPointOrderByTimeDesc(point))
                .flatMap(Mono::justOrEmpty)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<AirMapDto> getAllAirDataForMap() {
        return Mono.fromCallable(airQualityDataRepository::findLatestAirDataWithPoint)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler)
                .map(airQualityMapper::toMapDto);
    }

    @Override
    public Flux<AirQualityHistoricalResponseDto> getHistoricalAirQualityData(
            ObservationPoint nearestPoint,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return Mono.fromCallable(() -> {

                    return airQualityDataRepository.findDailyAveragesAirQualityData(
                            nearestPoint.getId(), startDate, endDate
                    );
                })
                .subscribeOn(jdbcScheduler)
                .flatMapMany(Flux::fromIterable);
    }
}