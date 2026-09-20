package com.shinhan.match.domain;

import java.time.LocalDateTime;

public class Post {
    private Long id;              // 게시글 고유 번호
    private String title;         // 모집글 제목
    private String content;       // 모집글 내용
    private String category;      // 구분 (공모전 / 스터디 / 프로젝트)
    private String position;      // 모집 분야 (백엔드, 프론트엔드, 기획, 디자인 등)
    private String writer;        // 작성자
    private LocalDateTime createdAt; // 작성일시

    public Post() {
    }

    public Post(Long id, String title, String content, String category, String position, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.position = position;
        this.writer = writer;
        this.createdAt = LocalDateTime.now();
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}