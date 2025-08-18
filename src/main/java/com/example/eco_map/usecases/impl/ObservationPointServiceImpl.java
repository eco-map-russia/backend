package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.AirQualityNotFoundException;
import com.example.eco_map.api.exception.CityNotFoundException;
import com.example.eco_map.api.exception.ObservationPointNotFound;
import com.example.eco_map.api.exception.ObservationPointUpdateException;
import com.example.eco_map.api.exception.RadiationNotFoundException;
import com.example.eco_map.persistence.model.City;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.CityRepository;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.usecases.AirQualityDataService;
import com.example.eco_map.usecases.ObservationPointService;
import com.example.eco_map.usecases.RadiationDataService;
import com.example.eco_map.usecases.dto.AirQualityHistoricalObservationDto;
import com.example.eco_map.usecases.dto.AirQualityObservationDto;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.ObservationPointRequestDto;
import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import com.example.eco_map.usecases.dto.PageObservationPointResponseDto;
import com.example.eco_map.usecases.dto.RadiationDataObservationDto;
import com.example.eco_map.usecases.dto.UpdateObservationPointRequestDto;
import com.example.eco_map.usecases.mapper.AirQualityMapper;
import com.example.eco_map.usecases.mapper.ObservationPointMapper;
import com.example.eco_map.usecases.mapper.PageMapper;
import com.example.eco_map.usecases.mapper.RadiationMapper;
import com.example.eco_map.util.GeometryUtils;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObservationPointServiceImpl implements ObservationPointService {
    private final ObservationPointRepository observationPointRepository;
    private final AirQualityDataService airQualityDataService;
    private final AirQualityMapper airQualityMapper;
    private final Scheduler jdbcScheduler;
    private final RadiationDataService radiationDataService;
    private final RadiationMapper radiationMapper;
    private final CityRepository cityRepository;
    private final TransactionTemplate transactionTemplate;
    private final ObservationPointMapper observationPointMapper;
    private final PageMapper pageMapper;
    private final GeometryUtils geometryUtils;

    @Override
    public Mono<AirQualityObservationDto> getLatestAirQualityDataByCoordinates(Double lat, Double lon) {
        return findNearestObservationPoint(lat, lon)
                .flatMap(point -> airQualityDataService.getLatestByCoordinates(point))
                .flatMap(data -> {

                    AirQualityObservationDto dto = new AirQualityObservationDto();
                    dto.setCoordinatesResponseDto(new CoordinatesResponseDto().lat(lat).lon(lon));
                    dto.setAirQualityData(airQualityMapper.entityToDto(data));
                    return Mono.just(dto);
                })
                .switchIfEmpty(Mono.error(new AirQualityNotFoundException("No air quality data found for this point")));
    }

    @Override
    public Mono<ObservationPoint> findNearestObservationPoint(double lat, double lon) {
        return Mono.fromCallable(() -> {
            Point point = geometryUtils.createPoint(lon, lat);
            return observationPointRepository.findNearestPoint(point)
                    .orElseThrow(() -> new ObservationPointNotFound("Observation point not found"));
        }).subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<AirQualityHistoricalObservationDto> getHistoricalDataForObservationPoint(
            double lat,
            double lon,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("StartDate must be before endDate");
        }


        return findNearestObservationPoint(lat, lon)
                .flatMapMany(point -> airQualityDataService.getHistoricalAirQualityData(point, startDate, endDate))
                .map(airQualityDto -> {
                    AirQualityHistoricalObservationDto dto = new AirQualityHistoricalObservationDto();
                    dto.setCoordinatesResponseDto(new CoordinatesResponseDto().lat(lat).lon(lon));
                    dto.setAirQualityHistoricalResponseDto(airQualityDto);
                    return dto;
                });
    }

    @Override
    public Mono<RadiationDataObservationDto> getRadiationDataByCoordinates(double lat, double lon) {
        return findNearestObservationPoint(lat, lon)
                .flatMap(point -> radiationDataService.findRadiationData(point))
                .flatMap(data -> {
                    RadiationDataObservationDto dto = new RadiationDataObservationDto();
                    dto.setCoordinatesResponseDto(new CoordinatesResponseDto().lat(lat).lon(lon));
                    dto.setRadiationDataDto(radiationMapper.toDto(data));
                    return Mono.just(dto);
                })
                .switchIfEmpty(Mono.error(new RadiationNotFoundException("Radiation data not found for this point")));
    }

    @Override
    public Mono<ObservationPointResponseDto> addObservationPoint(ObservationPointRequestDto dto) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    City city = cityRepository.findById(dto.getCityId()).orElseThrow(
                            () -> new CityNotFoundException("City not found: " + dto.getCityId()));

                    ObservationPoint point = observationPointMapper.toEntity(dto);
                    Point coordinates = geometryUtils.createPoint(dto.getLongitude(), dto.getLatitude());
                    point.setCity(city);
                    point.setCoordinates(coordinates);
                    return observationPointRepository.save(point);
                })).subscribeOn(jdbcScheduler)
                .map(observationPointMapper::toResponseDto);
    }

    @Override
    public Mono<PageObservationPointResponseDto> getAllObservationPoints(Pageable pageable) {
        return Mono.fromCallable(() -> observationPointRepository
                        .findAllBy(pageable)
                ).subscribeOn(jdbcScheduler)
                .map(page -> {
                    Page<ObservationPointResponseDto> dtoPage = page.map(observationPointMapper::toResponseDto);
                    return pageMapper.mapToPageObservationPointResponse(dtoPage);
                });
    }

    @Override
    public Mono<ObservationPointResponseDto> updateObservationPoint(UUID id, UpdateObservationPointRequestDto dto) {
        if (!id.equals(dto.getId())) {
            throw new ObservationPointUpdateException("Cannot update the ID of a observation point data");
        }
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    ObservationPoint existing = observationPointRepository.findByIdWithCity(id)
                            .orElseThrow(() -> new ObservationPointNotFound("Observation point not found: " + id));
                    if (!existing.getCity().getId().equals(dto.getCityId())) {
                        throw new ObservationPointUpdateException("City ID cannot be changed during update");
                    }
                    existing.setName(dto.getName());
                    Point coordinates = geometryUtils.createPoint(dto.getLongitude(), dto.getLatitude());
                    existing.setCoordinates(coordinates);

                    return observationPointRepository.save(existing);
                }))
                .subscribeOn(jdbcScheduler)
                .map(observationPointMapper::toResponseDto);
    }

    @Override
    public Mono<Void> deleteObservationPoint(UUID id) {
        return Mono.fromCallable(() -> {
            transactionTemplate.executeWithoutResult(status ->
                    observationPointRepository.findById(id)
                            .ifPresent(observationPointRepository::delete)
            );
            return null;
        }).subscribeOn(jdbcScheduler).then();
    }
}
