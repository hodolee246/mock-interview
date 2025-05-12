package com.hodolee.example.mockinterview.ch11.feed.domain.repository;

import com.hodolee.example.mockinterview.ch11.feed.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
