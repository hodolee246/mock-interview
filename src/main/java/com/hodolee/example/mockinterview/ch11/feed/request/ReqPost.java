package com.hodolee.example.mockinterview.ch11.feed.request;

import java.time.LocalDateTime;

public record ReqPost(Long id, Long userId, String content, LocalDateTime createdAt) {}
