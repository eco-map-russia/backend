package com.example.eco_map.util;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.SoilData;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.persistence.repository.SoilDataRepository;
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
public class LandDegradationBalanceIndexImporter extends AbstractCsvImporter<SoilData> {

    private static final Integer REGION_INDEX = 5;
    private static final Integer VALUE_INDEX = 10;

    private final RegionRepository regionRepository;
    private final SoilDataRepository soilDataRepository;
    private final PathProperties pathProperties;
    private Map<String, Region> regionMap;

    private Map<String, SoilData> soilDataMap;


    public void importDegradationBalanceIndex() {
        this.regionMap = regionRepository.findAll().stream()
                .collect(Collectors.toMap(r -> r.getName().toLowerCase(), Function.identity()));
        this.soilDataMap = soilDataRepository.findAllWithRegion().stream()
                .collect(Collectors.toMap(
                        data -> data.getRegion().getName().toLowerCase(),
                        Function.identity()
                ));
        log.info("soilDataMap.toString()" + soilDataMap.toString());
        importLines(this::parseLine, pathProperties.getLandDegradationIndexFile());
    }

    private SoilData parseLine(String[] line) {
        String regionName = line[REGION_INDEX].trim().toLowerCase();
        Region region = regionMap.get(regionName);

        if (region == null) {
            log.error("Region not found: {} ", regionName);
            throw new IllegalArgumentException("Region not found: " + regionName);
        }
        Double value;
        String rawValue = line[VALUE_INDEX];
        if (rawValue.equals("9999")) {
            value = 28.0;
        } else {
            value = Double.parseDouble(line[VALUE_INDEX].replace(",", "."));
        }
        SoilData data = soilDataMap.get(regionName);
        if (data == null) {
            data = new SoilData();
            data.setRegion(region);
            soilDataMap.put(regionName, data);
        }
        data.setLandDegradationNeutralityIndex(value);
        return data;
    }

    @Transactional
    @Override
    protected void saveAll(List<SoilData> records) {
        soilDataRepository.saveAll(records);
    }
}
