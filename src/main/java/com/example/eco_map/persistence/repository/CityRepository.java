package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.City;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {

    @Query(value = "SELECT * FROM cities c WHERE ST_Contains(c.geom, :point)  LIMIT 1", nativeQuery = true)
    City findCityForPoint(@Param("point") Point point);

    @Query(value = """
            SELECT *
            FROM cities c
            ORDER BY ST_Distance(c.geom, ST_SetSRID(:point, 4326))
            LIMIT 1
            """, nativeQuery = true)
    City findNearestCity(@Param("point") Point point);
}
