package com.boarding.base.entity;

import lombok.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午5:50
 * @Pkg com.boarding.base
 * @Desc description
 */
@Data
public class UniversityEntity {

    /**
     * 主键 id
     */
    private Long id;

    /**
     * 高校 id
     */
    private Long universityId;

    /**
     * 高校 名称
     */
    private String universityName;

    /**
     * 是否有研究生院
     */
    private Boolean hasPostgraduateInstitute;

    /**
     * 高校从属关系：从属于 交易部，工信部
     */
    private String subordination;

    /**
     * 是否自主划线
     */
    private Boolean decidePassingScoreSelf;

    /**
     * 在线咨询网站
     */
    private String questionSite;

    /**
     * 高校说明
     */
    private String notationSite;

    /**
     * 调剂信息
     */
    private String adjustmentSite;

    /**
     * 招生网站
     */
    private String recruitmentSite;

    /**
     * 高校所在地编码
     */
    private String locationCode;

    /**
     * 高校所在地名称：
     * 北京
     * 河北
     * 江苏
     * 天津
     */
    private String locationName;

    /**
     * 高校所在二级地 编码
     */
    private String subLocationCode;

    /**
     * 高校所在二级地 编码
     * 比如 武汉 石家庄
     */
    private String subLocationName;

    /**
     * 是否 985 工程院校
     */
    private Boolean projectNef;

    /**
     * 是否 211 工程院校
     */
    private Boolean projectToo;

    /**
     * 是否双一流高校
     */
    private Boolean projectFirstClassUniversity;

    /**
     * 是否双一流学科高校
     */
    private Boolean projectFirstClassSubject;

    /**
     * createdAt
     */
    private Date createdAt;

    /**
     * updatedAt
     */
    private Date updatedAt;
}
