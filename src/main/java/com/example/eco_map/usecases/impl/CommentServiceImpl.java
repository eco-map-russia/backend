package com.example.eco_map.usecases.impl;

import com.example.eco_map.api.exception.CommentNotFoundException;
import com.example.eco_map.api.exception.RegionNotFoundException;
import com.example.eco_map.persistence.model.Comment;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.User;
import com.example.eco_map.persistence.repository.CommentRepository;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.usecases.CommentService;
import com.example.eco_map.usecases.dto.CommentResponseDto;
import com.example.eco_map.usecases.dto.CreateCommentRequestDto;
import com.example.eco_map.usecases.dto.PageCommentResponseDto;
import com.example.eco_map.usecases.dto.UpdateCommentRequestDto;
import com.example.eco_map.usecases.mapper.CommentMapper;
import com.example.eco_map.usecases.mapper.PageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TransactionTemplate transactionTemplate;
    private final Scheduler jdbcScheduler;
    private final CommentMapper commentMapper;
    private final RegionRepository regionRepository;
    private final PageMapper pageMapper;


    @Override
    public Mono<PageCommentResponseDto> getAllComments(UUID regionId, Pageable pageable) {
        return Mono.fromCallable(() -> commentRepository
                        .findAllByRegionId(regionId, pageable)
                ).subscribeOn(jdbcScheduler)
                .map(page -> {
                    Page<CommentResponseDto> dtoPage = page.map(commentMapper::toCommentResponseDto);
                    return pageMapper.mapToPageComments(dtoPage);
                });
    }

    @Override
    public Mono<CommentResponseDto> createComment(UUID regionId, CreateCommentRequestDto request, User user) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {

                    Region region = regionRepository.findById(regionId)
                            .orElseThrow(() -> new RegionNotFoundException("Region not found with id: " + regionId));

                    Comment comment = new Comment();
                    comment.setUser(user);
                    comment.setText(request.getText());
                    comment.setRegion(region);

                    return commentRepository.save(comment);
                }))
                .subscribeOn(jdbcScheduler)
                .map(commentMapper::toCommentResponseDto);
    }

    @Override
    public Mono<CommentResponseDto> updateComment(UpdateCommentRequestDto request, UUID commentId, UUID userId) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                            Comment comment = commentRepository.findByIdWithUser(commentId)
                                    .orElseThrow(() ->
                                            new CommentNotFoundException("Comment not found with id: " + commentId));

                            UUID authorId = Optional.ofNullable(comment.getUser())
                                    .map(User::getId)
                                    .orElseThrow(() -> new AccessDeniedException("Comment has no associated user"));

                            if (!authorId.equals(userId)) {
                                throw new AccessDeniedException("You are not allowed to update this comment");
                            }

                            comment.setText(request.getText());
                            return commentRepository.save(comment);
                        })
                ).subscribeOn(jdbcScheduler)
                .map(commentMapper::toCommentResponseDto);
    }

    @Override
    public Mono<Void> deleteComment(UUID id, UUID userId) {
        return Mono.fromCallable(() -> {
            transactionTemplate.executeWithoutResult(status ->
                    commentRepository.findById(id)
                            .ifPresent(commentRepository::delete)
            );
            return null;
        }).subscribeOn(jdbcScheduler).then();
    }


}
