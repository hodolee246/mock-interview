package com.hodolee.example.mockinterview.ch4;

import com.hodolee.example.mockinterview.ch4.searcher.BlogSearcher;
import com.hodolee.example.mockinterview.ch4.searcher.dto.BlogSearchDto;
import com.hodolee.example.mockinterview.ch4.searcher.dto.ExternalApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Ch4Service {

    private final BlogSearcher kakaoSearcher;
    private final BlogSearcher naverSearcher;

    public Ch4Service(@Qualifier("kakaoSearcher") BlogSearcher kakaoSearcher,
                      @Qualifier("naverSearcher") BlogSearcher naverSeacher) {
        this.kakaoSearcher = kakaoSearcher;
        this.naverSearcher = naverSeacher;
    }

    @CircuitBreaker(name = "caller", fallbackMethod = "getNaverBlog")
    public ExternalApiResponse getKakaoBlog(String query, String sort, Integer page) {
        log.info("getKakaoBlog query: {}, sort: {}, page: {}", query, sort, page);
        return kakaoSearcher.searchBlog(new BlogSearchDto(query, sort, page));
    }

    private ExternalApiResponse getNaverBlog(String query, String sort, Integer page, Throwable t) {
        log.info("Fallback : {}", t.getMessage());
        BlogSearchDto blogSearchDto = new BlogSearchDto(query, sort, page);
        blogSearchDto.convertNaverApi();
        return naverSearcher.searchBlog(blogSearchDto);
    }
}
