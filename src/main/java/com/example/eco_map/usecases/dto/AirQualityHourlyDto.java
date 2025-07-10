package com.example.eco_map.usecases.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AirQualityHourlyDto {
    private List<String> time;
    private List<Double> pm10;
    @JsonProperty("pm2_5")
    private List<Double> pm25;
}
