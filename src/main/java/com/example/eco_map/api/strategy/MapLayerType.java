package com.example.eco_map.api.strategy;

import lombok.Getter;

@Getter
public enum MapLayerType {
    SOIL("soil"),
    WATER("water"),
    AIR("air"),
    RADIATION("radiation");
    private final String value;

    MapLayerType(String value) {
        this.value = value;
    }
}
