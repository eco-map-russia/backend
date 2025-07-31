package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.RegionDetailsDto;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RegionService {
    Flux<RegionResponseDto> getAllRegions();

    Mono<RegionDetailsDto> getRegionById(UUID id);
}
