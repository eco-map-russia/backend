package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.City;
import com.example.eco_map.persistence.repository.CityRepository;
import com.example.eco_map.usecases.CityService;
import com.example.eco_map.usecases.dto.PageCityResponseDto;
import com.example.eco_map.usecases.mapper.PageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final Scheduler jdbcScheduler;
    private final PageMapper pageMapper;

    @Transactional
    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public Mono<PageCityResponseDto> getAllCities(Pageable pageable) {
        return Mono.fromCallable(() -> cityRepository
                        .findAllWithoutCoordinates(pageable)
                ).subscribeOn(jdbcScheduler)
                .map(pageMapper::mapToPageCityResponse);
    }
}
