package com.hodolee.example.mockinterview.ch11;

import com.hodolee.example.mockinterview.ch11.cache.CachedPost;
import com.hodolee.example.mockinterview.ch11.feed.domain.FeedUser;
import com.hodolee.example.mockinterview.ch11.feed.domain.Follow;
import com.hodolee.example.mockinterview.ch11.feed.domain.Post;
import com.hodolee.example.mockinterview.ch11.feed.domain.repository.FollowRepository;
import com.hodolee.example.mockinterview.ch11.feed.domain.repository.PostRepository;
import com.hodolee.example.mockinterview.ch11.feed.domain.repository.UserRepository;
import com.hodolee.example.mockinterview.ch11.feed.response.RespPost;
import lombok.RequiredArgsConstructor;
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
    // cache
    private final Map<Long, List<RespPost>> cache = new HashMap<>();

    @Transactional
    public void createUser(String name) {
        userRepository.save(FeedUser.builder()
                .name(name).build());
    }

    @Transactional
    public void follow(Long fromUserId, Long toUserId) {
        FeedUser fromFeedUser = userRepository.findById(fromUserId).orElseThrow(() -> new NoSuchElementException("can not found fromUserId: " + fromUserId));
        FeedUser toFeedUser = userRepository.findById(toUserId).orElseThrow(() -> new NoSuchElementException("can not found toUserId: " + toUserId));

        followRepository.save(Follow.builder()
                .fromFeedUser(fromFeedUser)
                .toFeedUser(toFeedUser)
                .createAt(LocalDateTime.now())
                .build());
    }

    @Transactional
    public void createPost(Long userId, String content) {
        FeedUser feedUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("can not found userId: " + userId));
        postRepository.save(Post.builder()
                .feedUser(feedUser)
                .content(content)
                .createAt(LocalDateTime.now())
                .build());
    }

    public List<RespPost> getFeed(Long userId) throws InterruptedException {
        // 캐시 존재하면 캐시 반환
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }

        FeedUser fromFeedUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("can not found userId: " + userId));
        List<Follow> followList = followRepository.findByFromFeedUser(fromFeedUser);
        // 팔로우 누른 유저 조회
        List<FeedUser> followFeedUserList = followList.stream()
                .map(Follow::getToFeedUser)
                .toList();

        // 팔로우 유저들의 포스팅 조회
        List<Post> followingUserPost = postRepository.findByFeedUserList(followFeedUserList);
        // 리스폰스 변환
        List<RespPost> responseList = followingUserPost.stream()
                .map(followPost -> RespPost.of(
                        followPost.getId(),
                        followPost.getFeedUser().getId(),
                        followPost.getContent(),
                        followPost.getCreateAt()
                )).toList();
        // 너무 많아서 조회하는데 시간이 걸립니다...
        Thread.sleep(2500);
        // 캐시 저장
        cache.put(userId, responseList);

        return responseList;
    }

}
