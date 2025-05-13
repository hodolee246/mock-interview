package com.hodolee.example.mockinterview.ch11.feed.domain.repository;

import com.hodolee.example.mockinterview.ch11.feed.domain.FeedUser;
import com.hodolee.example.mockinterview.ch11.feed.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.feedUser in (:feedUserList)")
    List<Post> findByFeedUserList(List<FeedUser> feedUserList);

}
