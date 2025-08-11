package com.example.eco_map.api.controller;

import com.example.eco_map.api.CommentsApi;
import com.example.eco_map.security.CurrentUserService;
import com.example.eco_map.usecases.CommentService;
import com.example.eco_map.usecases.dto.CommentResponseDto;
import com.example.eco_map.usecases.dto.CreateCommentRequestDto;
import com.example.eco_map.usecases.dto.PageCommentResponseDto;
import com.example.eco_map.util.CommentSortField;
import com.example.eco_map.util.PaginationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/regions/{regionId}/comments")
public class CommentController implements CommentsApi {
    private final CommentService commentService;
    private final CurrentUserService currentUserService;

    @GetMapping
    @Override
    public Mono<ResponseEntity<PageCommentResponseDto>> getAllComments(
            @PathVariable UUID regionId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "desc") String direction,
            ServerWebExchange exchange
    ) {
        Pageable pageable = PaginationUtils.buildSortedPageable(
                page,
                size,
                direction,
                CommentSortField.CREATED_AT.getFieldName()
        );

        return commentService.getAllComments(regionId, pageable)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Override
    public Mono<ResponseEntity<CommentResponseDto>> createComment(
            @PathVariable UUID regionId,
            @RequestBody @Valid Mono<CreateCommentRequestDto> createCommentRequestDto,
            ServerWebExchange exchange) {
        return currentUserService.getCurrentUser()
                .zipWith(createCommentRequestDto)
                .flatMap(tuple -> {
                    var user = tuple.getT1();
                    var comment = tuple.getT2();
                    return commentService.createComment(regionId, comment, user);
                })
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));

    }

}
