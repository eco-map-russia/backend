package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.NatureReserveNotFoundException;
import com.example.eco_map.api.exception.NatureReserveUpdateException;
import com.example.eco_map.api.exception.RegionNotFoundException;
import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.repository.NatureReservesRepository;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.usecases.NatureReservesService;
import com.example.eco_map.usecases.dto.NatureReserveRequestDto;
import com.example.eco_map.usecases.dto.NatureReserveResponseDto;
import com.example.eco_map.usecases.dto.PageNatureReserveResponseDto;
import com.example.eco_map.usecases.dto.UpdateNatureReserveRequestDto;
import com.example.eco_map.usecases.mapper.NatureReservesMapper;
import com.example.eco_map.usecases.mapper.PageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NatureReservesServiceImpl implements NatureReservesService {
    private final NatureReservesRepository natureReservesRepository;
    private final Scheduler jdbcScheduler;
    private final TransactionTemplate transactionTemplate;
    private final RegionRepository regionRepository;
    private final NatureReservesMapper natureReservesMapper;
    private final PageMapper pageMapper;

    @Override
    public Mono<List<NatureReserve>> getByRegion(Region region) {
        return Mono.fromCallable(() -> natureReservesRepository.findByRegion(region))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<NatureReserveResponseDto> addNatureReserve(NatureReserveRequestDto dto) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    Region region = regionRepository.findById(dto.getRegionId()).orElseThrow(
                            () -> new RegionNotFoundException("Region not found: " + dto.getRegionId()));
                    NatureReserve natureReserve = natureReservesMapper.toEntity(dto);
                    natureReserve.setRegion(region);
                    return natureReservesRepository.save(natureReserve);
                })).subscribeOn(jdbcScheduler)
                .map(natureReservesMapper::toResponseDto);
    }

    @Override
    public Mono<PageNatureReserveResponseDto> getAllNatureReserves(Pageable pageable) {
        return Mono.fromCallable(() -> natureReservesRepository.findAll(pageable))
                .subscribeOn(jdbcScheduler)
                .map(page -> {
                    Page<NatureReserveResponseDto> dtoPage = page.map(natureReservesMapper::toResponseDto);
                    return pageMapper.mapToPageNatureReserveResponses(dtoPage);
                });
    }

    @Override
    public Mono<NatureReserveResponseDto> updateNatureReserve(UUID id, UpdateNatureReserveRequestDto dto) {
        if (!id.equals(dto.getId())) {
            throw new NatureReserveUpdateException("Cannot change NatureReserve ID");
        }

        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    NatureReserve existing = natureReservesRepository.findByIdWithRegion(id)
                            .orElseThrow(() -> new NatureReserveNotFoundException("NatureReserve not found: " + id));

                    if (!existing.getRegion().getId().equals(dto.getRegionId())) {
                        throw new NatureReserveUpdateException("Region cannot be changed");
                    }

                    existing.setName(dto.getName());
                    existing.setArea(dto.getArea());
                    existing.setYearFounded(dto.getYearFounded());
                    existing.setDescription(dto.getDescription());
                    existing.setWebsite(dto.getWebsite());

                    return natureReservesRepository.save(existing);
                })).subscribeOn(jdbcScheduler)
                .map(natureReservesMapper::toResponseDto);
    }

    @Override
    public Mono<Void> deleteNatureReserve(UUID id) {
        return Mono.fromCallable(() -> {
            transactionTemplate.executeWithoutResult(status ->
                    natureReservesRepository.findById(id)
                            .ifPresent(natureReservesRepository::delete)
            );
            return null;
        }).subscribeOn(jdbcScheduler).then();
    }
}
