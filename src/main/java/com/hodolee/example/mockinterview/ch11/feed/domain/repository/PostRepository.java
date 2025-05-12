package com.hodolee.example.mockinterview.ch11.feed.domain.repository;

import com.hodolee.example.mockinterview.ch11.feed.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
