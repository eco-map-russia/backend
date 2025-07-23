package com.example.eco_map.usecases.dto;

import lombok.Data;

@Data
public class ObservationPointHistoricalResponseDto {
    private CoordinatesResponseDto coordinates;
    private AirQualityHistoricalResponseDto airQualityHistoricalResponseDto;

}
