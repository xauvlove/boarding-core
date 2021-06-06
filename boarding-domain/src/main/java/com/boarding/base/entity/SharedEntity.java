package com.boarding.base.entity;

import lombok.Data;
import java.util.Date;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午7:55
 * @Pkg com.boarding.base.entity
 * @Desc description
 */
@Data
public class SharedEntity {

    /**
     *  id
     */
    private Long id;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 内容
     */
    private String content;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * created
     */
    private Date created;

    /**
     * updated
     */
    private Date updated;
}
