package com.hodolee.example.mockinterview.ch4.searcher.dto;

import com.hodolee.example.mockinterview.ch4.searcher.dto.kakao.KakaoApiResponseDto;
import com.hodolee.example.mockinterview.ch4.searcher.dto.naver.NaverBlogResponseDto;
import lombok.Builder;

@Builder
public record ExternalApiResponse(
        KakaoApiResponseDto kakaoApiResponseDto,
        NaverBlogResponseDto naverBlogResponseDto
) {}
