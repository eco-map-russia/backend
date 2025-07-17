package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.AirQualityDataRepository;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.usecases.ObservationPointService;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import com.example.eco_map.usecases.mapper.AirQualityMapper;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@RequiredArgsConstructor
public class ObservationPointServiceImpl implements ObservationPointService {
    private final ObservationPointRepository observationPointRepository;
    private final AirQualityDataRepository airQualityDataRepository;
    private final AirQualityMapper airQualityMapper;
    private final Scheduler jdbcScheduler;
    private final GeometryFactory geometryFactory;


    @Override
    public Mono<ObservationPointResponseDto> getLatestDataByCoordinates(Double lat, Double lon) {
        return Mono.fromCallable(() -> {
            Point point = geometryFactory.createPoint(new Coordinate(lon, lat));
            ObservationPoint nearestPoint = observationPointRepository.findNearestPoint(point)
                    .orElseThrow(() -> new RuntimeException("Observation point not found"));

            ObservationPointResponseDto dto = new ObservationPointResponseDto();

            dto.setCoordinates(new CoordinatesResponseDto(lat, lon));
            airQualityDataRepository.findLatestByObservationPoint(nearestPoint)
                    .ifPresent(data -> dto.setAirQualityData(airQualityMapper.entityToDto(data)));
            return dto;
        }).subscribeOn(jdbcScheduler);
    }

}
