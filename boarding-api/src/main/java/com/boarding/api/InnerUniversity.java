package com.boarding.api;

import lombok.Data;

/**
 * @Author ling yue
 * @Date 2021/6/8 下午7:34
 * @Pkg com.boarding.bo
 * @Desc description
 */
@Data
public class InnerUniversity {

    /**
     * 大学名
     */
    private String universityName;

    /**
     * 大学 id
     */
    private String universityId;

    /**
     * 大学 id
     */
    private String universityLocation;

    /**
     * 大学 位置 例如：北京 河北
     */
    private String universitySubordination;

    /**
     * 大学 隶属关系，比如隶属于工信部，教育部
     */
    private Boolean hasPostgraduateInstitute;

    /**
     * 大学  是否自主划线
     */
    private Boolean passingScoreBySelf;

    /**
     * 招生公告
     */
    private String universityNotation;

    /**
     * 招生简章
     */
    private String recruitRegulation;

    /**
     * 在线咨询
     */
    private String questionOnline;

    /**
     * 调剂办法
     */
    private String adjustmentRegulation;
}
