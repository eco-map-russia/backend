package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.ObservationPoint;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ObservationPointRepository extends JpaRepository<ObservationPoint, UUID> {
    Page<ObservationPoint> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM observation_points op ORDER BY ST_Distance(op.coordinates, :point) LIMIT 1", nativeQuery = true)
    Optional<ObservationPoint> findNearestPoint(@Param("point") Point point);
}
