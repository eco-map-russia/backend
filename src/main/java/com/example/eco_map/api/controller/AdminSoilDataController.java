package com.example.eco_map.api.controller;

import com.example.eco_map.api.AdminSoilDataApi;
import com.example.eco_map.usecases.SoilDataService;
import com.example.eco_map.usecases.dto.PageSoilDataResponseDto;
import com.example.eco_map.usecases.dto.SoilDataRequestDto;
import com.example.eco_map.usecases.dto.SoilDataResponseDto;
import com.example.eco_map.usecases.dto.UpdateSoilDataRequestDto;
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
@RequestMapping("api/v1/admin/soil")
@RequiredArgsConstructor
public class AdminSoilDataController implements AdminSoilDataApi {
    private final SoilDataService soilDataService;

    @PostMapping
    public Mono<ResponseEntity<SoilDataResponseDto>> addSoilData(
            @RequestBody @Valid Mono<SoilDataRequestDto> soilDataRequestDtoMono,
            ServerWebExchange exchange) {
        return soilDataRequestDtoMono.flatMap(
                soilDataService::addSoilData
        ).map(soilDataResponseDto -> new ResponseEntity<>(soilDataResponseDto, HttpStatus.CREATED));
    }

    @GetMapping
    public Mono<ResponseEntity<PageSoilDataResponseDto>> getSoilData(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            ServerWebExchange exchange
    ) {
        Pageable pageable = PaginationUtils.buildPageable(page, size);
        return soilDataService.getSoilData(pageable)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<SoilDataResponseDto>> updateSoilData(
            @PathVariable UUID id,
            @RequestBody @Valid Mono<UpdateSoilDataRequestDto> soilDataRequestDtoMono,
            ServerWebExchange exchange) {
        return soilDataRequestDtoMono.flatMap(
                        soilDataRequestDto -> soilDataService.updateSoilData(id, soilDataRequestDto))
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteSoilData(
            @PathVariable UUID id,
            ServerWebExchange exchange) {
        return soilDataService.removeSoilData(id)
                .thenReturn(ResponseEntity.noContent().build());
    }

}
