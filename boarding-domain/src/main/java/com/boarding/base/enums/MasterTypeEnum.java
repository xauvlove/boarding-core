package com.boarding.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午8:15
 * @Pkg com.boarding.base.enums
 * @Desc description
 */
@Getter
@AllArgsConstructor
public enum MasterTypeEnum {

    FULL_TIME_ACADEMIC(1, "全日制学硕"),

    PART_TIME_ACADEMIC(2, "非全日制学硕"),

    FULL_PROFESSIONAL(3, "全日制专硕"),

    PART_TIME_PROFESSIONAL(4, "非全日制专硕"),

    ;

    private Integer type;

    private String desc;
}
