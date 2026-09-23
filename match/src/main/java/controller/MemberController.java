package controller;

import domain.Member;
import repository.MemberRepository;
import service.MailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MailService mailService;

    public MemberController(MemberRepository memberRepository, MailService mailService) {
        this.memberRepository = memberRepository;
        this.mailService = mailService;
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("error", null);
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Member member, Model model) {
        if (!member.getEmail().endsWith("@shinhan.ac.kr")) {
            model.addAttribute("error", "신한대학교 이메일(@shinhan.ac.kr)만 사용 가능합니다.");
            return "members/createMemberForm";
        }

        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            model.addAttribute("error", "이미 가입된 이메일입니다.");
            return "members/createMemberForm";
        }

        memberRepository.save(member);
        return "redirect:/members/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("error", null);
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        Model model) {

        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        if (memberOptional.isEmpty() || !memberOptional.get().getPassword().equals(password)) {
            model.addAttribute("error", "이메일 또는 비밀번호가 일치하지 않습니다.");
            return "members/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", memberOptional.get());

        return "redirect:/posts";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/posts";
    }

    @PostMapping("/mail/send")
    @ResponseBody
    public String sendMail(@RequestParam("email") String email) {
        mailService.sendVerificationCode(email);
        return "ok";
    }

    @PostMapping("/mail/verify")
    @ResponseBody
    public boolean verifyMail(@RequestParam("email") String email, @RequestParam("code") String code) {
        return mailService.verifyCode(email, code);
    }
}