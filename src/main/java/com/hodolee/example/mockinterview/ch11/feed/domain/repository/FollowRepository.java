package com.hodolee.example.mockinterview.ch11.feed.domain.repository;

import com.hodolee.example.mockinterview.ch11.feed.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
