package com.example.eco_map.api.controller;

import com.example.eco_map.security.CurrentUserService;
import com.example.eco_map.usecases.CommentService;
import com.example.eco_map.usecases.dto.CommentResponseDto;
import com.example.eco_map.usecases.dto.CreateCommentRequestDto;
import com.example.eco_map.util.CommentSortField;
import com.example.eco_map.util.PaginationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/regions/{regionId}/comments")
public class CommentController {
    private final CommentService commentService;
    private final CurrentUserService currentUserService;

    @GetMapping
    public Mono<ResponseEntity<Page<CommentResponseDto>>> getAllComments(
            @PathVariable UUID regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String direction

    ) {
        Pageable pageable = PaginationUtils.buildSortedPageable(page,
                size,
                direction,
                CommentSortField.CREATED_AT.getFieldName());
        return commentService.getAllComments(regionId, pageable).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<CommentResponseDto>> createComment(
            @PathVariable UUID regionId,
            @RequestBody @Valid CreateCommentRequestDto request) {
        return currentUserService.getCurrentUser()
                .flatMap(currentUser ->
                        commentService.createComment(regionId, request, currentUser)
                )
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

}
