package com.hodolee.example.mockinterview.ch11;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ch11")
@RequiredArgsConstructor
public class Ch11Controller {

    private final Ch11Service ch11Service;

    @PostMapping("users")
    public ResponseEntity<?> createUser(@RequestParam String name) {
        ch11Service.createUser(name);
        return ResponseEntity.ok().build();
    }

    @PostMapping("follow")
    public ResponseEntity<String> follow(@RequestParam Long from, @RequestParam Long to) {
        ch11Service.follow(from, to);
        return ResponseEntity.ok("Followed");
    }

    @PostMapping("posts")
    public ResponseEntity<?> createPost(@RequestParam Long userId, @RequestParam String content) {
        ch11Service.createPost(userId, content);
        return ResponseEntity.ok().build();
    }

    @GetMapping("feed/{userId}")
    public ResponseEntity<?> getFeed(@PathVariable Long userId) throws InterruptedException {
        return ResponseEntity.ok().body(ch11Service.getFeed(userId));
    }

}
