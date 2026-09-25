package com.shinhan.match.domain;

import jakarta.persistence.*; // javax -> jakarta로 변경
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private String author; // 작성자 이름 또는 아이디

    @Enumerated(EnumType.STRING)
    private Category category; // COMPETITION, PROJECT, STUDY

    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }
}