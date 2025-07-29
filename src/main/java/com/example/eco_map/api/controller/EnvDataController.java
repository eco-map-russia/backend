package com.example.eco_map.api.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EnvDataController {

    private final RegionService regionService;
    private final ObservationPointService observationPointService;
    private final SearchService searchService;
    private final MapLayerStrategyResolver resolver;

    @GetMapping("/regions")
    public Flux<RegionResponseDto> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/air-quality/current")
    public Mono<AirQualityObservationDto> getCurrentAirQuality(
            @RequestParam Double lat,
            @RequestParam Double lon) {
        return observationPointService.getLatestAirQualityDataByCoordinates(lat, lon);
    }

    @GetMapping("/search")
    public Flux<LocationSearchDto> searchLocation(@RequestParam String query) {
        return searchService.searchCityOrRegionByName(query);
    }

    @GetMapping("/regions/{id}")
    public Mono<RegionDetailsDto> getRegionById(@PathVariable UUID id) {
        return regionService.getRegionById(id);
    }

    @GetMapping("/stations")
    public Mono<RadiationDataObservationDto> getRadiationData(
            @RequestParam Double lat,
            @RequestParam Double lon) {
        return observationPointService.getRadiationDataByCoordinates(lat, lon);
    }

    @GetMapping("/map/layer/{type}")
    public Flux<?> getMapLayerData(@PathVariable String type) {
        return resolver.resolve(type).getMapData();
    }
}
