package com.example.eco_map.api.controller;

import com.example.eco_map.api.AdminNatureReserveApi;
import com.example.eco_map.usecases.NatureReservesService;
import com.example.eco_map.usecases.dto.NatureReserveRequestDto;
import com.example.eco_map.usecases.dto.NatureReserveResponseDto;
import com.example.eco_map.usecases.dto.PageNatureReserveResponseDto;
import com.example.eco_map.usecases.dto.UpdateNatureReserveRequestDto;
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
@RequestMapping("api/v1/admin/nature-reserve")
@RequiredArgsConstructor
public class AdminNatureReserveController implements AdminNatureReserveApi {
    private final NatureReservesService natureReservesService;

    @PostMapping
    public Mono<ResponseEntity<NatureReserveResponseDto>> addNatureReserve(
            @RequestBody @Valid Mono<NatureReserveRequestDto> requestDtoMono,
            ServerWebExchange exchange) {
        return requestDtoMono
                .flatMap(natureReservesService::addNatureReserve)
                .map(response -> new ResponseEntity<>(response, HttpStatus.CREATED));
    }

    @GetMapping
    public Mono<ResponseEntity<PageNatureReserveResponseDto>> getNatureReserves(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            ServerWebExchange exchange) {
        Pageable pageable = PaginationUtils.buildPageable(page, size);
        return natureReservesService.getAllNatureReserves(pageable)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<NatureReserveResponseDto>> updateNatureReserve(
            @PathVariable UUID id,
            @RequestBody @Valid Mono<UpdateNatureReserveRequestDto> requestDtoMono,
            ServerWebExchange exchange) {
        return requestDtoMono.flatMap(dto ->
                natureReservesService.updateNatureReserve(id, dto)
        ).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteNatureReserve(
            @PathVariable UUID id,
            ServerWebExchange exchange) {
        return natureReservesService.deleteNatureReserve(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}

