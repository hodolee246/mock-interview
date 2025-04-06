package com.hodolee.example.mockinterview.ch8;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class UrlService {

    private final Map<String, String> urlMap = new HashMap<>();

    public String generateSlug(String title) {
        return title.trim().replaceAll("\\s+", "-");
    }

    public String createShortUrl(String title, Long id, String originalUrl) {
        String slug = generateSlug(title);
        String encodedSlug = URLEncoder.encode(slug, StandardCharsets.UTF_8);
        String key = slug + "/" + id;

        urlMap.put(key, originalUrl);

        return String.format("http://localhost:8080/%s/%d", encodedSlug, id);
    }

    public String redirect(String title, String id) {
        try {
            String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
            String key = decodedTitle + "/" + id;
            String originalUrl = urlMap.get(key);

            if (originalUrl == null) {
                throw new RuntimeException("not found");
            }

            return originalUrl;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
