package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @EntityGraph(attributePaths = {"region", "user"})
    Page<Comment> findAllByRegionId(UUID regionId, Pageable pageable);

    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.id = :id")
    Optional<Comment> findByIdWithUser(UUID id);

}
