package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.City;
import com.example.eco_map.usecases.dto.PageCityResponseDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface CityService {
    void save(City city);

    Mono<PageCityResponseDto> getAllCities(Pageable pageable);
}
