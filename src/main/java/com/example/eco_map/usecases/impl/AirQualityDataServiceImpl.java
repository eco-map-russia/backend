package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.persistence.repository.AirQualityDataRepository;
import com.example.eco_map.usecases.AirQualityDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirQualityDataServiceImpl implements AirQualityDataService {

    private final AirQualityDataRepository airQualityDataRepository;

    @Transactional
    public List<AirQualityData> saveAllAirQualityData(List<AirQualityData> dataList) {
        return airQualityDataRepository.saveAll(dataList);

    }
}