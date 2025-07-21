package com.example.eco_map.util;

import com.example.eco_map.persistence.model.City;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.CityRepository;
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
    private final CityRepository cityRepository;


    @Transactional(readOnly = true)
    public List<ObservationPoint> assignCitiesToPoints(List<ObservationPoint> points) {

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

        return points;
    }
}
