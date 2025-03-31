package com.hodolee.example.mockinterview.ch4.searcher.impl;

import com.hodolee.example.mockinterview.ch4.searcher.BlogSearcher;
import com.hodolee.example.mockinterview.ch4.searcher.dto.BlogSearchDto;
import com.hodolee.example.mockinterview.ch4.searcher.dto.ExternalApiResponse;
import com.hodolee.example.mockinterview.ch4.searcher.dto.naver.NaverBlogResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverSearcher implements BlogSearcher {

    @Value("${blog.search.naver.apiUri}")
    private String apiUri;

    @Value("${blog.search.naver.clientId}")
    private String clientId;

    @Value("${blog.search.naver.clientSecret}")
    private String clientSecret;

    public ExternalApiResponse searchBlog(final BlogSearchDto blogSearchDto) {
        Integer page = blogSearchDto.getPage();
        int start = (page - 1) * 10 + 1;
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(apiUri)
                .queryParam("query", blogSearchDto.getQuery())
                .queryParam("sort", blogSearchDto.getSort())
                .queryParam("start", start)
                .queryParam("display", page)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", clientId);
        headers.add("X-Naver-Client-Secret", clientSecret);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<NaverBlogResponseDto> apiResponse = restTemplate.exchange(
                    uriComponents.encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    NaverBlogResponseDto.class);

            return ExternalApiResponse.builder()
                    .naverBlogResponseDto(apiResponse.getBody())
                    .build();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("API Parse Error", e);
        }
    }
}
