package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.RegionNotFoundException;
import com.example.eco_map.api.exception.SoilDataNotFoundException;
import com.example.eco_map.api.exception.SoilDataUpdateException;
import com.example.eco_map.config.properties.GeoSimplifyProperties;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.SoilData;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.persistence.repository.SoilDataRepository;
import com.example.eco_map.usecases.SoilDataService;
import com.example.eco_map.usecases.dto.PageSoilDataResponseDto;
import com.example.eco_map.usecases.dto.SoilDataRequestDto;
import com.example.eco_map.usecases.dto.SoilDataResponseDto;
import com.example.eco_map.usecases.dto.SoilMapDto;
import com.example.eco_map.usecases.dto.UpdateSoilDataRequestDto;
import com.example.eco_map.usecases.mapper.PageMapper;
import com.example.eco_map.usecases.mapper.SoilDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SoilDataServiceImpl implements SoilDataService {
    private final SoilDataRepository soilDataRepository;
    private final Scheduler jdbcScheduler;
    private final SoilDataMapper soilDataMapper;
    private final RegionRepository regionRepository;
    private final PageMapper pageMapper;
    private final TransactionTemplate transactionTemplate;
    private final GeoSimplifyProperties simplifyProperties;

    @Override
    public Mono<SoilData> getLatestByRegion(Region region) {
        return Mono.fromCallable(() -> soilDataRepository.findFirstByRegionOrderByCreatedAtDesc(region))
                .flatMap(Mono::justOrEmpty)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<SoilMapDto> getAllSoilDataForMap() {
        return Mono.fromCallable(() -> soilDataRepository.findLatestSoilDataWithRegion(simplifyProperties.getSoil()))
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<SoilDataResponseDto> addSoilData(SoilDataRequestDto dto) {

        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    Region region = regionRepository.findById(dto.getRegionId()).orElseThrow(
                            () -> new RegionNotFoundException("Region not found: " + dto.getRegionId()));
                    SoilData soilData = soilDataMapper.toEntity(dto);
                    soilData.setRegion(region);
                    return soilDataRepository.save(soilData);
                })).subscribeOn(jdbcScheduler)
                .map(soilDataMapper::toResponseDto);
    }

    @Override
    public Mono<PageSoilDataResponseDto> getSoilData(Pageable pageable) {
        return Mono.fromCallable(() -> soilDataRepository
                        .findAll(pageable)
                ).subscribeOn(jdbcScheduler)
                .map(page -> {
                    Page<SoilDataResponseDto> dtoPage = page.map(soilDataMapper::toResponseDto);
                    return pageMapper.mapToPageSoilDataResponses(dtoPage);
                });
    }

    @Override
    public Mono<SoilDataResponseDto> updateSoilData(UUID id, UpdateSoilDataRequestDto dto) {

        if (!id.equals(dto.getId())) {
            throw new SoilDataUpdateException("Cannot update the ID of a soil data");
        }
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    SoilData existing = soilDataRepository.findByIdWithRegion(id)
                            .orElseThrow(() -> new SoilDataNotFoundException("SoilData not found: " + id));
                    if (!existing.getRegion().getId().equals(dto.getRegionId())) {
                        throw new SoilDataUpdateException("Region ID cannot be changed during update");
                    }
                    existing.setLandDegradationNeutralityIndex(dto.getLandDegradationNeutralityIndex());
                    existing.setChronicSoilPollutionPercent(dto.getChronicSoilPollutionPercent());
                    return soilDataRepository.save(existing);
                }))
                .subscribeOn(jdbcScheduler)
                .map(soilDataMapper::toResponseDto);

    }

    @Override
    public Mono<Void> removeSoilData(UUID id) {
        return Mono.fromCallable(() -> {
            transactionTemplate.executeWithoutResult(status ->
                    soilDataRepository.findById(id)
                            .ifPresent(soilDataRepository::delete)
            );
            return null;
        }).subscribeOn(jdbcScheduler).then();
    }
}
