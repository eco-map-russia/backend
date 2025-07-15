package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.Role;
import com.example.eco_map.persistence.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleType name);
}
