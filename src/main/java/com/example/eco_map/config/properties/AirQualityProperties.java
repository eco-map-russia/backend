package com.example.eco_map.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "air.quality")

public class AirQualityProperties {
    private Integer batchSize;
    private String baseUrl;
    private List<String> currentParameters;
}