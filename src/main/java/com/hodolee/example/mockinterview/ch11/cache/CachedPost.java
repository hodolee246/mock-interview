package com.hodolee.example.mockinterview.ch11.cache;

import com.hodolee.example.mockinterview.ch11.feed.request.ReqPost;

import java.time.LocalDateTime;
import java.util.List;

public record CachedPost(List<ReqPost> posts, LocalDateTime cachedAt) {}