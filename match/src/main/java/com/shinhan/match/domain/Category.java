package com.shinhan.match.domain;

import lombok.Getter;

@Getter
public enum Category {
    COMPETITION("공모전"),
    PROJECT("프로젝트"),
    STUDY("스터디");

    private final String description;

    Category(String description) {
        this.description = description;
    }
}