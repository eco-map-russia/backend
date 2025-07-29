package com.example.eco_map.api.strategy;

import reactor.core.publisher.Flux;

public interface MapLayerStrategy {
    MapLayerType getType();

    Flux<?> getMapData();
}