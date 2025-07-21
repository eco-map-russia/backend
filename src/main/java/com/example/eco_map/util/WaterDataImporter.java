package com.example.eco_map.util;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.WaterData;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.persistence.repository.WaterDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class WaterDataImporter extends AbstractCsvImporter<WaterData> {

    private static final Integer REGION_INDEX = 4;
    private static final Integer VALUE_INDEX = 9;

    private final WaterDataRepository waterDataRepository;
    private final RegionRepository regionRepository;
    private final PathProperties pathProperties;

    private Map<String, Region> regionMap;

    public void importWaterData() {
        this.regionMap = regionRepository.findAll().stream()
                .collect(Collectors.toMap(r -> r.getName().toLowerCase(), Function.identity()));

        importLines(this::parseLine, pathProperties.getWaterDataFile());
    }

    private WaterData parseLine(String[] line) {
        String regionName = line[REGION_INDEX].trim().toLowerCase();
        Region region = regionMap.get(regionName);

        if (region == null) {
            log.error("Region not found: {} ", regionName);
            throw new IllegalArgumentException("Region not found: " + regionName);
        }

        Double value = Double.parseDouble(line[VALUE_INDEX].replace(",", "."));

        WaterData data = new WaterData();
        data.setRegion(region);
        data.setDirtySurfaceWaterPercent(value);

        return data;
    }

    @Override
    @Transactional
    protected void saveAll(List<WaterData> records) {
        waterDataRepository.saveAll(records);
    }
}
