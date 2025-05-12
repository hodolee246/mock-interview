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
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    @Builder
    public Follow(Long id, LocalDateTime createAt, User fromUser, User toUser) {
        this.id = id;
        this.createAt = createAt;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

}
