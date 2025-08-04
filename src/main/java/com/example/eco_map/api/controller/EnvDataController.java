package com.example.eco_map.api.controller;

import com.example.eco_map.api.EnvDataApi;
import com.example.eco_map.api.strategy.MapLayerStrategyResolver;
import com.example.eco_map.usecases.ObservationPointService;
import com.example.eco_map.usecases.RegionService;
import com.example.eco_map.usecases.SearchService;
import com.example.eco_map.usecases.dto.AirQualityObservationDto;
import com.example.eco_map.usecases.dto.LocationSearchDto;
import com.example.eco_map.usecases.dto.RadiationDataObservationDto;
import com.example.eco_map.usecases.dto.RegionDetailsDto;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EnvDataController implements EnvDataApi {

    private final RegionService regionService;
    private final ObservationPointService observationPointService;
    private final SearchService searchService;
    private final MapLayerStrategyResolver resolver;

    @Override
    @GetMapping("/regions")
    public Mono<ResponseEntity<Flux<RegionResponseDto>>> getAllRegions(ServerWebExchange exchange) {
        return Mono.defer(() -> Mono.just(ResponseEntity.ok(regionService.getAllRegions())));
    }

    @Override
    @GetMapping("/air-quality/current")
    public Mono<ResponseEntity<AirQualityObservationDto>> getCurrentAirQuality(
            @RequestParam Double lat,
            @RequestParam Double lon,
            ServerWebExchange exchange) {
        return observationPointService.getLatestAirQualityDataByCoordinates(lat, lon)
                .map(ResponseEntity::ok);
    }

    @Override
    @GetMapping("/map/layer/{type}")
    public Mono<ResponseEntity<Flux<Object>>> getMapLayerData(@PathVariable String type, ServerWebExchange exchange) {
        return Mono.defer(() -> Mono.just(resolver.resolve(type).getMapData()).map(ResponseEntity::ok));
    }


    @Override
    @GetMapping("/stations")
    public Mono<ResponseEntity<RadiationDataObservationDto>> getRadiationData(@RequestParam Double lat,
                                                                              @RequestParam Double lon,
                                                                              ServerWebExchange exchange) {
        return observationPointService.getRadiationDataByCoordinates(lat, lon)
                .map(ResponseEntity::ok);
    }

    @Override
    @GetMapping("/regions/{id}")
    public Mono<ResponseEntity<RegionDetailsDto>> getRegionById(@PathVariable UUID id, ServerWebExchange exchange) {
        return regionService.getRegionById(id)
                .map(ResponseEntity::ok);
    }


    @GetMapping("/search")
    @Override
    public Mono<ResponseEntity<Flux<LocationSearchDto>>> searchLocation(@RequestParam String query,
                                                                        ServerWebExchange exchange) {
        return Mono.defer(() -> Mono.just(ResponseEntity.ok(searchService.searchCityOrRegionByName(query))));
    }
}
