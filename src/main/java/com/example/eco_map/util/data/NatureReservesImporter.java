package com.example.eco_map.util.data;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.repository.NatureReservesRepository;
import com.example.eco_map.persistence.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class NatureReservesImporter extends AbstractCsvImporter<NatureReserve> {
    private static final Integer REGION_INDEX = 2;
    private static final Integer NAME_INDEX = 1;
    private static final Integer AREA_INDEX = 3;
    private static final Integer YEAR_FOUNDED_INDEX = 4;
    private static final Integer DESCRIPTION_INDEX = 5;
    private static final Integer WEBSITE_INDEX = 5;

    private final RegionRepository regionRepository;
    private final PathProperties pathProperties;
    private final NatureReservesRepository natureReservesRepository;

    private Map<String, Region> regionMap;

    public NatureReservesImporter(ResourceLoader resourceLoader,
                                  RegionRepository regionRepository,
                                  PathProperties pathProperties,
                                  NatureReservesRepository natureReservesRepository) {
        super(resourceLoader);
        this.regionRepository = regionRepository;
        this.pathProperties = pathProperties;
        this.natureReservesRepository = natureReservesRepository;
    }

    public void importNatureReserves() {
        this.regionMap = regionRepository.findAll().stream()
                .collect(Collectors.toMap(r -> r.getName().toLowerCase(), Function.identity()));

        importLines(this::parseLine, pathProperties.getNatureReserveFile());
    }

    private NatureReserve parseLine(String[] line) {
        String regionName = line[REGION_INDEX].trim().toLowerCase();
        Region region = regionMap.get(regionName);
        if (region == null) {
            log.error("Region not found: " + regionName);
            throw new IllegalArgumentException("Region not found: " + regionName);
        }
        String name = line[NAME_INDEX];
        Double area = (line[AREA_INDEX] != null && !line[AREA_INDEX].trim().isEmpty())
                ? Double.parseDouble(line[AREA_INDEX].trim())
                : null;

        Integer yearFounded = (line[YEAR_FOUNDED_INDEX] != null && !line[YEAR_FOUNDED_INDEX].trim().isEmpty())
                ? Integer.parseInt(line[YEAR_FOUNDED_INDEX].trim())
                : null;
        String description = line[DESCRIPTION_INDEX];
        String website = line[WEBSITE_INDEX];
        NatureReserve natureReserve = new NatureReserve();
        natureReserve.setName(name);
        natureReserve.setDescription(description);
        natureReserve.setWebsite(website);
        natureReserve.setArea(area);
        natureReserve.setYearFounded(yearFounded);
        natureReserve.setRegion(region);
        return natureReserve;
    }

    @Override
    @Transactional
    protected void saveAll(List<NatureReserve> records) {
        natureReservesRepository.saveAll(records);
    }
}
