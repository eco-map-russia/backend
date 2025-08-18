package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.dto.RegionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RegionRepository extends JpaRepository<Region, UUID> {
    @Query(value = """
            SELECT * FROM regions
            WHERE to_tsvector('russian', name) @@ plainto_tsquery('russian', :query)
            LIMIT 1
            """, nativeQuery = true)
    Optional<Region> findByRegionName(@Param("query") String query);

    @Query("SELECT new com.example.eco_map.usecases.dto.RegionDto(r.id, r.name) FROM Region r")
    Page<RegionDto> findAllWithoutCoordinates(Pageable pageable);
}
