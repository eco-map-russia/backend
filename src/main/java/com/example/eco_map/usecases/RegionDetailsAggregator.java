package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.dto.RegionDetailsDto;
import reactor.core.publisher.Mono;

public interface RegionDetailsAggregator {
    Mono<RegionDetailsDto> buildDetails(Region region);
}
