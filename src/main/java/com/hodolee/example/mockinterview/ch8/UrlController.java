package com.hodolee.example.mockinterview.ch8;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UrlController {

    private final UrlService urlService;

    @PostMapping("url")
    public ResponseEntity<String> createShortUrl(@RequestBody ShortenRequest request) {
        String shortUrl = urlService.createShortUrl(
                request.getTitle(),
                request.getId(),
                request.getOriginalUrl()
        );

        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{title}/{id}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String title,
                                                      @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.redirect(title, id)))
                .build();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<String> getProduct(@PathVariable String id) {
        log.info("getProduct id: {}", id);
        return ResponseEntity.ok().body(id);
    }

}
