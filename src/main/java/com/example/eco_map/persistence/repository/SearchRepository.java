package com.example.eco_map.persistence.repository;

import com.example.eco_map.usecases.dto.LocationSearchDto;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SearchRepository {

    List<LocationSearchDto> searchByName(@Param("query") String query);
}
