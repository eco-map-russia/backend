package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.SoilData;
import com.example.eco_map.usecases.dto.PageSoilDataResponseDto;
import com.example.eco_map.usecases.dto.SoilDataRequestDto;
import com.example.eco_map.usecases.dto.SoilDataResponseDto;
import com.example.eco_map.usecases.dto.SoilMapDto;
import com.example.eco_map.usecases.dto.UpdateSoilDataRequestDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SoilDataService {
    Mono<SoilData> getLatestByRegion(Region region);

    Flux<SoilMapDto> getAllSoilDataForMap();

    Mono<SoilDataResponseDto> addSoilData(SoilDataRequestDto dto);

    Mono<PageSoilDataResponseDto> getSoilData(Pageable pageable);

    Mono<SoilDataResponseDto> updateSoilData(UUID id, UpdateSoilDataRequestDto dto);

    Mono<Void> removeSoilData(UUID id);
}
