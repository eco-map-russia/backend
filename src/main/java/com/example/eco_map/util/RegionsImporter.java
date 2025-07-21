package com.example.eco_map.util;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
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
import java.util.Optional;


@Component
@RequiredArgsConstructor
@Slf4j

public class RegionsImporter {

    private final RegionRepository regionRepository;
    private final ObjectMapper objectMapper;
    private final GeoJsonReader geoJsonReader;
    private final PathProperties pathProperties;

    @Transactional
    public void importRegions() throws IOException, ParseException {

        File file = new File(pathProperties.getRegionsFile());
        String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);

        JsonNode root = objectMapper.readTree(content);
        JsonNode features = root.get("features");
        List<Region> regions = new ArrayList<>();

        for (JsonNode feature : features) {
            String regionName = Optional.ofNullable(feature.get("properties"))
                    .map(p -> p.get("region"))
                    .map(JsonNode::asText)
                    .orElseThrow(() -> new IllegalArgumentException("Failed to parse feature: " + feature));
            JsonNode geometryNode = feature.get("geometry");
            String geomJson = geometryNode.toString();
            Geometry geom = geoJsonReader.read(geomJson);
            MultiPolygon multiPolygon = (MultiPolygon) geom;
            Point center = multiPolygon.getCentroid();
            center.setSRID(4326);
            Region region = new Region();
            region.setName(regionName);
            region.setGeom(multiPolygon);
            region.setCenter(center);

            regions.add(region);
        }
        regionRepository.saveAll(regions);

    }


}