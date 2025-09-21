package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.RegionNotFoundException;
import com.example.eco_map.api.exception.WaterDataNotFoundException;
import com.example.eco_map.api.exception.WaterDataUpdateException;
import com.example.eco_map.config.properties.GeoSimplifyProperties;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.WaterData;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.persistence.repository.WaterDataRepository;
import com.example.eco_map.usecases.WaterDataService;
import com.example.eco_map.usecases.dto.PageWaterDataResponseDto;
import com.example.eco_map.usecases.dto.UpdateWaterDataRequestDto;
import com.example.eco_map.usecases.dto.WaterDataRequestDto;
import com.example.eco_map.usecases.dto.WaterDataResponseDto;
import com.example.eco_map.usecases.dto.WaterMapDto;
import com.example.eco_map.usecases.mapper.PageMapper;
import com.example.eco_map.usecases.mapper.WaterDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WaterDataServiceImpl implements WaterDataService {
    private final WaterDataRepository waterDataRepository;
    private final Scheduler jdbcScheduler;
    private final WaterDataMapper waterDataMapper;
    private final TransactionTemplate transactionTemplate;
    private final RegionRepository regionRepository;
    private final PageMapper pageMapper;
    private final GeoSimplifyProperties geoSimplifyProperties;

    @Override
    public Mono<Optional<WaterData>> getLatestByRegion(Region region) {
        return Mono.fromCallable(() -> waterDataRepository.findFirstByRegionOrderByCreatedAt(region))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<WaterMapDto> getAllWaterDataForMap() {
        return Mono.fromCallable(() -> waterDataRepository.findLatestWaterDataWithRegion(geoSimplifyProperties.getWater()))
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<WaterDataResponseDto> addWaterData(WaterDataRequestDto dto) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    Region region = regionRepository.findById(dto.getRegionId()).orElseThrow(
                            () -> new RegionNotFoundException("Region not found: " + dto.getRegionId()));
                    WaterData waterData = waterDataMapper.toEntity(dto);
                    waterData.setRegion(region);
                    return waterDataRepository.save(waterData);
                })).subscribeOn(jdbcScheduler)
                .map(waterDataMapper::toResponseDto);
    }

    @Override
    public Mono<PageWaterDataResponseDto> getAllWaterData(Pageable pageable) {
        return Mono.fromCallable(() -> waterDataRepository
                        .findAll(pageable)
                ).subscribeOn(jdbcScheduler)
                .map(page -> {
                    Page<WaterDataResponseDto> dtoPage = page.map(waterDataMapper::toResponseDto);
                    return pageMapper.mapToPageWaterDataResponse(dtoPage);
                });
    }

    @Override
    public Mono<WaterDataResponseDto> updateWaterData(UUID id, UpdateWaterDataRequestDto dto) {
        if (!id.equals(dto.getId())) {
            throw new WaterDataUpdateException("Cannot update the ID of a water data");
        }
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    WaterData existing = waterDataRepository.findByIdWithRegion(id)
                            .orElseThrow(() -> new WaterDataNotFoundException("WaterData not found: " + id));
                    if (!existing.getRegion().getId().equals(dto.getRegionId())) {
                        throw new WaterDataUpdateException("Region ID cannot be changed during update");
                    }
                    existing.setDirtySurfaceWaterPercent(dto.getDirtySurfaceWaterPercent());
                    return waterDataRepository.save(existing);
                }))
                .subscribeOn(jdbcScheduler)
                .map(waterDataMapper::toResponseDto);
    }

    @Override
    public Mono<Void> removeWaterData(UUID id) {
        return Mono.fromCallable(() -> {
            transactionTemplate.executeWithoutResult(status ->
                    waterDataRepository.findById(id)
                            .ifPresent(waterDataRepository::delete)
            );
            return null;
        }).subscribeOn(jdbcScheduler).then();
    }
}
