package com.example.eco_map.api.strategy;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MapLayerStrategyResolver {
    private final Map<MapLayerType, MapLayerStrategy> strategiesByType;

    public MapLayerStrategyResolver(List<MapLayerStrategy> strategies) {
        this.strategiesByType = strategies.stream()
                .collect(Collectors.toUnmodifiableMap(
                        MapLayerStrategy::getType,
                        Function.identity()
                ));
    }

    public MapLayerStrategy resolve(String type) {
        MapLayerStrategy strategy = strategiesByType.get(MapLayerTypeConverter.fromString(type));
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported map layer type: " + type);
        }
        return strategy;
    }
}