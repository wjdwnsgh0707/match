package com.shinhan.match.repository;

import com.shinhan.match.domain.Category;
import com.shinhan.match.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategory(Category category);
}