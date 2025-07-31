package com.example.eco_map.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "import")
@Component
@Getter
@Setter
public class PathProperties {
    private String citiesFile;
    private String regionsFile;
    private String pointsFile;
    private String waterDataFile;
    private String landDegradationIndexFile;
    private String soilPollutionDataFile;
    private String radiationDataFile;
}
