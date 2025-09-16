package com.example.eco_map.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.geo.simplify")
public class GeoSimplifyProperties {
    private Double soil;
    private Double water;
}
