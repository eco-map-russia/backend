package com.example.eco_map.api.strategy;

import com.example.eco_map.usecases.AirQualityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class AirMapLayerStrategy implements MapLayerStrategy {
    private final AirQualityDataService airQualityDataService;

    @Override
    public MapLayerType getType() {
        return MapLayerType.AIR;
    }

    @Override
    public Flux<?> getMapData() {
        return airQualityDataService.getAllAirDataForMap();
    }
}
