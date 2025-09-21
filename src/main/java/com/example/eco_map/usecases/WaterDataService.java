package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.WaterData;
import com.example.eco_map.usecases.dto.PageWaterDataResponseDto;
import com.example.eco_map.usecases.dto.UpdateWaterDataRequestDto;
import com.example.eco_map.usecases.dto.WaterDataRequestDto;
import com.example.eco_map.usecases.dto.WaterDataResponseDto;
import com.example.eco_map.usecases.dto.WaterMapDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface WaterDataService {
    Mono<Optional<WaterData>> getLatestByRegion(Region region);

    Flux<WaterMapDto> getAllWaterDataForMap();

    Mono<WaterDataResponseDto> addWaterData(WaterDataRequestDto waterDataRequestDto);

    Mono<PageWaterDataResponseDto> getAllWaterData(Pageable pageable);

    Mono<WaterDataResponseDto> updateWaterData(UUID id, UpdateWaterDataRequestDto waterDataRequestDto);

    Mono<Void> removeWaterData(UUID id);
}
