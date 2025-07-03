package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.AirQualityDataRepository;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.usecases.ObservationPointService;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import com.example.eco_map.usecases.mapper.AirQualityMapper;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObservationPointServiceImpl implements ObservationPointService {
    private final ObservationPointRepository observationPointRepository;
    private final AirQualityDataRepository airQualityDataRepository;
    private final AirQualityMapper airQualityMapper;

    @Override
    public ObservationPointResponseDto getLatestDataByCoordinates(Double lat, Double lon) {
        Point point = createPoint(lon, lat);
        ObservationPoint nearestPoint = observationPointRepository.findNearestPoint(point)
                .orElseThrow(() -> new RuntimeException("Observation point not found"));
        ObservationPointResponseDto dto = new ObservationPointResponseDto();
        dto.setCoordinates(new CoordinatesResponseDto(lat, lon)); // Или nearestPoint?? что лучше

        airQualityDataRepository.findLatestByObservationPoint(nearestPoint)
                .ifPresent(data -> dto.setAirQualityData(airQualityMapper.entityToDto(data)));

        return dto;
    }

    private Point createPoint(double lon, double lat) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(new Coordinate(lon, lat));
    }
}
