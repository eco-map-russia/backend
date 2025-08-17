package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.RadiationData;
import com.example.eco_map.usecases.dto.PageRadiationDataResponseDto;
import com.example.eco_map.usecases.dto.RadiationDataRequestDto;
import com.example.eco_map.usecases.dto.RadiationDataResponseDto;
import com.example.eco_map.usecases.dto.RadiationMapDto;
import com.example.eco_map.usecases.dto.UpdateRadiationDataRequestDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RadiationDataService {

    Mono<RadiationData> findRadiationData(ObservationPoint nearestPoint);

    Flux<RadiationMapDto> getAllRadiationDataForMap();

    Mono<RadiationDataResponseDto> addRadiationData(RadiationDataRequestDto dto);

    Mono<RadiationDataResponseDto> updateRadiationData(UUID id, UpdateRadiationDataRequestDto dto);

    Mono<PageRadiationDataResponseDto> getAllRadiationData(Pageable pageable);

    Mono<Void> removeRadiationData(UUID id);
}
