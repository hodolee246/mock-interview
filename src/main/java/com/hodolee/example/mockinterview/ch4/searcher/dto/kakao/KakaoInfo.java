package com.hodolee.example.mockinterview.ch4.searcher.dto.kakao;

public record KakaoInfo(
        String title,
        String contents,
        String url,
        String blogname,
        String thumbnail,
        String datetime
) {}