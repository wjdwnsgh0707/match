package com.shinhan.match.service;

import com.shinhan.match.domain.Member;
import com.shinhan.match.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public void join(Member member) {
        // 아이디 중복 체크
        if (memberRepository.findByUsername(member.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        memberRepository.save(member);
    }

    // 로그인 검증 (아이디, 비밀번호 확인)
    public Member login(String username, String password) {
        Optional<Member> findMember = memberRepository.findByUsername(username);

        if (findMember.isPresent()) {
            Member member = findMember.get();
            // 입력한 비밀번호와 DB 저장된 비밀번호 일치 확인
            if (member.getPassword().equals(password)) {
                return member;
            }
        }
        return null; // 로그인 실패
    }
}