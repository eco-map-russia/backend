package com.example.eco_map.util.data;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.RegionService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Component
@RequiredArgsConstructor
@Slf4j
public class RegionsImporter {

    private final RegionService regionService;
    private final GeoJsonReader geoJsonReader;
    private final JsonFactory jsonFactory;
    private final PathProperties pathProperties;
    private final ResourceLoader resourceLoader;
    private static final String FIELD_FEATURES = "features";
    private static final String FIELD_PROPERTIES = "properties";
    private static final String FIELD_REGION = "region";
    private static final String FIELD_GEOMETRY = "geometry";
    private final ObjectMapper objectMapper;

    public void importRegions() throws Exception {
        Resource resource = resourceLoader.getResource(pathProperties.getRegionsFile());
        try (InputStream is = resource.getInputStream();
             JsonParser parser = jsonFactory.createParser(is)) {

            while (!parser.isClosed()) {
                JsonToken token = parser.nextToken();

                if (JsonToken.FIELD_NAME.equals(token) && FIELD_FEATURES.equals(parser.currentName())) {
                    if (parser.nextToken() == JsonToken.START_ARRAY) {
                        readFeatures(parser);
                    }
                }
            }
        }


    }

    private void readFeatures(JsonParser parser) throws Exception {
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            parseFeature(parser);
        }
    }

    private void parseFeature(JsonParser parser) throws IOException, ParseException {
        String regionName = null;
        String geometryJson = null;

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.currentName();
            parser.nextToken();

            if (FIELD_PROPERTIES.equals(fieldName)) {
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String propName = parser.currentName();
                    parser.nextToken();
                    if (FIELD_REGION.equals(propName)) {
                        regionName = parser.getValueAsString();
                    } else {
                        parser.skipChildren();
                    }
                }
            } else if (FIELD_GEOMETRY.equals(fieldName)) {
                geometryJson = extractCurrentObjectAsJson(parser);
            } else {
                parser.skipChildren();
            }
        }

        if (regionName != null && geometryJson != null) {
            processRegion(regionName, geometryJson);
        }
    }

    private String extractCurrentObjectAsJson(JsonParser parser) throws IOException {
        return objectMapper.writeValueAsString(parser.readValueAs(Object.class));
    }

    private void processRegion(String name, String geometryJson) throws ParseException {
        Geometry geom = geoJsonReader.read(geometryJson);
        MultiPolygon polygon = (MultiPolygon) geom;
        Point center = polygon.getCentroid();
        center.setSRID(4326);

        Region region = new Region();
        region.setName(name);
        region.setGeom(polygon);
        region.setCenter(center);

        regionService.saveRegion(region);
    }

}