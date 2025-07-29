package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.RadiationData;
import com.example.eco_map.usecases.dto.RadiationMapDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RadiationDataService {

    Mono<RadiationData> findRadiationData(ObservationPoint nearestPoint);

    Flux<RadiationMapDto> getAllRadiationDataForMap();
}
