package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.usecases.dto.ObservationPointHistoricalResponseDto;
import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ObservationPointService {
    Mono<ObservationPointResponseDto> getLatestDataByCoordinates(Double lat, Double lon);

    ObservationPoint findNearestObservationPoint(double lat, double lon);

    Flux<ObservationPointHistoricalResponseDto> getHistoricalDataForObservationPoint(
            double lat,
            double lon,
            LocalDate startDate,
            LocalDate endDate
    );
}
