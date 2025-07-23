package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.WaterData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WaterDataRepository extends JpaRepository<WaterData, UUID> {
}
