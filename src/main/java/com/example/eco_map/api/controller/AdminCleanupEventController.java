package com.example.eco_map.api.controller;

import com.example.eco_map.api.AdminCleanupEventApi;
import com.example.eco_map.usecases.CleanupService;
import com.example.eco_map.usecases.dto.CleanupEventCreateRequestDto;
import com.example.eco_map.usecases.dto.CleanupEventResponseDto;
import com.example.eco_map.usecases.dto.CleanupEventUpdateDto;
import com.example.eco_map.usecases.dto.PageCleanupEventResponseDto;
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
@RequestMapping("/api/v1/admin/cleanup-events")
@RequiredArgsConstructor
public class AdminCleanupEventController implements AdminCleanupEventApi {
    private final CleanupService cleanupService;

    @PostMapping
    @Override
    public Mono<ResponseEntity<CleanupEventResponseDto>> addCleanupEvent(
            @RequestBody @Valid Mono<CleanupEventCreateRequestDto> requestDtoMono,
            ServerWebExchange exchange) {
        return requestDtoMono
                .flatMap(cleanupService::addCleanupEvent)
                .map(response -> new ResponseEntity<>(response, HttpStatus.CREATED));
    }

    @GetMapping
    @Override
    public Mono<ResponseEntity<PageCleanupEventResponseDto>> getCleanupEvents(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            ServerWebExchange exchange) {
        Pageable pageable = PaginationUtils.buildPageable(page, size);
        return cleanupService.getAllCleanupEvents(pageable)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    @Override
    public Mono<ResponseEntity<CleanupEventResponseDto>> updateCleanupEvent(
            @PathVariable UUID id,
            @RequestBody @Valid Mono<CleanupEventUpdateDto> requestDtoMono,
            ServerWebExchange exchange) {
        return requestDtoMono.flatMap(dto ->
                cleanupService.updateCleanupEvent(id, dto)
        ).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    @Override
    public Mono<ResponseEntity<Void>> deleteCleanupEvent(
            @PathVariable UUID id,
            ServerWebExchange exchange) {
        return cleanupService.deleteCleanupEvent(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
