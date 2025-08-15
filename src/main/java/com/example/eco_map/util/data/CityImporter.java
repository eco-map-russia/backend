package com.example.eco_map.util.data;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.City;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.usecases.CityService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class CityImporter {
    private final CityService cityService;
    private final RegionRepository regionRepository;
    private final GeoJsonReader geoJsonReader;
    private final PathProperties pathProperties;
    private final ResourceLoader resourceLoader;
    private static final String FIELD_FEATURES = "features";
    private static final String FIELD_PROPERTIES = "properties";
    private static final String FIELD_REGION = "region";
    private static final String FIELD_GEOMETRY = "geometry";
    private static final String FIELD_CITY = "city";
    private final JsonFactory jsonFactory;
    private final ObjectMapper objectMapper;

    private Map<String, Region> regionMap;

    public void importCities() throws IOException, ParseException {
        this.regionMap = regionRepository.findAll().stream()
                .collect(Collectors.toMap(
                        r -> normalizeRegionName(r.getName()),
                        Function.identity()
                ));
        Resource resource = resourceLoader.getResource(pathProperties.getCitiesFile());
        try (InputStream is = resource.getInputStream()) {
            JsonParser parser = jsonFactory.createParser(is);

            while (!parser.isClosed()) {
                JsonToken token = parser.nextToken();

                if (JsonToken.FIELD_NAME.equals(token) && FIELD_FEATURES.equals(parser.currentName())) {
                    if (parser.nextToken() == JsonToken.START_ARRAY) {
                        parseFeatures(parser);
                    }
                }
            }

        }
    }

    private void parseFeatures(JsonParser parser) throws IOException, ParseException {
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            parseFeature(parser);
        }
    }

    private void parseFeature(JsonParser parser) throws IOException, ParseException {
        String regionName = null;
        String geoJson = null;
        String cityName = null;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String currentFieldName = parser.currentName();
            parser.nextToken();
            if (FIELD_PROPERTIES.equals(currentFieldName)) {
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String currentPropertyName = parser.currentName();
                    parser.nextToken();
                    if (FIELD_REGION.equals(currentPropertyName)) {
                        regionName = parser.getValueAsString();
                    } else if (FIELD_CITY.equals(currentPropertyName)) {
                        cityName = parser.getValueAsString();
                    } else {
                        parser.skipChildren();
                    }
                }
            } else if (FIELD_GEOMETRY.equals(currentFieldName)) {
                geoJson = extractCurrentObjectAsJson(parser);
            } else {
                parser.skipChildren();
            }
        }
        if (regionName != null && geoJson != null && cityName != null) {
            processCity(cityName, regionName, geoJson);
        }
    }

    private String extractCurrentObjectAsJson(JsonParser parser) throws IOException {
        return objectMapper.writeValueAsString(parser.readValueAs(Object.class));
    }


    public void processCity(String cityName, String regionName, String geoJson) throws ParseException {

        Region region = regionMap.get(normalizeRegionName(regionName));

        if (region == null) {
            log.error("Region not found: {} ", regionName);
            throw new IllegalArgumentException("Region not found: " + regionName);
        }

        Geometry geom = geoJsonReader.read(geoJson);
        Polygon polygon = (Polygon) geom;

        Point center = polygon.getCentroid();
        center.setSRID(4326);

        City city = new City();
        city.setName(cityName);
        city.setRegion(region);
        city.setCenter(center);
        city.setGeom(polygon);
        cityService.save(city);
    }
    private String normalizeRegionName(String name) {
        return name.toLowerCase()
                .replace('–', '-')
                .replace('—', '-')
                .replace('‑', '-')
                .replace('−', '-')
                .replaceAll("\\s+", " ")
                .trim();
    }

}