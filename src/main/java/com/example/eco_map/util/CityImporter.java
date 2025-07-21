package com.example.eco_map.util;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.City;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.repository.CityRepository;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CityImporter {
    private final CityRepository cityRepository;
    private final RegionRepository regionRepository;
    private final ObjectMapper objectMapper;
    private final GeoJsonReader geoJsonReader;
    private final PathProperties pathProperties;

    @Transactional
    public List<City> importCities() throws IOException, ParseException {
        List<City> cities = new ArrayList<>();

        File file = new File(pathProperties.getCitiesFile());
        String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        JsonNode root = objectMapper.readTree(content);
        JsonNode features = root.get("features");

        for (JsonNode feature : features) {
            String cityName = feature.at("/properties/city").asText();
            String regionName = feature.at("/properties/region").asText();

            Region region = regionRepository.findByRegionName(regionName)
                    .orElseThrow(() -> new IllegalArgumentException("Region not found: " + regionName));

            Polygon polygon = (Polygon) geoJsonReader.read(feature.get("geometry").toString());

            Point center = polygon.getCentroid();
            center.setSRID(4326);

            City city = new City();
            city.setName(cityName);
            city.setRegion(region);
            city.setCenter(center);
            city.setGeom(polygon);
            cities.add(city);
        }

        return cityRepository.saveAll(cities);


    }
}
