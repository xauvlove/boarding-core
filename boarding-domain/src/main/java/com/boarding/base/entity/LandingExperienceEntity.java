package com.boarding.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author ling yue
 * @Date 2021/6/8 上午9:59
 * @Pkg com.boarding.base.dal
 * @Desc description
 */
@Data
public class LandingExperienceEntity {

    /**
     * - id
     */
    private Long id;

    /**
     * - universityId
     */
    private Long universityId;

    /**
     * - universityName
     */
    private String universityName;

    /**
     * -
     * @see MasterTypeEnum
     */
    private Integer masterType;

    /**
     * - 门类
     */
    private String categoryCode;

    /**
     * - 一级学科编号
     */
    private String firstSubjectCode;

    /**
     * - 二级学科编号
     */
    private String secondarySubjectCode;

    /**
     * - 专业
     */
    private String discipline;

    /**
     * - 研究方向
     */
    private String direction;

    /**
     * - 考研届 如 2021
     */
    private Integer examYear;

    /**
     * - 初试分数 * 100
     */
    private Long examScore;

    /**
     * - 初试排名
     */
    private Integer examRank;

    /**
     * - 复试时间 ：20210322
     */
    private Integer reExamDate;

    /**
     * - 复试分数
     */
    private Integer reExamScore;

    /**
     * - 总排名 初试+复试 加权排名
     */
    private Integer rank;

    /**
     * - 总排名 初试+复试 加权总分数
     */
    private Long weightTotalScore;

    /**
     * - 联系方式
     */
    private String contactInfo;

    /**
     * - 本科学校名称
     */
    private String bachelorUniversityName;

    /**
     * - 经验分享
     */
    private String experience;

    /**
     * - createdAt
     */
    private Date createdAt;

    /**
     * - updatedAt
     */
    private Date updatedAt;
}
