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
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private FeedUser fromFeedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private FeedUser toFeedUser;

    @Builder
    public Follow(Long id, LocalDateTime createAt, FeedUser fromFeedUser, FeedUser toFeedUser) {
        this.id = id;
        this.createAt = createAt;
        this.fromFeedUser = fromFeedUser;
        this.toFeedUser = toFeedUser;
    }

}
