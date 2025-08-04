package com.example.eco_map.api.controller;

import com.example.eco_map.security.CustomUserDetails;
import com.example.eco_map.usecases.FavoriteRegionsService;
import com.example.eco_map.usecases.dto.FavoriteRegionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite-regions")
public class FavoriteRegionsController {
    private final FavoriteRegionsService favoriteRegionsService;

    @GetMapping
    public Mono<ResponseEntity<Page<FavoriteRegionResponseDto>>> getAllFavoriteRegions(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return favoriteRegionsService.getAllFavoriteRegions(user.getUser().getId(), pageable)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/{regionId}")
    public Mono<ResponseEntity<FavoriteRegionResponseDto>> addFavoriteRegion(
            @PathVariable UUID regionId,
            @AuthenticationPrincipal CustomUserDetails user) {

        return favoriteRegionsService.addFavoriteRegion(user.getUser(), regionId)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> removeFavoriteRegion(
            @PathVariable UUID id,
            @AuthenticationPrincipal CustomUserDetails user) {

        return favoriteRegionsService.removeFavoriteRegion(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
