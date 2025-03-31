package com.hodolee.example.mockinterview.ch4.searcher;

import com.hodolee.example.mockinterview.ch4.searcher.dto.BlogSearchDto;
import com.hodolee.example.mockinterview.ch4.searcher.dto.ExternalApiResponse;

public interface BlogSearcher {

    ExternalApiResponse searchBlog(BlogSearchDto blogSearchDto);

}
