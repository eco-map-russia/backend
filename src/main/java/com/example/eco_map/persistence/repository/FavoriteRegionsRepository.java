package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.FavoriteRegion;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteRegionsRepository extends JpaRepository<FavoriteRegion, UUID> {
    boolean existsByUserAndRegion(User user, Region region);

    @EntityGraph(attributePaths = "region")
    Page<FavoriteRegion> findAllByUserId(UUID userId, Pageable pageable);

}
