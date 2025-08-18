package com.example.eco_map.api.controller;

import com.example.eco_map.api.AdminCitiesApi;
import com.example.eco_map.usecases.CityService;
import com.example.eco_map.usecases.dto.PageCityResponseDto;
import com.example.eco_map.util.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/admin/cities")
@RequiredArgsConstructor
public class AdminCitiesController implements AdminCitiesApi {
    private final CityService cityService;

    @GetMapping
    public Mono<ResponseEntity<PageCityResponseDto>> getCities(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            ServerWebExchange exchange) {
        Pageable pageable = PaginationUtils.buildPageable(page, size);
        return cityService.getAllCities(pageable)
                .map(ResponseEntity::ok);
    }
}
