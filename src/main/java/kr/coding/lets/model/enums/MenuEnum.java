package kr.coding.lets.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuEnum {
    INTRO("학원소개"), 
    SUBJECTS("과목"), 
    NEWS("코딩뉴스"), 
    NOTICE("학원공지");

    private String name;
}
