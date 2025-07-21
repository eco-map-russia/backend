package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.LocationSearchDto;
import reactor.core.publisher.Flux;

public interface SearchService {
    Flux<LocationSearchDto> searchCityOrRegionByName(String query);
}
