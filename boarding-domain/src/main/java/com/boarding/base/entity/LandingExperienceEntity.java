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
     * -
     */
    private Long id;

    /**
     * -
     */
    private Long universityId;

    /**
     * -
     */
    private String universityName;

    /**
     * -
     */
    private Integer masterType;

    /**
     * -
     */
    private String categoryCode;

    /**
     * -
     */
    private String firstSubjectCode;

    /**
     * -
     */
    private String secondarySubjectCode;

    /**
     * -
     */
    private String discipline;

    /**
     * -
     */
    private String direction;

    /**
     * -
     */
    private Integer examYear;

    /**
     * -
     */
    private Long examScore;

    /**
     * -
     */
    private Integer examRank;

    /**
     * -
     */
    private Integer reExamDate;

    /**
     * -
     */
    private Integer reExamScore;

    /**
     * -
     */
    private Integer rank;

    /**
     * -
     */
    private Long weightTotalScore;

    /**
     * -
     */
    private String contactInfo;

    /**
     * -
     */
    private String bachelorUniversityName;

    /**
     * -
     */
    private String experience;

    /**
     * -
     */
    private Date createdAt;

    /**
     * -
     */
    private Date updatedAt;
}
