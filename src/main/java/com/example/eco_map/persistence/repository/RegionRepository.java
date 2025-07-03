package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.Region;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RegionRepository extends JpaRepository<Region, UUID> {
    @Query(value = "SELECT * FROM regions r WHERE ST_Contains(r.geom, :point)  LIMIT 1", nativeQuery = true)
    Region findRegionByPoint(@Param("point") Point point);

}
