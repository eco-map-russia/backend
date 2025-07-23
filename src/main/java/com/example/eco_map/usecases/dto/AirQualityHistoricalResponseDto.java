package com.example.eco_map.usecases.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AirQualityHistoricalResponseDto {
    private LocalDate date;
    private Double pm10;
    private Double pm25;
    private Double carbonMonoxide;
    private Double carbonDioxide;
    private Double nitrogenDioxide;
    private Double sulphurDioxide;
    private Double ozone;
    private Double aerosolOpticalDepth;
    private Double dust;
    private Double methane;
    private Double europeanAqi;


}
