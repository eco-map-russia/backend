package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.User;
import com.example.eco_map.usecases.dto.FavoriteRegionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FavoriteRegionsService {
    Mono<FavoriteRegionResponseDto> addFavoriteRegion(User user, UUID regionId);

    Mono<Page<FavoriteRegionResponseDto>> getAllFavoriteRegions(UUID userId, Pageable pageable);

    Mono<Void> removeFavoriteRegion(UUID id);
}
