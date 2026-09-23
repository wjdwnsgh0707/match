package controller;

import domain.Member;
import domain.Post;
import repository.PostRepository;
import service.FileStore;
import jakarta.servlet.http.HttpServletRequest; // 👈 추가된 부분
import jakarta.servlet.http.HttpSession;        // 👈 추가된 부분
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final FileStore fileStore;

    public PostController(PostRepository postRepository, FileStore fileStore) {
        this.postRepository = postRepository;
        this.fileStore = fileStore;
    }

    @GetMapping
    public String list(HttpServletRequest request, Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        HttpSession session = request.getSession(false);
        if (session != null) {
            Member loginMember = (Member) session.getAttribute("loginMember");
            model.addAttribute("loginMember", loginMember);
        }

        return "posts/list";
    }

    @GetMapping("/new")
    public String createForm() {
        return "posts/createPostForm";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Post post,
                         @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = fileStore.storeFile(imageFile);
            post.setImageUrl(imageUrl);
        }

        postRepository.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id);

        if (post == null) {
            return "redirect:/posts";
        }

        model.addAttribute("post", post);
        return "posts/detail";
    }
}