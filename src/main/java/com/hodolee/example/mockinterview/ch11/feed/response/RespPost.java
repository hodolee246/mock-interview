package com.hodolee.example.mockinterview.ch11.feed.response;

import java.time.LocalDateTime;

public record RespPost(Long id, Long userId, String content, LocalDateTime createdAt) {
    public static RespPost of(Long id, Long userId, String content, LocalDateTime createdAt) {
        return new RespPost(id, userId, content, createdAt);
    }
}