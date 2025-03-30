package com.hodolee.example.mockinterview.ch4.searcher.impl;

import com.hodolee.example.mockinterview.ch4.searcher.BlogSearcher;
import com.hodolee.example.mockinterview.ch4.searcher.dto.BlogDto;
import com.hodolee.example.mockinterview.ch4.searcher.dto.BlogSearchDto;
import com.hodolee.example.mockinterview.ch4.searcher.dto.ExternalApiResponseDto;
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
public class KakaoSearcher implements BlogSearcher {

    @Value("${blog.search.kakao.apiUri}")
    private String apiUri;

    @Value("${blog.search.kakao.apiKey}")
    private String apiKey;

    public ExternalApiResponseDto searchBlog(final BlogSearchDto blogSearchDto) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(apiUri)
                .queryParam("query", blogSearchDto.query())
                .queryParam("sort", blogSearchDto.sort())
                .queryParam("page", blogSearchDto.page())
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<BlogDto> apiResponse = restTemplate.exchange(
                    uriComponents.encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    BlogDto.class);
            ExternalApiResponseDto response = new ExternalApiResponseDto();
            BlogDto blogDto = apiResponse.getBody();

            if (blogDto != null) {
                response.getBlogs().add(blogDto);
            }

            return response;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("API Parse Error", e);
        }
    }

}
