package com.hodolee.example.mockinterview.ch11.cache;

import com.hodolee.example.mockinterview.ch11.feed.request.Post;

import java.time.LocalDateTime;
import java.util.List;

public record CachedPost(List<Post> posts, LocalDateTime cachedAt) {}