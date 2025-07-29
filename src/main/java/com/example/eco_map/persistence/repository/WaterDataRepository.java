package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.WaterData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WaterDataRepository extends JpaRepository<WaterData, UUID> {
    Optional<WaterData> findFirstByRegionOrderByCreatedAt(Region region);

    @Query("""
                SELECT wd FROM WaterData wd
                JOIN FETCH wd.region r
                WHERE wd.createdAt = (
                    SELECT MAX(wd2.createdAt)
                    FROM WaterData wd2
                    WHERE wd2.region.id = r.id
                )
            """)
    List<WaterData> findLatestWaterDataWithRegion();
}
