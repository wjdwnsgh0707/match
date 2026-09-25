package com.shinhan.match.controller;

import com.shinhan.match.domain.Member;
import com.shinhan.match.service.EmailService;
import com.shinhan.match.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;

    // 회원가입 폼
    @GetMapping("/members/signup")
    public String signupForm() {
        return "members/signup";
    }

    // 인증번호 발송 요청 (AJAX)
    @PostMapping("/members/send-code")
    @ResponseBody
    public ResponseEntity<String> sendCode(@RequestParam("email") String email) {
        emailService.sendVerificationCode(email);
        return ResponseEntity.ok("인증번호가 발송되었습니다. (콘솔 확인)");
    }

    // 인증번호 확인 요청 (AJAX)
    @PostMapping("/members/verify-code")
    @ResponseBody
    public ResponseEntity<Boolean> verifyCode(@RequestParam("email") String email,
                                              @RequestParam("code") String code) {
        boolean isVerified = emailService.verifyCode(email, code);
        return ResponseEntity.ok(isVerified);
    }

    // 회원가입 처리
    @PostMapping("/members/signup")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         @RequestParam("nickname") String nickname) {
        Member member = new Member(username, password, email, nickname);
        memberService.join(member);
        return "redirect:/members/login";
    }

    // 로그인 폼
    @GetMapping("/members/login")
    public String loginForm() {
        return "members/login";
    }

    // 로그인 처리 (DB 유저 정보 검증)
    @PostMapping("/members/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        Member loginMember = memberService.login(username, password);

        if (loginMember == null) {
            // 로그인 실패 시 에러 메시지 전달
            model.addAttribute("loginError", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "members/login";
        }

        // 로그인 성공 시 세션에 유저 정보(닉네임 또는 아이디) 저장 후 이동
        session.setAttribute("loginMember", loginMember.getNickname());
        return "redirect:/posts";
    }

    // 로그아웃
    @GetMapping("/members/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/posts";
    }
}