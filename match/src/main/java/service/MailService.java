package service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MailService {

    // 이메일별 생성된 인증번호 임시 저장소 (메모리)
    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();

    // 6자리 난수 인증번호 생성 및 콘솔 출력 (Mock)
    public String sendVerificationCode(String email) {
        // 6자리 랜덤 숫자 생성
        Random random = new Random();
        String code = String.format("%06d", random.nextInt(1000000));

        // 저장소에 이메일 - 인증번호 매핑
        verificationCodes.put(email, code);

        // 🎯 실제 메일 발송 대신 콘솔창에 안전하게 출력!
        System.out.println("==========================================");
        System.out.println("[MOCK EMAIL SERVICE] 학교 이메일 인증번호 발송");
        System.out.println("수신 이메일: " + email);
        System.out.println("인증번호: " + code);
        System.out.println("==========================================");

        return code;
    }

    // 인증번호 검증
    public boolean verifyCode(String email, String inputCode) {
        String savedCode = verificationCodes.get(email);
        if (savedCode != null && savedCode.equals(inputCode)) {
            // 인증 성공 시 일회성이므로 삭제
            verificationCodes.remove(email);
            return true;
        }
        return false;
    }
}