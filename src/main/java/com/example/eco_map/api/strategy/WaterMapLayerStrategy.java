package com.example.eco_map.api.strategy;

import com.example.eco_map.usecases.WaterDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class WaterMapLayerStrategy implements MapLayerStrategy {
    private final WaterDataService waterDataService;

    @Override
    public MapLayerType getType() {
        return MapLayerType.WATER;
    }

    @Override
    public Flux<Object> getMapData() {
        return waterDataService.getAllWaterDataForMap().cast(Object.class);
    }
}
