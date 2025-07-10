package com.example.eco_map.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RegionsImporter regionsImporter;
    private final CityImporter cityImporter;
    private final AssignationService assignationService;

    @Override
    public void run(String... args) {
        log.info("Initializing data...");

        try {
            log.info("Importing regions...");
            regionsImporter.importRegions();

            log.info("Importing cities...");
            cityImporter.importCities();

            log.info("Assigning regions/cities to observation points...");
            assignationService.assignCitiesToPoints();

        } catch (Exception e) {
            log.error("Failed during initialization", e);
        }
    }
}