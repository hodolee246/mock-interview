package com.hodolee.example.mockinterview.ch11;

import java.time.LocalDateTime;

public record Post(Long id, Long userId, String content, LocalDateTime createdAt) {}
