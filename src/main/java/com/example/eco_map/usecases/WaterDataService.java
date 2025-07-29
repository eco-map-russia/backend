package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.WaterData;
import com.example.eco_map.usecases.dto.WaterMapDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WaterDataService {
    Mono<WaterData> getLatestByRegion(Region region);

    Flux<WaterMapDto> getAllWaterDataForMap();
}
