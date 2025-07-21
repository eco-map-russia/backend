package com.example.eco_map.usecases.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AirQualityCurrentDto {
    private String time;

    private Double pm10;

    @JsonProperty("pm2_5")
    private Double pm25;

    @JsonProperty("carbon_monoxide")
    private Double carbonMonoxide;

    @JsonProperty("carbon_dioxide")
    private Double carbonDioxide;

    @JsonProperty("nitrogen_dioxide")
    private Double nitrogenDioxide;

    @JsonProperty("sulphur_dioxide")
    private Double sulphurDioxide;

    private Double ozone;

    @JsonProperty("aerosol_optical_depth")
    private Double aerosolOpticalDepth;

    private Double dust;

    private Double methane;

    @JsonProperty("european_aqi")
    private Double europeanAqi;
}