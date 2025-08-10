package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.repository.NatureReservesRepository;
import com.example.eco_map.usecases.NatureReservesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NatureReservesServiceImpl implements NatureReservesService {
    private final NatureReservesRepository natureReservesRepository;
    private final Scheduler jdbcScheduler;

    @Override
    public Mono<List<NatureReserve>> getByRegion(Region region) {
        return Mono.fromCallable(() -> natureReservesRepository.findByRegion(region))
                .subscribeOn(jdbcScheduler);
    }
}
