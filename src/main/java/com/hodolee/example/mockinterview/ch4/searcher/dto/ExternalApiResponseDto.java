package com.hodolee.example.mockinterview.ch4.searcher.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExternalApiResponseDto {

    private final List<BlogDto> blogs = new ArrayList<>();
    @Setter
    private MetaData meta;

}
