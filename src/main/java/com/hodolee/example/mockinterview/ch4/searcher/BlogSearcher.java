package com.hodolee.example.mockinterview.ch4.searcher;

import com.hodolee.example.mockinterview.ch4.searcher.dto.BlogSearchDto;
import com.hodolee.example.mockinterview.ch4.searcher.dto.ExternalApiResponseDto;

public interface BlogSearcher {

    ExternalApiResponseDto searchBlog(final BlogSearchDto blogSearchDto);

}
