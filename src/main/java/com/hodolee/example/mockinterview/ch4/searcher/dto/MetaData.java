package com.hodolee.example.mockinterview.ch4.searcher.dto;

public record MetaData(
        int totalCount,
        Integer pageableCount,
        Boolean isEnd
) {}
