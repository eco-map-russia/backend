package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.AirQualityData;

import java.util.List;

public interface AirQualityDataService {
    List<AirQualityData> saveAllAirQualityData(List<AirQualityData> dataList);
}
