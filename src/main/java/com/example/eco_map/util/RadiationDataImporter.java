package com.example.eco_map.util;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.RadiationData;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.persistence.repository.RadiationDataRepository;
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
public class RadiationDataImporter extends AbstractCsvImporter<RadiationData> {
    private static final Integer LAT_INDEX = 1;
    private static final Integer LON_INDEX = 2;
    private static final Integer BETA_FALLOUT_INDEX = 3;
    private static final Integer OBSERVATION_POINT_NAME = 0;
    private final PathProperties pathProperties;
    private final GeometryFactory geometryFactory;
    private final AssignationService assignationService;
    private final ObservationPointRepository observationPointRepository;
    private final RadiationDataRepository radiationDataRepository;

    public void importRadiationData() {
        importLines(this::parseLine, pathProperties.getRadiationDataFile());
    }

    private RadiationData parseLine(String[] line) {
        double lat = Double.parseDouble(line[LAT_INDEX]);
        double lon = Double.parseDouble(line[LON_INDEX]);
        String name = line[OBSERVATION_POINT_NAME];
        Point coordinates = geometryFactory.createPoint(new Coordinate(lon, lat));
        ObservationPoint point = new ObservationPoint();
        point.setName(name);
        point.setCoordinates(coordinates);
        RadiationData radiationData = new RadiationData();
        radiationData.setBetaFallout(Double.parseDouble(line[BETA_FALLOUT_INDEX]));
        radiationData.setObservationPoint(point);
        return radiationData;
    }

    @Override
    @Transactional
    protected void saveAll(List<RadiationData> records) {
        List<ObservationPoint> points = records.stream()
                .map(RadiationData::getObservationPoint)
                .toList();

        List<ObservationPoint> pointsWithCities = assignationService.assignCitiesToPoints(points);

        observationPointRepository.saveAll(pointsWithCities);
        log.info("saved " + points.size() + " points");
        radiationDataRepository.saveAll(records);
        log.info("saved " + points.size() + " points");
    }
}
