package com.hodolee.example.mockinterview.ch11.feed.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private FeedUser feedUser;

    @Builder
    public Post(Long id, String content, LocalDateTime createAt, FeedUser feedUser) {
        this.id = id;
        this.content = content;
        this.createAt = createAt;
        this.feedUser = feedUser;
    }

}
