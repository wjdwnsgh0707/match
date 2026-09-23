package repository;

import domain.Post; // 👈 domain 패키지의 Post 클래스를 가져오도록 명시
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepository {
    private static final Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    public PostRepository() {
        save(new Post(null, "공모전", "[신한대] 2026 대학생 AI/SW 해커톤 팀원 모집", "김신한", "백엔드(Spring Boot) 1명",
                "신한대학교 학생들과 함께하는 AI 해커톤 대회입니다. 서비스 서버 구축 및 API 개발을 담당해 주실 백엔드 개발자를 모십니다!",
                "https://picsum.photos/800/400"));

        save(new Post(null, "스터디", "알고리즘 및 코딩테스트 스터디원 모집", "이컴공", "자바/파이썬 상관없음",
                "매주 3문제씩 백준/프로그래머스 푸는 스터디입니다. 강한 의지 가지신 분 우대합니다.",
                null));
    }

    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }

    public Post findById(Long id) {
        return store.get(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }
}