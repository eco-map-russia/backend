package com.example.eco_map.api.client;

import com.example.eco_map.config.properties.AirQualityProperties;
import com.example.eco_map.usecases.dto.AirQualityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

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
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("latitude", lat)
                .queryParam("longitude", lon)
                .queryParam("current", String.join(",", properties.getCurrentParameters()))
                .build()
                .toUri();

        log.debug("Calling current air quality API: {}", uri);

        return get(uri.toString(), AirQualityDto.class)
                .onErrorResume(e -> {
                    log.error("External API failed: {}", e.getMessage());
                    return Mono.empty();
                });

    }

}
