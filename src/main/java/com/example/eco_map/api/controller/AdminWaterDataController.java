package com.example.eco_map.api.controller;

import com.example.eco_map.api.AdminWaterDataApi;
import com.example.eco_map.usecases.WaterDataService;
import com.example.eco_map.usecases.dto.PageWaterDataResponseDto;
import com.example.eco_map.usecases.dto.UpdateWaterDataRequestDto;
import com.example.eco_map.usecases.dto.WaterDataRequestDto;
import com.example.eco_map.usecases.dto.WaterDataResponseDto;
import com.example.eco_map.util.PaginationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/water")
public class AdminWaterDataController implements AdminWaterDataApi{
    private final WaterDataService waterDataService;

    @PostMapping
    public Mono<ResponseEntity<WaterDataResponseDto>> addWaterData(
            @RequestBody @Valid Mono<WaterDataRequestDto> waterDataRequestDtoMono,
            ServerWebExchange exchange) {
        return waterDataRequestDtoMono.flatMap(
                waterDataService::addWaterData
        ).map(waterDataResponseDto -> new ResponseEntity<>(waterDataResponseDto, HttpStatus.CREATED));
    }

    @GetMapping
    public Mono<ResponseEntity<PageWaterDataResponseDto>> getWaterData(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            ServerWebExchange exchange
    ) {
        Pageable pageable = PaginationUtils.buildPageable(page, size);
        return waterDataService.getAllWaterData(pageable)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<WaterDataResponseDto>> updateWaterData(
            @PathVariable UUID id,
            @RequestBody @Valid Mono<UpdateWaterDataRequestDto> waterDataRequestDtoMono,
            ServerWebExchange exchange) {
        return waterDataRequestDtoMono.flatMap(
                        waterDataRequestDto -> waterDataService.updateWaterData(id, waterDataRequestDto))
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteWaterData(
            @PathVariable UUID id,
            ServerWebExchange exchange) {
        return waterDataService.removeWaterData(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
