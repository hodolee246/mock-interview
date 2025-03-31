package com.hodolee.example.mockinterview.ch4.searcher.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MetaData(
        @JsonProperty("total_count") int totalCount,
        @JsonProperty("pageable_count") int pageableCount,
        @JsonProperty("is_end") boolean isEnd
) {}