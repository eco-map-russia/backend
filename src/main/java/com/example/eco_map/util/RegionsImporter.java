package com.example.eco_map.util;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
public class RegionsImporter implements CommandLineRunner {

    private final RegionRepository regionRepository;
    private final ObjectMapper objectMapper;
    private final GeoJsonReader geoJsonReader = new GeoJsonReader();
    private final AssignationRegions assignationRegions;

    @Override
    public void run(String... args) {

        try {
            List<Region> regions = parseRegionsFromGeoJson();
            regionRepository.saveAll(regions);
            log.info("Successfully loaded {} regions", regions.size());
            assignationRegions.assignRegionsToPoints();
        } catch (Exception e) {
            log.error("Error while importing regions", e);
        }


    }

    private List<Region> parseRegionsFromGeoJson() throws IOException, ParseException {
        File file = new File("src/main/resources/russia_regions.geojson");
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
            Region region = new Region();
            region.setName(regionName);
            region.setGeom((MultiPolygon) geom);

            regions.add(region);
        }
        return regions;
    }

}