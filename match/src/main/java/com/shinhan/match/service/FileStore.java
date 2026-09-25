package com.shinhan.match.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStore {

    // 📁 파일이 실제로 저장될 컴퓨터 상의 경로 (프로젝트 루트 디렉토리 내부 uploads 폴더)
    private final String fileDir = System.getProperty("user.dir") + "/uploads/";

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    // 파일 저장 후 저장된 파일명 반환
    public String storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);

        // uploads 폴더가 없으면 자동 생성
        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 실제 지정 경로에 파일 저장
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return "/images/" + storeFileName; // 웹 브라우저 접근용 URL 경로 반환
    }

    // 파일 중복 방지를 위한 UUID 파일명 생성 (예: qwe-123.png)
    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    // 확장자 추출 (.png, .jpg)
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}