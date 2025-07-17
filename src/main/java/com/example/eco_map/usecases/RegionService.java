package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.RegionResponseDto;
import reactor.core.publisher.Flux;

public interface RegionService {
    Flux<RegionResponseDto> getAllRegions();
}
