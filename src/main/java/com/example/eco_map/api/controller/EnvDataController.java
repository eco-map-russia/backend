package com.example.eco_map.api.controller;

import com.example.eco_map.usecases.ObservationPointService;
import com.example.eco_map.usecases.RegionService;
import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class EnvDataController {
    private final RegionService regionService;
    private final ObservationPointService observationPointService;

    @GetMapping(path = "/regions")
    public Flux<RegionResponseDto> getRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/data")
    public Mono<ResponseEntity<ObservationPointResponseDto>> getNearestData(
            @RequestParam double lat,
            @RequestParam double lon) {
        return observationPointService.getLatestDataByCoordinates(lat, lon)
                .map(ResponseEntity::ok);
    }
}
