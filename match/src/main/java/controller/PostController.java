package com.shinhan.match.controller;

import com.shinhan.match.domain.Post;
import com.shinhan.match.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 1. 모집글 전체 목록 보기
    @GetMapping
    public String list(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts/list";
    }

    // 2. 모집글 작성 폼 이동
    @GetMapping("/new")
    public String createForm() {
        return "posts/createPostForm";
    }

    // 3. 모집글 저장 처리
    @PostMapping("/new")
    public String create(@RequestParam("title") String title,
                         @RequestParam("category") String category,
                         @RequestParam("position") String position,
                         @RequestParam("writer") String writer,
                         @RequestParam("content") String content) {

        Post post = new Post(null, title, content, category, position, writer);
        postRepository.save(post);

        return "redirect:/posts";
    }
}