package com.example.eco_map.usecases.scheduler;

import com.example.eco_map.api.client.AirQualityBaseClient;
import com.example.eco_map.config.properties.AirQualityProperties;
import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.usecases.AirQualityDataService;
import com.example.eco_map.usecases.mapper.AirQualityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class AirQualityScheduler {
    private final Scheduler jdbcScheduler;
    private final ObservationPointRepository observationPointRepository;
    private final AirQualityMapper airQualityMapper;
    private final AirQualityDataService airQualityDataService;
    private final AirQualityProperties airQualityProperties;
    private final AirQualityBaseClient airQualityClient;
    private int currentOffset = 0;
    private int totalPoints = -1;

    @Scheduled(fixedRate = 600000, initialDelay = 120000)
    public void fetchAndSaveAirQuality() {
        int batchSize = airQualityProperties.getBatchSize();

        Mono.fromCallable(() -> {
                    if (totalPoints < 0) {
                        totalPoints = (int) observationPointRepository.count();
                    }
                    int currentPage = currentOffset / batchSize;

                    List<ObservationPoint> observationPointList = observationPointRepository
                            .findAll(PageRequest.of(currentPage, batchSize))
                            .getContent();

                    if (observationPointList.isEmpty()) {
                        currentOffset = 0;
                        return observationPointRepository.findAll(PageRequest.of(0, batchSize)).getContent();
                    } else {
                        currentOffset += batchSize;
                    }

                    return observationPointList;
                })
                .subscribeOn(jdbcScheduler)
                .flatMapMany(Flux::fromIterable)
                .flatMap(point -> {

                    Double lat = point.getCoordinates().getY();
                    Double lon = point.getCoordinates().getX();

                    return airQualityClient.getCurrentAirQuality(lat, lon)
                            .map(dto -> {
                                AirQualityData data = airQualityMapper.dtoToEntity(dto);
                                data.setObservationPoint(point);
                                return data;
                            })
                            .onErrorResume(e -> {
                                log.warn("Failed to fetch air quality for {}, {}: {}", lat, lon, e.getMessage());
                                return Mono.empty();
                            });
                })
                .collectList()
                .flatMap(dataList -> {
                    return Mono.fromRunnable(() -> airQualityDataService.saveAllAirQualityData(dataList))
                            .subscribeOn(jdbcScheduler);
                })
                .doOnError(e -> log.error("Failed to save air quality batch", e))
                .block();
    }
}
