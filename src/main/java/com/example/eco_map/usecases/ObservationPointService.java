package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.ObservationPointResponseDto;

public interface ObservationPointService {

    ObservationPointResponseDto getLatestDataByCoordinates(Double lat, Double lon);
}
