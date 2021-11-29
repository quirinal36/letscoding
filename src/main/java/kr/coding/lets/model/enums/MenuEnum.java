package kr.coding.lets.model.enums;

import lombok.Getter;

@Getter
public enum MenuEnum {
    INTRO("학원소개", "/intro/"),
    SUBJECTS("과목", "/subjects/"),
    NEWS("코딩뉴스", "/news/"),
    NOTICE("학원공지", "/notice/");

    private String name;
    private String uri;

    MenuEnum(String name, String uri){
        this.name = name;
        this.uri = uri;
    }
}
