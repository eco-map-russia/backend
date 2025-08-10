package com.example.eco_map.api.controller;

import com.example.eco_map.security.CurrentUserService;
import com.example.eco_map.usecases.CommentService;
import com.example.eco_map.usecases.dto.CommentResponseDto;
import com.example.eco_map.usecases.dto.UpdateCommentRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comments")
public class CommentModificationController {
    private final CommentService commentService;
    private final CurrentUserService currentUserService;

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<CommentResponseDto>> updateComment(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateCommentRequestDto request) {

        return currentUserService.getCurrentUser()
                .flatMap(currentUser ->
                        commentService.updateComment(request, id, currentUser.getId()
                        ))
                .map(ResponseEntity::ok);

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteComment(
            @PathVariable UUID id) {
        return currentUserService.getCurrentUser()
                .flatMap(currentUser -> commentService.deleteComment(id, currentUser.getId()))
                .thenReturn(ResponseEntity.noContent().build());
    }
}
