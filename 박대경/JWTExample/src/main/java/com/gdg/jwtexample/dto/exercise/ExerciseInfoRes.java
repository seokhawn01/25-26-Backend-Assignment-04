package com.gdg.jwtexample.dto.post;

import com.gdg.jwtexample.domain.Exercise;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostInfoRes(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostInfoRes fromEntity(Exercise exercise) {
        return PostInfoRes.builder()
                .id(exercise.getId())
                .title(exercise.getTitle())
                .content(exercise.getContent())
                .author(exercise.getUser().getName())
                .createdAt(exercise.getCreatedAt())
                .updatedAt(exercise.getUpdatedAt())
                .build();
    }
}
