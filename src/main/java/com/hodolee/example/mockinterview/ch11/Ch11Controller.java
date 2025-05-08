package com.hodolee.example.mockinterview.ch11;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ch11")
@RequiredArgsConstructor
public class Ch11Controller {

    private final Ch11Service ch11Service;

    @PostMapping("users")
    public User createUser(@RequestParam String name) {
        return ch11Service.createUser(name);
    }

    @PostMapping("follow")
    public ResponseEntity<String> follow(@RequestParam Long from, @RequestParam Long to) {
        ch11Service.follow(from, to);
        return ResponseEntity.ok("Followed");
    }

    @PostMapping("posts")
    public Post createPost(@RequestParam Long userId, @RequestParam String content) {
        return ch11Service.createPost(userId, content);
    }

    @GetMapping("/feed/{userId}")
    public List<Post> getFeed(@PathVariable Long userId) {
        return ch11Service.getFeed(userId);
    }

}
