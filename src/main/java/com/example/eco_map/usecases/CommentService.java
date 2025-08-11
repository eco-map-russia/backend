package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.User;
import com.example.eco_map.usecases.dto.CommentResponseDto;
import com.example.eco_map.usecases.dto.CreateCommentRequestDto;
import com.example.eco_map.usecases.dto.PageCommentResponseDto;
import com.example.eco_map.usecases.dto.UpdateCommentRequestDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CommentService {
    Mono<PageCommentResponseDto> getAllComments(UUID regionId, Pageable pageable);

    Mono<CommentResponseDto> createComment(UUID regionId, CreateCommentRequestDto request, User user);

    Mono<CommentResponseDto> updateComment(UpdateCommentRequestDto request, UUID commentId, UUID userId);

    Mono<Void> deleteComment(UUID id, UUID userId);
}
