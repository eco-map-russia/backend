package com.example.eco_map.api.strategy;

import com.example.eco_map.usecases.CleanupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class CleanupEventStrategy implements MapLayerStrategy {
    private final CleanupService cleanupService;

    @Override
    public MapLayerType getType() {
        return MapLayerType.CLEANUP_EVENTS;
    }

    @Override
    public Flux<Object> getMapData() {
        return cleanupService.getAllCleanupEventsForMap().cast(Object.class);
    }
}
