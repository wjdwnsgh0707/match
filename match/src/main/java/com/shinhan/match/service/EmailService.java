package com.shinhan.match.service;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailService {

    // 메모리 내 인증번호 저장소 (이메일, 인증번호)
    private final ConcurrentHashMap<String, String> verificationCodes = new ConcurrentHashMap<>();

    // 6자리 난수 인증번호 생성 및 콘솔 출력
    public String sendVerificationCode(String email) {
        Random random = new Random();
        String code = String.format("%06d", random.nextInt(1000000));

        // 저장소에 저장
        verificationCodes.put(email, code);

        // 콘솔창 출력
        System.out.println("==================================================");
        System.out.println("[인증번호 발송] 이메일: " + email + " | 인증번호: " + code);
        System.out.println("==================================================");

        return code;
    }

    // 인증번호 검증
    public boolean verifyCode(String email, String code) {
        String savedCode = verificationCodes.get(email);
        if (savedCode != null && savedCode.equals(code)) {
            verificationCodes.remove(email); // 인증 성공 시 제거
            return true;
        }
        return false;
    }
}