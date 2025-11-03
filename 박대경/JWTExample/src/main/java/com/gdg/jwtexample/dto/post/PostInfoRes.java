package com.gdg.jwtexample.dto.post;

import com.gdg.jwtexample.domain.Post;
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
    public static PostInfoRes fromEntity(Post post) {
        return PostInfoRes.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getName())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
