package com.boarding.base.dal;

import com.boarding.base.enums.MasterTypeEnum;

import java.util.Date;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午8:07
 * @Pkg com.boarding.base.dal
 * @Desc description
 */
public class RecruitmentDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 高校 id
     */
    private Long universityId;

    /**
     * 高校 name
     */
    private String universityName;

    /**
     * 硕士类型
     * @see MasterTypeEnum
     */
    private Integer masterType;

    /**
     * 门类名称
     */
    private String categoryName;

    /**
     * 门类编号
     */
    private String categoryCode;

    /**
     * 一级学科名称
     */
    private String firstSubjectName;

    /**
     * 一级学科编号
     */
    private String firstSubjectCode;

    /**
     * 二级学科名称
     */
    private String secondarySubjectName;

    /**
     * 二级学科编号
     */
    private String secondarySubjectCode;

    /**
     * 专业名称
     */
    private String disciplineName;

    /**
     * 专业编号
     */
    private String disciplineCode;

    /**
     * 研究方向名称
     */
    private String directionName;

    /**
     * 研究方向编号
     */
    private String directionCode;

    /**
     * 研究所名称
     */
    private String instituteName;

    /**
     * 研究所编号
     */
    private String instituteCode;

    /**
     * 学习方式
     * 全日制、非全日制
     */
    private Integer learningMode;

    /**
     * 考试方式
     * 统考 or ？
     */
    private Integer examMode;

    /**
     * 考试大纲网站
     */
    private String examOutlineSite;

    /**
     * 跨专业情况
     */
    private String crossSubject;

    /**
     * 招生人数 减去非免
     */
    private Integer recruitmentByExam;

    /**
     * 招生人数信息
     */
    private String recruitmentByExamInfo;

    /**
     * 招生人数方式
     * 1.按照专业招生多少人
     * 2.按照研究方向招生多少人
     */
    private String recruitmentMode;

    /**
     * 备注
     */
    private String remark;

    /**
     * createdAt
     */
    private Date createdAt;

    /**
     * updatedAt
     */
    private Date updatedAt;
}
