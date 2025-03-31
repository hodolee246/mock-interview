package com.hodolee.example.mockinterview.ch4.searcher.dto.naver;

public record NaverInfo(
        String title,
        String link,
        String description,
        String bloggername,
        String bloggerlink,
        String postdate
) {}