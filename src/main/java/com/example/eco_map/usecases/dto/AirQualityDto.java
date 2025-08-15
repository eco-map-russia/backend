package com.example.eco_map.usecases.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirQualityDto {
    private AirQualityCurrentDto current;
}