package com.example.eco_map.usecases.dto;

import lombok.Data;

@Data
public class ObservationPointResponseDto {
    private CoordinatesResponseDto coordinates;
    private AirQualityResponseDto airQualityData;
}
