package com.hodolee.example.mockinterview.ch8;

import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class UrlService {

    private final Map<String, String> urlMap = new HashMap<>();
    private final Hashids hashids = new Hashids("hodolee", 7);

    public String createShortUrl(String title, Long id, String originalUrl) {
        String encode = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String hashedId = hashids.encode(id);
        String key = title + "/" + hashedId;

        urlMap.put(key, originalUrl);

        return String.format("http://localhost:8080/%s/%s", encode, hashedId);
    }

    public String redirect(String title, String hashedId) {
        try {
            String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);

            if (hashids.decode(hashedId).length == 0) {
                throw new RuntimeException("id not found");
            }

            String key = decodedTitle + "/" + hashedId;
            String originalUrl = urlMap.get(key);

            if (originalUrl == null) {
                throw new RuntimeException("url not found");
            }

            return originalUrl;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
