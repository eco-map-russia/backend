package com.example.eco_map.usecases.dto;

import java.time.LocalDateTime;

public record AirQualityResponseDto(
        LocalDateTime time,
        Double pm10,
        Double pm25,
        Double carbonMonoxide,
        Double carbonDioxide,
        Double nitrogenDioxide,
        Double sulphurDioxide,
        Double ozone,
        Double aerosolOpticalDepth,
        Double dust,
        Double methane,
        Double europeanAqi) {
}
