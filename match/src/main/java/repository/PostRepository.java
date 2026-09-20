package com.shinhan.match.repository;

import com.shinhan.match.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {

    private static final Map<Long, Post> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0L);

    // 게시글 저장
    public Post save(Post post) {
        post.setId(sequence.incrementAndGet());
        store.put(post.getId(), post);
        return post;
    }

    // 게시글 단건 조회 (ID로 찾기)
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 전체 게시글 목록 조회
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    // 저장소 비우기 (테스트용)
    public void clearStore() {
        store.clear();
    }
}