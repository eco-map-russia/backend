package com.example.eco_map.api.client;

import com.example.eco_map.usecases.dto.AirQualityCurrentDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.example.eco_map.util.UrlConstants.CURRENT_AIR_QUALITY_URL;

@Component
@RequiredArgsConstructor
@Slf4j
public class AirQualityClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AirQualityCurrentDto getAirQuality(Double lat, Double lon) {
        String url = buildApiUrl(lat, lon);
        String json = restTemplate.getForObject(url, String.class);
        log.debug("Received JSON: {}", json);
        try {
            return processAirQualityData(json);
        } catch (IOException e) {
            log.error("Failed to process air quality JSON", e);
            throw new RuntimeException(e);
        }

    }

    private String buildApiUrl(double lat, double lon) {
        return String.format(CURRENT_AIR_QUALITY_URL, lat, lon);
    }

    private AirQualityCurrentDto processAirQualityData(String json) throws IOException {
        JsonNode root = objectMapper.readTree(json);
        JsonNode current = root.path("current");
        AirQualityCurrentDto dto = objectMapper.treeToValue(current, AirQualityCurrentDto.class);
        log.debug("Received AirQualityCurrentDto: {}", dto);
        return dto;

    }
}