package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import reactor.core.publisher.Mono;

public interface ObservationPointService {
    Mono<ObservationPointResponseDto> getLatestDataByCoordinates(Double lat, Double lon);
}
