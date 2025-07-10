package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.Region;
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


}
