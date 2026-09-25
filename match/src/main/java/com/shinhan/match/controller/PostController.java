package com.shinhan.match.controller;

import com.shinhan.match.domain.Category;
import com.shinhan.match.domain.Post;
import com.shinhan.match.repository.PostRepository;
import jakarta.servlet.http.HttpSession; // javax -> jakarta로 변경
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    // 1. 게시글 목록 (카테고리 필터링 지원)
    @GetMapping
    public String list(@RequestParam(value = "category", required = false) Category category, Model model) {
        List<Post> posts;
        if (category != null) {
            posts = postRepository.findByCategory(category);
        } else {
            posts = postRepository.findAll();
        }
        model.addAttribute("posts", posts);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("categories", Category.values());
        return "posts/list";
    }

    // 2. 글 작성 폼 이동
    @GetMapping("/new")
    public String createForm(Model model, HttpSession session) {
        String loginMember = (String) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/members/login";
        }

        Post post = new Post();
        post.setAuthor(loginMember);

        model.addAttribute("post", post);
        model.addAttribute("categories", Category.values());
        return "posts/form";
    }

    // 3. 게시글 저장
    @PostMapping("/new")
    public String create(@ModelAttribute("post") Post post, HttpSession session) {
        String loginMember = (String) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/members/login";
        }

        post.setAuthor(loginMember);
        postRepository.save(post);
        return "redirect:/posts";
    }

    // 4. 게시글 상세 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        model.addAttribute("post", post);
        return "posts/detail";
    }

    // 5. 게시글 수정 폼 이동
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        model.addAttribute("post", post);
        model.addAttribute("categories", Category.values());
        return "posts/edit";
    }

    // 6. 게시글 수정 처리
    @PostMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id, @ModelAttribute("post") Post postParam) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        post.setTitle(postParam.getTitle());
        post.setContent(postParam.getContent());
        post.setCategory(postParam.getCategory());

        postRepository.save(post);
        return "redirect:/posts/" + id;
    }

    // 7. 게시글 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return "redirect:/posts";
    }
}