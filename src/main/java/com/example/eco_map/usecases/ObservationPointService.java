package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.usecases.dto.AirQualityHistoricalObservationDto;
import com.example.eco_map.usecases.dto.AirQualityObservationDto;
import com.example.eco_map.usecases.dto.ObservationPointRequestDto;
import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import com.example.eco_map.usecases.dto.PageObservationPointResponseDto;
import com.example.eco_map.usecases.dto.RadiationDataObservationDto;
import com.example.eco_map.usecases.dto.UpdateObservationPointRequestDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

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

    Mono<ObservationPointResponseDto> addObservationPoint(ObservationPointRequestDto dto);

    Mono<PageObservationPointResponseDto> getAllObservationPoints(Pageable pageable);

    Mono<ObservationPointResponseDto> updateObservationPoint(UUID id, UpdateObservationPointRequestDto dto);

    Mono<Void> deleteObservationPoint(UUID id);
}
