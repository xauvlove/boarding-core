package com.boarding.base;

import lombok.Data;

import java.util.Date;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午5:50
 * @Pkg com.boarding.base
 * @Desc description
 */
@Data
public class UniversityEntity {

    private Long id;

    private Long universityId;

    private String universityName;

    private Boolean hasPostgraduateInstitute;

    private String subordination;

    private Boolean decidePassingScoreBySelf;

    private String questionSite;

    private String notationSite;

    private String adjustmentSite;

    private String recruitmentSite;

    private String locationCode;

    private String locationName;

    private String subLocationCode;

    private String subLocationName;

    private Boolean projectNef;

    private Boolean projectToo;

    private Boolean projectFirstClassUniversity;

    private Boolean projectFirstClassSubject;

    private Date createdAt;

    private Date updatedAt;
}
