package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.client.AirQualityClient;
import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.repository.AirQualityDataRepository;
import com.example.eco_map.persistence.repository.ObservationPointRepository;
import com.example.eco_map.usecases.AirQualityDataService;
import com.example.eco_map.usecases.dto.AirQualityCurrentDto;
import com.example.eco_map.usecases.mapper.AirQualityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirQualityDataServiceImpl implements AirQualityDataService {
    @Value("${air.quality.batch-size}")
    private int batchSize;
    private final AirQualityDataRepository airQualityDataRepository;
    private final ObservationPointRepository pointRepo;
    private final AirQualityClient airQualityClient;
    private final AirQualityMapper airQualityMapper;

    @Override
    @Scheduled(fixedRate = 600000)
    @Async
    public void fetchAndSaveAirQuality() {
        List<ObservationPoint> points = pointRepo.findAll(PageRequest.of(batchSize, 10)).getContent();
        List<AirQualityData> data = new ArrayList<>();
        for (ObservationPoint point : points) {
            Double lat = point.getCoordinates().getY();
            Double lon = point.getCoordinates().getX();
            AirQualityCurrentDto airQualityCurrentDto;
            airQualityCurrentDto = airQualityClient.getAirQuality(lat, lon);
            AirQualityData airQualityData = airQualityMapper.dtoToEntity(airQualityCurrentDto);
            airQualityData.setObservationPoint(point);
            data.add(airQualityData);
        }
        airQualityDataRepository.saveAll(data);
    }

}