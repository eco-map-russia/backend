package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.City;
import com.example.eco_map.persistence.repository.CityRepository;
import com.example.eco_map.usecases.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Transactional
    @Override
    public void save(City city) {
        cityRepository.save(city);
    }
}
