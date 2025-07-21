package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.SoilData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SoilDataRepository extends JpaRepository<SoilData, UUID> {
}
