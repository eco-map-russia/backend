package com.example.eco_map.api.strategy;

import com.example.eco_map.usecases.RadiationDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class RadiationMapLayerStrategy implements MapLayerStrategy {
    private final RadiationDataService radiationDataService;

    @Override
    public MapLayerType getType() {
        return MapLayerType.RADIATION;
    }

    @Override
    public Flux<Object> getMapData() {
        return radiationDataService.getAllRadiationDataForMap().cast(Object.class);
    }
}
