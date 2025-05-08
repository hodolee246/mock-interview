package com.hodolee.example.mockinterview.ch11;

import java.time.LocalDateTime;
import java.util.List;

public record CachedPost(List<Post> posts, LocalDateTime cachedAt) {}