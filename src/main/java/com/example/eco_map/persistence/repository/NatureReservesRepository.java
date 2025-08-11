package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.persistence.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NatureReservesRepository extends JpaRepository<NatureReserve, UUID> {
    List<NatureReserve> findByRegion(Region region);
}
