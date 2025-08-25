package com.example.eco_map.util.data;

import com.example.eco_map.config.properties.PathProperties;
import com.example.eco_map.persistence.model.City;
import com.example.eco_map.persistence.model.CleanupEvent;
import com.example.eco_map.persistence.repository.CityRepository;
import com.example.eco_map.persistence.repository.CleanupEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CleanupEventImporter extends AbstractCsvImporter<CleanupEvent> {

    private final PathProperties pathProperties;
    private final CleanupEventRepository cleanupEventRepository;
    private final CityRepository cityRepository;

    private Map<String, City> cityMap;

    private static final int CITY = 1;
    private static final int LOCATION = 2;
    private static final int DATE = 3;
    private static final int ORGANIZER = 4;
    private static final int DESCRIPTION = 5;
    private static final int PARTICIPANTS_EXPECTED = 6;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CleanupEventImporter(ResourceLoader resourceLoader,
                                PathProperties pathProperties,
                                CleanupEventRepository cleanupEventRepository,
                                CityRepository cityRepository) {
        super(resourceLoader);
        this.pathProperties = pathProperties;
        this.cleanupEventRepository = cleanupEventRepository;
        this.cityRepository = cityRepository;
    }

    public void importCleanupEvent() {
        this.cityMap = cityRepository.findAll().stream()
                .collect(Collectors.toMap(
                        city -> city.getName().toLowerCase(),
                        Function.identity()
                ));

        importLines(this::parseLine, pathProperties.getCleanupEventsFile());
    }

    private CleanupEvent parseLine(String[] line) {
        String cityName = line[CITY].trim().toLowerCase();
        City city = cityMap.get(cityName);

        if (city == null) {
            log.warn("City does not exist: {}", cityName);
            return null;
        }

        LocalDate date;
        try {
            date = LocalDate.parse(line[DATE].trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            log.error("Invalid date format: {}", line[DATE].trim());
            throw new IllegalArgumentException("Invalid date format: " + line[DATE].trim(), e);
        }

        int participantsExpected;
        try {
            participantsExpected = Integer.parseInt(line[PARTICIPANTS_EXPECTED].trim());
        } catch (NumberFormatException e) {
            log.error("Invalid number format for participants: {}", line[PARTICIPANTS_EXPECTED].trim());
            throw new IllegalArgumentException("Invalid number format for participants: " + line[PARTICIPANTS_EXPECTED].trim(), e);
        }

        CleanupEvent cleanupEvent = new CleanupEvent();
        cleanupEvent.setCity(city);
        cleanupEvent.setLocation(line[LOCATION].trim());
        cleanupEvent.setDate(date);
        cleanupEvent.setOrganizer(line[ORGANIZER].trim());
        cleanupEvent.setDescription(line[DESCRIPTION].trim());
        cleanupEvent.setParticipantsExpected(participantsExpected);

        return cleanupEvent;
    }

    @Transactional
    @Override
    protected void saveAll(List<CleanupEvent> records) {
        cleanupEventRepository.saveAll(records);
    }
}
