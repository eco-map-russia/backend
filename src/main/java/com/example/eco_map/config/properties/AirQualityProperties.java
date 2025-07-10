package com.example.eco_map.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "air.quality")

public class AirQualityProperties {
    private Integer batchSize;
    private String baseUrl;
    private Templates templates;

    @Getter
    @Setter
    public static class Templates {
        private String current;
        private String historical;
    }
}