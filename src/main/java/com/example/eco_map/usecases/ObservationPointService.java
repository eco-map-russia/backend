package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.usecases.dto.AirQualityHistoricalObservationDto;
import com.example.eco_map.usecases.dto.AirQualityObservationDto;
import com.example.eco_map.usecases.dto.RadiationDataObservationDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ObservationPointService {
    Mono<AirQualityObservationDto> getLatestAirQualityDataByCoordinates(Double lat, Double lon);

    Mono<ObservationPoint> findNearestObservationPoint(double lat, double lon);

    Flux<AirQualityHistoricalObservationDto> getHistoricalDataForObservationPoint(
            double lat,
            double lon,
            LocalDate startDate,
            LocalDate endDate
    );

    Mono<RadiationDataObservationDto> getRadiationDataByCoordinates(double lat, double lon);
}
