package com.example.eco_map.api.client;

import com.example.eco_map.config.properties.AirQualityProperties;
import com.example.eco_map.usecases.dto.AirQualityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AirQualityBaseClient extends BaseApiClient {

    private final AirQualityProperties properties;

    public AirQualityBaseClient(
            AirQualityProperties properties,
            WebClient.Builder webClientBuilder
    ) {
        super(
                webClientBuilder.baseUrl(properties.getBaseUrl()).build(),
                properties.getBaseUrl()
        );
        this.properties = properties;
    }

    public Mono<AirQualityDto> getCurrentAirQuality(double lat, double lon) {
        String uri = String.format(properties.getTemplates().getCurrent(), lat, lon);
        log.debug("Calling current air quality API: {}", uri);
        return get(uri, AirQualityDto.class);
    }

}
