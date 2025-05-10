package com.hodolee.example.mockinterview.ch11;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class Ch11Service {

    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, Set<Long>> followingUsers = new HashMap<>();
    private final Map<Long, List<Post>> userPosts = new HashMap<>();
    private final Map<Long, CachedPost> cache = new HashMap<>();

    private Long userSeq = 1L;
    private Long postSeq = 1L;

    public User createUser(String name) {
        User user = new User(userSeq++, name);
        users.put(user.id(), user);
        return user;
    }

    public void follow(Long fromUserId, Long toUserId) {
        // computeIfAbsent (없으면 put(fromUserId, new HashSet<>(toUserId)
        followingUsers.computeIfAbsent(fromUserId, k -> new HashSet<>()).add(toUserId);
    }

    public Post createPost(Long userId, String content) {
        Post post = new Post(postSeq++, userId, content, LocalDateTime.now());
        userPosts.computeIfAbsent(userId, k -> new ArrayList<>()).add(post);
        return post;
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
