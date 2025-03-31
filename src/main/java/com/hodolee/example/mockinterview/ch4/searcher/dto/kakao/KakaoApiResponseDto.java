package com.hodolee.example.mockinterview.ch4.searcher.dto.kakao;

import java.util.List;

public record KakaoApiResponseDto(
        List<KakaoInfo> documents,
        MetaData meta
) {}