package com.example.eco_map.persistence.repository;

import com.example.eco_map.usecases.dto.LocationSearchDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepository {

    private final EntityManager entityManager;

    @Override
    public List<LocationSearchDto> searchByName(String query) {
        String sql = """
                    SELECT * FROM (
                        SELECT id, name, 
                               ST_Y(center::geometry) as lat,
                               ST_X(center::geometry) as lon 
                        FROM regions
                        WHERE LOWER(name) LIKE :q
                        UNION ALL
                        SELECT id, name, 
                               ST_Y(center::geometry) as lat,
                               ST_X(center::geometry) as lon
                        FROM cities
                        WHERE LOWER(name) LIKE :q
                    ) AS res
                    ORDER BY name
                    LIMIT 10
                """;
        return entityManager.createNativeQuery(sql, "LocationSearchDtoMapping")
                .setParameter("q", query.toLowerCase() + "%")
                .getResultList();
    }

}