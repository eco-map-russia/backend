package com.example.eco_map.util;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ObservationPointImporter extends AbstractCsvImporter<ObservationPoint> {
    private static final Integer LAT_INDEX = 20;
    private static final Integer LON_INDEX = 21;
    private static final Integer OBSERVATION_POINT_NAME = 9;
    private final PathProperties pathProperties;
    private final GeometryFactory geometryFactory;
    private final AssignationService assignationService;
    private final ObservationPointRepository observationPointRepository;

    public void importObservationPoint() {
        importLines(this::parseLine, pathProperties.getPointsFile());
    }

    private ObservationPoint parseLine(String[] line) {
        double lat = Double.parseDouble(line[LAT_INDEX]);
        double lon = Double.parseDouble(line[LON_INDEX]);
        String name = line[OBSERVATION_POINT_NAME];
        Point coordinates = geometryFactory.createPoint(new Coordinate(lon, lat));
        ObservationPoint point = new ObservationPoint();
        point.setCoordinates(coordinates);
        point.setName(name);
        return point;
    }

    @Override
    @Transactional
    protected void saveAll(List<ObservationPoint> records) {
        List<ObservationPoint> points = assignationService.assignCitiesToPoints(records);
        observationPointRepository.saveAll(points);
    }
}