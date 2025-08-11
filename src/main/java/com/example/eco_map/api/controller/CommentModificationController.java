package com.example.eco_map.api.controller;

import com.example.eco_map.api.CommentsModificationApi;
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
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comments/{id}")
public class CommentModificationController implements CommentsModificationApi {
    private final CommentService commentService;
    private final CurrentUserService currentUserService;

    @DeleteMapping
    @Override
    public Mono<ResponseEntity<Void>> deleteComment(@PathVariable UUID id, ServerWebExchange exchange) {
        return currentUserService.getCurrentUser()
                .flatMap(currentUser -> commentService.deleteComment(id, currentUser.getId()))
                .thenReturn(ResponseEntity.noContent().build());
    }

    @PatchMapping
    @Override
    public Mono<ResponseEntity<CommentResponseDto>> updateComment(
            @PathVariable UUID id,
            @RequestBody @Valid Mono<UpdateCommentRequestDto> updateCommentRequestDto,
            ServerWebExchange exchange) {
        return currentUserService.getCurrentUser()
                .zipWith(updateCommentRequestDto)
                .flatMap(tuple -> {
                    var currentUser = tuple.getT1();
                    var request = tuple.getT2();
                    return commentService.updateComment(request, id, currentUser.getId());
                })
                .map(ResponseEntity::ok);
    }
}
