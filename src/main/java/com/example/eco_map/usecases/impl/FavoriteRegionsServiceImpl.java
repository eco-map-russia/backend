package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.DuplicateFavoriteRegionException;
import com.example.eco_map.api.exception.RegionNotFoundException;
import com.example.eco_map.persistence.model.FavoriteRegion;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.User;
import com.example.eco_map.persistence.repository.FavoriteRegionsRepository;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.usecases.FavoriteRegionsService;
import com.example.eco_map.usecases.dto.FavoriteRegionResponseDto;
import com.example.eco_map.usecases.mapper.FavoriteRegionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteRegionsServiceImpl implements FavoriteRegionsService {
    private final FavoriteRegionsRepository favoriteRegionsRepository;
    private final RegionRepository regionRepository;
    private final Scheduler jdbcScheduler;
    private final FavoriteRegionsMapper regionsMapper;
    private final TransactionTemplate transactionTemplate;

    @Override
    public Mono<FavoriteRegionResponseDto> addFavoriteRegion(User user, UUID regionId) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {

                    Region region = regionRepository.findById(regionId)
                            .orElseThrow(() -> new RegionNotFoundException("Region not found: " + regionId));
                    if (favoriteRegionsRepository.existsByUserAndRegion(user, region)) {
                        throw new DuplicateFavoriteRegionException("Region already exists: " + regionId);
                    }
                    FavoriteRegion favoriteRegion = new FavoriteRegion();
                    favoriteRegion.setRegion(region);
                    favoriteRegion.setUser(user);

                    FavoriteRegion saved = favoriteRegionsRepository.save(favoriteRegion);
                    return saved;
                }))
                .subscribeOn(jdbcScheduler)
                .map(regionsMapper::toDto);
    }

    @Override
    public Mono<Page<FavoriteRegionResponseDto>> getAllFavoriteRegions(UUID userId, Pageable pageable) {
        return Mono.fromCallable(() -> favoriteRegionsRepository.findAllByUserId(userId, pageable))
                .subscribeOn(jdbcScheduler)
                .map(page -> page.map(regionsMapper::toDto));
    }

    @Override
    public Mono<Void> removeFavoriteRegion(UUID id) {
        return Mono.fromCallable(() -> {
            transactionTemplate.executeWithoutResult(status ->
                    favoriteRegionsRepository.findById(id)
                            .ifPresent(favoriteRegionsRepository::delete)
            );
            return null;
        }).subscribeOn(jdbcScheduler).then();
    }
}
