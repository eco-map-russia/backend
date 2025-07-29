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
    private final WaterDataImporter waterDataImporter;
    private final PollutionSoilDataImporter pollutionSoilDataImporter;
    private final LandDegradationBalanceIndexImporter landDegradationBalanceIndexImporter;
    private final ObservationPointImporter observationPointImporter;
    private final RadiationDataImporter radiationDataImporter;

    @Override
    public void run(String... args) {
        log.info("Initializing data...");

        try {
            log.info("Importing regions...");
            regionsImporter.importRegions();

            log.info("Importing cities...");
            cityImporter.importCities();

            log.info("Importing points...");
            observationPointImporter.importObservationPoint();

            log.info("Importing water data...");
            waterDataImporter.importWaterData();

            log.info("Importing soil data...");
            pollutionSoilDataImporter.importPollutionSoilData();
            landDegradationBalanceIndexImporter.importDegradationBalanceIndex();

            log.info("Importing radiation data...");
            radiationDataImporter.importRadiationData();

        } catch (Exception e) {
            log.error("Failed during initialization", e);
        }
    }
}