package com.example.eco_map.usecases.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AirQualityCurrentDto {
    private String time;
    private Double pm10;
    @JsonProperty("pm2_5")
    private Double pm25;
}