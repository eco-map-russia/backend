package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.AirQualityNotFoundException;
import com.example.eco_map.api.exception.ObservationPointNotFound;
import com.example.eco_map.api.exception.RadiationNotFoundException;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.usecases.AirQualityDataService;
import com.example.eco_map.usecases.ObservationPointService;
import com.example.eco_map.usecases.RadiationDataService;
import com.example.eco_map.usecases.dto.AirQualityHistoricalObservationDto;
import com.example.eco_map.usecases.dto.AirQualityObservationDto;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.RadiationDataObservationDto;
import com.example.eco_map.usecases.mapper.AirQualityMapper;
import com.example.eco_map.usecases.mapper.RadiationMapper;
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
    private final AirQualityDataService airQualityDataService;
    private final AirQualityMapper airQualityMapper;
    private final Scheduler jdbcScheduler;
    private final GeometryFactory geometryFactory;
    private final RadiationDataService radiationDataService;
    private final RadiationMapper radiationMapper;

    @Override
    public Mono<AirQualityObservationDto> getLatestAirQualityDataByCoordinates(Double lat, Double lon) {
        return findNearestObservationPoint(lat, lon)
                .flatMap(point -> airQualityDataService.getLatestByCoordinates(point))
                .flatMap(data -> {

                    AirQualityObservationDto dto = new AirQualityObservationDto();
                    dto.setCoordinatesResponseDto(new CoordinatesResponseDto().lat(lat).lon(lon));
                    dto.setAirQualityData(airQualityMapper.entityToDto(data));
                    return Mono.just(dto);
                })
                .switchIfEmpty(Mono.error(new AirQualityNotFoundException("No air quality data found for this point")));
    }

    @Override
    public Mono<ObservationPoint> findNearestObservationPoint(double lat, double lon) {
        return Mono.fromCallable(() -> {
            Point point = geometryFactory.createPoint(new Coordinate(lon, lat));
            return observationPointRepository.findNearestPoint(point)
                    .orElseThrow(() -> new ObservationPointNotFound("Observation point not found"));
        }).subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<AirQualityHistoricalObservationDto> getHistoricalDataForObservationPoint(
            double lat,
            double lon,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("StartDate must be before endDate");
        }


        return findNearestObservationPoint(lat, lon)
                .flatMapMany(point -> airQualityDataService.getHistoricalAirQualityData(point, startDate, endDate))
                .map(airQualityDto -> {
                    AirQualityHistoricalObservationDto dto = new AirQualityHistoricalObservationDto();
                    dto.setCoordinatesResponseDto(new CoordinatesResponseDto().lat(lat).lon(lon));
                    dto.setAirQualityHistoricalResponseDto(airQualityDto);
                    return dto;
                });
    }

    @Override
    public Mono<RadiationDataObservationDto> getRadiationDataByCoordinates(double lat, double lon) {
        return findNearestObservationPoint(lat, lon)
                .flatMap(point -> radiationDataService.findRadiationData(point))
                .flatMap(data -> {
                    RadiationDataObservationDto dto = new RadiationDataObservationDto();
                    dto.setCoordinatesResponseDto(new CoordinatesResponseDto().lat(lat).lon(lon));
                    dto.setRadiationDataDto(radiationMapper.toDto(data));
                    return Mono.just(dto);
                })
                .switchIfEmpty(Mono.error(new RadiationNotFoundException("Radiation data not found for this point")));
    }

}
