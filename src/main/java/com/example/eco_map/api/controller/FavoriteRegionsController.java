package com.example.eco_map.api.controller;

import com.example.eco_map.api.FavoriteRegionsApi;
import com.example.eco_map.security.CurrentUserService;
import com.example.eco_map.usecases.FavoriteRegionsService;
import com.example.eco_map.usecases.dto.FavoriteRegionResponseDto;
import com.example.eco_map.usecases.dto.PageFavoriteRegionResponseDto;
import com.example.eco_map.util.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite-regions")
public class FavoriteRegionsController implements FavoriteRegionsApi {
    private final FavoriteRegionsService favoriteRegionsService;
    private final CurrentUserService currentUserService;

    @PostMapping("/{regionId}")
    @Override
    public Mono<ResponseEntity<FavoriteRegionResponseDto>> addFavoriteRegion(
            @PathVariable UUID regionId,
            ServerWebExchange exchange) {

        return currentUserService.getCurrentUser()
                .flatMap(current -> favoriteRegionsService.addFavoriteRegion(current, regionId))
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    @Override
    public Mono<ResponseEntity<Void>> removeFavoriteRegion(
            @PathVariable UUID id,
            ServerWebExchange exchange) {
        return favoriteRegionsService.removeFavoriteRegion(id)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping
    @Override
    public Mono<ResponseEntity<PageFavoriteRegionResponseDto>> getAllFavoriteRegions(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            ServerWebExchange exchange) {
        Pageable pageable = PaginationUtils.buildPageable(page, size);
        return currentUserService.getCurrentUser()
                .flatMap(current ->
                        favoriteRegionsService.getAllFavoriteRegions(current.getId(), pageable))
                .map(ResponseEntity::ok);
    }

}
