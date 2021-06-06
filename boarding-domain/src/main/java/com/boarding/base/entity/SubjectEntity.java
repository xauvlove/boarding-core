package com.boarding.base.entity;

import com.boarding.base.enums.SubjectTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午7:55
 * @Pkg com.boarding.base.entity
 * @Desc description
 */
@Data
public class SubjectEntity {

    /**
     *  id
     */
    private Long id;

    /**
     * 学科名称
     */
    private String name;

    /**
     * 学科代码、编号
     */
    private String code;

    /**
     * 学科类型
     * @see SubjectTypeEnum
     */
    private Integer type;

    /**
     * 父学科 id
     */
    private Long parentId;

    /**
     * created
     */
    private Date created;

    /**
     * updated
     */
    private Date updated;
}
