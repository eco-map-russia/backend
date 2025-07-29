package com.example.eco_map.api.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MapLayerStrategyResolver {
    private final List<MapLayerStrategy> strategies;

    public MapLayerStrategy resolve(String type) {
        return strategies.stream()
                .filter(strategy -> strategy.getType().equals(MapLayerTypeConvector.fromString(type)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported map layer type: " + type));
    }
}
