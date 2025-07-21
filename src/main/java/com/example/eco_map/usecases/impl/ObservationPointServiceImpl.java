package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.AirQualityDataRepository;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.usecases.AirQualityDataService;
import com.example.eco_map.usecases.ObservationPointService;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.ObservationPointHistoricalResponseDto;
import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import com.example.eco_map.usecases.mapper.AirQualityMapper;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ObservationPointServiceImpl implements ObservationPointService {
    private final ObservationPointRepository observationPointRepository;
    private final AirQualityDataRepository airQualityDataRepository;
    private final AirQualityDataService airQualityDataService;
    private final AirQualityMapper airQualityMapper;
    private final Scheduler jdbcScheduler;
    private final GeometryFactory geometryFactory;


    @Override
    public Mono<ObservationPointResponseDto> getLatestDataByCoordinates(Double lat, Double lon) {
        return Mono.fromCallable(() -> {
            ObservationPoint nearestPoint = findNearestObservationPoint(lat, lon);
            ObservationPointResponseDto dto = new ObservationPointResponseDto();
            dto.setCoordinates(new CoordinatesResponseDto(lat, lon));
            airQualityDataRepository.findLatestByObservationPoint(nearestPoint)
                    .ifPresent(data -> dto.setAirQualityData(airQualityMapper.entityToDto(data)));
            return dto;
        }).subscribeOn(jdbcScheduler);
    }

    @Override
    public ObservationPoint findNearestObservationPoint(double lat, double lon) {
        Point point = geometryFactory.createPoint(new Coordinate(lon, lat));
        return observationPointRepository.findNearestPoint(point)
                .orElseThrow(() -> new RuntimeException("Observation point not found"));
    }

    @Override
    public Flux<ObservationPointHistoricalResponseDto> getHistoricalDataForObservationPoint(
            double lat,
            double lon,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("StartDate must be before endDate");
        }
        ObservationPoint nearestPoint = findNearestObservationPoint(lat, lon);

        return airQualityDataService.getHistoricalAirQualityData(nearestPoint, startDate, endDate)
                .map(airQualityDto -> {
                    ObservationPointHistoricalResponseDto dto = new ObservationPointHistoricalResponseDto();
                    dto.setCoordinates(new CoordinatesResponseDto(lat, lon));
                    dto.setAirQualityHistoricalResponseDto(airQualityDto);
                    return dto;
                });
    }

}
