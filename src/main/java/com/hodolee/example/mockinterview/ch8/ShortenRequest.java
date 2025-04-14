package com.hodolee.example.mockinterview.ch8;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortenRequest {

    private String title;
    private Long id;
    private String originalUrl;

}
