package com.example.eco_map.api.strategy;

public class MapLayerTypeConverter {


    public static MapLayerType fromString(String value) {
        for (MapLayerType target : MapLayerType.values()) {
            if (target.getValue().equalsIgnoreCase(value)) {
                return target;
            }
        }
        throw new IllegalArgumentException("Unknown service type value: " + value);
    }

}
