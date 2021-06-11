package com.boarding.base.repo;

import com.boarding.base.entity.RecruitmentEntity;
import com.boarding.base.entity.SubjectEntity;
import com.boarding.base.entity.SubjectTreeEntity;

import java.util.List;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午7:19
 * @Pkg PACKAGE_NAME
 * @Desc description
 */
public interface RecruitmentRepository extends BaseRepository {

    Long insertSelective(RecruitmentEntity recruitment);

    Integer updateSelective(RecruitmentEntity recruitment);
}
