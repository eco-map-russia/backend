package com.example.eco_map.util;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.persistence.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssignationRegions {
    private final RegionRepository regionRepository;
    private final ObservationPointRepository observationPointRepository;
    private final JsonObservationPointImporter jsonObservationPointImporter;

    public void assignRegionsToPoints() throws Exception {

        List<ObservationPoint> points=jsonObservationPointImporter.importObservationPoints();
        for (ObservationPoint point : points) {
            Region region = regionRepository.findRegionByPoint(point.getCoordinates());
            if (region != null) {
                point.setRegion(region);
            }
        }
        observationPointRepository.saveAll(points);
    }
}
