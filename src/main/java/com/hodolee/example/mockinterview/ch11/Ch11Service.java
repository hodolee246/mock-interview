package com.hodolee.example.mockinterview.ch11;

import com.hodolee.example.mockinterview.ch11.cache.CachedPost;
import com.hodolee.example.mockinterview.ch11.feed.domain.Follow;
import com.hodolee.example.mockinterview.ch11.feed.domain.Post;
import com.hodolee.example.mockinterview.ch11.feed.domain.User;
import com.hodolee.example.mockinterview.ch11.feed.domain.repository.FollowRepository;
import com.hodolee.example.mockinterview.ch11.feed.domain.repository.PostRepository;
import com.hodolee.example.mockinterview.ch11.feed.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class Ch11Service {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostRepository postRepository;

    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, Set<Long>> followingUsers = new HashMap<>();
    private final Map<Long, List<Post>> userPosts = new HashMap<>();
    private final Map<Long, CachedPost> cache = new HashMap<>();

    private Long userSeq = 1L;
    private Long postSeq = 1L;

    @Transactional
    public void createUser(String name) {
        userRepository.save(User.builder()
                .name(name).build());
    }

    @Transactional
    public void follow(Long fromUserId, Long toUserId) {
        User fromUser = userRepository.findById(fromUserId).orElseThrow(() -> new NoSuchElementException("can not found fromUserId: " + fromUserId));
        User toUser = userRepository.findById(toUserId).orElseThrow(() -> new NoSuchElementException("can not found toUserId: " + toUserId));

        followRepository.save(Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .createAt(LocalDateTime.now())
                .build());
    }

    @Transactional
    public void createPost(Long userId, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("can not found userId: " + userId));
        postRepository.save(Post.builder()
                .user(user)
                .content(content)
                .createAt(LocalDateTime.now())
                .build());
    }

    public List<Post> getFeed(Long userId) throws InterruptedException {
        Set<Long> followeeIds = followingUsers.getOrDefault(userId, Set.of());

        CachedPost cachedPost = cache.get(userId);
        if (cachedPost != null) {
            return cachedPost.posts();
        }

        List<Post> posts = followeeIds.stream()
                .flatMap(id -> userPosts.getOrDefault(id, List.of()).stream())
                .sorted(Comparator.comparing(Post::createdAt).reversed())
                .limit(3)
                .toList();
        cache.put(userId, new CachedPost(posts, LocalDateTime.now()));
        // 디따 많이 조회중...
        Thread.sleep(3000);

        return posts;
    }

}
