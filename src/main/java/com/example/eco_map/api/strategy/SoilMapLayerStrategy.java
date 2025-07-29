package com.example.eco_map.api.strategy;

import com.example.eco_map.usecases.SoilDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class SoilMapLayerStrategy implements MapLayerStrategy {
    private final SoilDataService soilDataService;

    @Override
    public MapLayerType getType() {
        return MapLayerType.SOIL;
    }

    @Override
    public Flux<?> getMapData() {
        return soilDataService.getAllSoilDataForMap();
    }
}
