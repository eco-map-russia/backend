package com.example.eco_map.api.controller;

import com.example.eco_map.api.AdminRegionsApi;
import com.example.eco_map.usecases.RegionService;
import com.example.eco_map.usecases.dto.PageRegionResponseDto;
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
@RequestMapping("api/v1/admin/regions")
@RequiredArgsConstructor
public class AdminRegionsController implements AdminRegionsApi {
    private final RegionService regionService;

    @GetMapping
    public Mono<ResponseEntity<PageRegionResponseDto>> getRegions(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            ServerWebExchange exchange) {
        Pageable pageable = PaginationUtils.buildPageable(page, size);
        return regionService.getAllRegions(pageable)
                .map(ResponseEntity::ok);
    }
}
