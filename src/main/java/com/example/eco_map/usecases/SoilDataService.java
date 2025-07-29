package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.SoilData;
import com.example.eco_map.usecases.dto.SoilMapDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SoilDataService {
    Mono<SoilData> getLatestByRegion(Region region);

    Flux<SoilMapDto> getAllSoilDataForMap();
}
