package com.hodolee.example.mockinterview.ch4.searcher.dto.naver;

import java.util.List;

public record NaverBlogResponseDto(
        String lastBuildDate,
        int total,
        int start,
        int display,
        List<NaverInfo> items
) {}