package com.example.eco_map.util;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonObservationPointImporter {
    private static final Integer LAT_INDEX = 20;
    private static final Integer LON_INDEX = 21;
    private final PathProperties pathProperties;
    private final GeometryFactory geometryFactory;


    public List<ObservationPoint> importObservationPoints() throws Exception {
        List<ObservationPoint> points = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(pathProperties.getPointsFile()))) {
            String[] header = reader.readNext();

            String[] line;

            while ((line = reader.readNext()) != null) {
                try {
                    ObservationPoint point = parseLineToObservationPoint(line);
                    if (point != null) {
                        points.add(point);
                    }
                } catch (Exception e) {
                    log.warn("Error while parsing the line");
                }

            }
        }

        return points;
    }

    private ObservationPoint parseLineToObservationPoint(String[] line) {
        double lat = Double.parseDouble(line[LAT_INDEX]);
        double lon = Double.parseDouble(line[LON_INDEX]);

        Point coordinates = geometryFactory.createPoint(new Coordinate(lon, lat));
        ObservationPoint point = new ObservationPoint();
        point.setCoordinates(coordinates);
        return point;
    }
}