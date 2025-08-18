package com.example.eco_map.api.controller;

import com.example.eco_map.api.AdminObservationPointApi;
import com.example.eco_map.usecases.ObservationPointService;
import com.example.eco_map.usecases.dto.ObservationPointRequestDto;
import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import com.example.eco_map.usecases.dto.PageObservationPointResponseDto;
import com.example.eco_map.usecases.dto.UpdateObservationPointRequestDto;
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
@RequestMapping("api/v1/admin/points")
@RequiredArgsConstructor
public class AdminObservationPointController implements AdminObservationPointApi {
    private final ObservationPointService observationPointService;

    @PostMapping
    public Mono<ResponseEntity<ObservationPointResponseDto>> addObservationPoint(
            @RequestBody @Valid Mono<ObservationPointRequestDto> requestDtoMono,
            ServerWebExchange exchange) {
        return requestDtoMono
                .flatMap(observationPointService::addObservationPoint)
                .map(response -> new ResponseEntity<>(response, HttpStatus.CREATED));
    }

    @GetMapping
    public Mono<ResponseEntity<PageObservationPointResponseDto>> getAllObservationPoints(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            ServerWebExchange exchange) {
        Pageable pageable = PaginationUtils.buildPageable(page, size);
        return observationPointService.getAllObservationPoints(pageable)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ObservationPointResponseDto>> updateObservationPoint(
            @PathVariable UUID id,
            @RequestBody @Valid Mono<UpdateObservationPointRequestDto> requestDtoMono,
            ServerWebExchange exchange) {
        return requestDtoMono.flatMap(dto ->
                observationPointService.updateObservationPoint(id, dto)
        ).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteObservationPoint(
            @PathVariable UUID id,
            ServerWebExchange exchange) {
        return observationPointService.deleteObservationPoint(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
