package com.boarding.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午8:00
 * @Pkg com.boarding.base.enums
 * @Desc description
 */
@Getter
@AllArgsConstructor
public enum SubjectTypeEnum {

    CATEGORY(1, "门类"),

    FIRST_SUBJECT(2, "一级学科"),

    SECONDARY_SUBJECT(3, "二级学科"),

    DISCIPLINE(4, "专业"),

    DIRECTION(5, "研究方向"),

    ;

    private Integer code;

    private String name;
}
