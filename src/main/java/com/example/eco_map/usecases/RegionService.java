package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.dto.PageRegionResponseDto;
import com.example.eco_map.usecases.dto.RegionDetailsDto;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RegionService {
    Flux<RegionResponseDto> getAllRegions();

    Mono<RegionDetailsDto> getRegionById(UUID id);

    void saveRegion(Region region);

    Mono<PageRegionResponseDto> getAllRegions(Pageable pageable);
}
