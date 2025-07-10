package com.example.eco_map.util;

import com.example.eco_map.persistence.model.City;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.CityRepository;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignationService {
    private final ObservationPointRepository pointRepository;
    private final CityRepository cityRepository;
    private final JsonObservationPointImporter observationPointImporter;


    @Transactional
    public void assignCitiesToPoints() throws Exception {
        List<ObservationPoint> points = observationPointImporter.importObservationPoints();

        for (ObservationPoint observationPoint : points) {
            Point point = observationPoint.getCoordinates();
            City matchedCity = cityRepository.findCityForPoint(point);

            if (matchedCity != null) {
                observationPoint.setCity(matchedCity);
            } else {
                log.warn("City not found for point coordinates={}", point);
                City nearestCity = cityRepository.findNearestCity(point);
                observationPoint.setCity(nearestCity);

            }
        }

        pointRepository.saveAll(points);
    }
}
