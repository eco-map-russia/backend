package com.example.eco_map.api.controller;

import com.example.eco_map.api.AdminRadiationDataApi;
import com.example.eco_map.usecases.RadiationDataService;
import com.example.eco_map.usecases.dto.PageRadiationDataResponseDto;
import com.example.eco_map.usecases.dto.RadiationDataRequestDto;
import com.example.eco_map.usecases.dto.RadiationDataResponseDto;
import com.example.eco_map.usecases.dto.UpdateRadiationDataRequestDto;
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
@RequestMapping("api/v1/admin/radiation")
@RequiredArgsConstructor
public class AdminRadiationDataController implements AdminRadiationDataApi {
    private final RadiationDataService radiationDataService;

    @PostMapping
    public Mono<ResponseEntity<RadiationDataResponseDto>> addRadiationData(
            @RequestBody @Valid Mono<RadiationDataRequestDto> radiationDataRequestDtoMono,
            ServerWebExchange exchange) {
        return radiationDataRequestDtoMono
                .flatMap(radiationDataService::addRadiationData)
                .map(data -> new ResponseEntity<>(data, HttpStatus.CREATED));
    }

    @GetMapping
    public Mono<ResponseEntity<PageRadiationDataResponseDto>> getRadiation(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            ServerWebExchange exchange) {
        Pageable pageable = PaginationUtils.buildPageable(page, size);
        return radiationDataService.getAllRadiationData(pageable)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<RadiationDataResponseDto>> updateRadiationData(
            @PathVariable UUID id,
            @RequestBody @Valid Mono<UpdateRadiationDataRequestDto> radiationDataDtoMono,
            ServerWebExchange exchange) {
        return radiationDataDtoMono
                .flatMap(dto -> radiationDataService.updateRadiationData(id, dto))
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteRadiationData(
            @PathVariable UUID id,
            ServerWebExchange exchange) {
        return radiationDataService.removeRadiationData(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
