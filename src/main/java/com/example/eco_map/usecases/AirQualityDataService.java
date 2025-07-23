package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.usecases.dto.AirQualityHistoricalResponseDto;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

public interface AirQualityDataService {
    List<AirQualityData> saveAllAirQualityData(List<AirQualityData> dataList);

    Flux<AirQualityHistoricalResponseDto> getHistoricalAirQualityData(
            ObservationPoint observationPoint,
            LocalDate startDate,
            LocalDate endDate
    );
}
