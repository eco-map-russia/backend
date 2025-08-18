package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.ObservationPointNotFound;
import com.example.eco_map.api.exception.RadiationDataUpdateException;
import com.example.eco_map.api.exception.RadiationNotFoundException;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.RadiationData;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.persistence.repository.RadiationDataRepository;
import com.example.eco_map.usecases.RadiationDataService;
import com.example.eco_map.usecases.dto.PageRadiationDataResponseDto;
import com.example.eco_map.usecases.dto.RadiationDataRequestDto;
import com.example.eco_map.usecases.dto.RadiationDataResponseDto;
import com.example.eco_map.usecases.dto.RadiationMapDto;
import com.example.eco_map.usecases.dto.UpdateRadiationDataRequestDto;
import com.example.eco_map.usecases.mapper.PageMapper;
import com.example.eco_map.usecases.mapper.RadiationMapper;
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
public class RadiationDataServiceImpl implements RadiationDataService {
    private final RadiationDataRepository radiationDataRepository;
    private final Scheduler jdbcScheduler;
    private final RadiationMapper radiationMapper;
    private final ObservationPointRepository observationPointRepository;
    private final TransactionTemplate transactionTemplate;
    private final PageMapper pageMapper;

    @Override
    public Mono<RadiationData> findRadiationData(ObservationPoint point) {
        return Mono.fromCallable(() -> radiationDataRepository.findTopByObservationPointOrderByCreatedAtDesc(point))
                .flatMap(Mono::justOrEmpty)
                .subscribeOn(jdbcScheduler);

    }

    @Override
    public Flux<RadiationMapDto> getAllRadiationDataForMap() {
        return Mono.fromCallable(radiationDataRepository::findLatestRadiationDataWithPoint)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler)
                .map(radiationMapper::toMapDto);
    }

    @Override
    public Mono<RadiationDataResponseDto> addRadiationData(RadiationDataRequestDto dto) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    ObservationPoint point = observationPointRepository.findById(dto.getPointId()).orElseThrow(
                            () -> new ObservationPointNotFound("Observation point not found: " + dto.getPointId()));
                    RadiationData radiationData = radiationMapper.toEntity(dto);
                    radiationData.setObservationPoint(point);
                    return radiationDataRepository.save(radiationData);
                })).subscribeOn(jdbcScheduler)
                .map(radiationMapper::toResponseDto);
    }

    @Override
    public Mono<RadiationDataResponseDto> updateRadiationData(UUID id, UpdateRadiationDataRequestDto dto) {
        if (!id.equals(dto.getId())) {
            throw new RadiationDataUpdateException("Cannot update the ID of a radiation data");
        }
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    RadiationData existing = radiationDataRepository.findByIdWithObservationPoint(id)
                            .orElseThrow(() -> new RadiationNotFoundException("Radiation not found: " + id));
                    if (!existing.getObservationPoint().getId().equals(dto.getPointId())) {
                        throw new RadiationDataUpdateException("Observation point ID cannot be changed during update");
                    }
                    existing.setBetaFallout(dto.getBetaFallout());
                    return radiationDataRepository.save(existing);
                }))
                .subscribeOn(jdbcScheduler)
                .map(radiationMapper::toResponseDto);
    }

    @Override
    public Mono<PageRadiationDataResponseDto> getAllRadiationData(Pageable pageable) {
        return Mono.fromCallable(() -> radiationDataRepository
                        .findAll(pageable)
                ).subscribeOn(jdbcScheduler)
                .map(page -> {
                    Page<RadiationDataResponseDto> dtoPage = page.map(radiationMapper::toResponseDto);
                    return pageMapper.mapToPageRadiationDataResponse(dtoPage);
                });
    }

    @Override
    public Mono<Void> removeRadiationData(UUID id) {
        return Mono.fromCallable(() -> {
            transactionTemplate.executeWithoutResult(status ->
                    radiationDataRepository.findById(id)
                            .ifPresent(radiationDataRepository::delete)
            );
            return null;
        }).subscribeOn(jdbcScheduler).then();
    }
}
