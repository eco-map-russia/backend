package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.repository.SearchRepository;
import com.example.eco_map.usecases.SearchService;
import com.example.eco_map.usecases.dto.LocationSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;
    private final Scheduler jdbcScheduler;

    @Override
    public Flux<LocationSearchDto> searchCityOrRegionByName(String query) {
        if (query == null || query.trim().isEmpty()) {
            return Flux.empty();
        }
        return Mono.fromCallable(() ->
                        searchRepository.searchByName(query))
                .subscribeOn(jdbcScheduler)
                .flatMapMany(Flux::fromIterable);
    }
}
