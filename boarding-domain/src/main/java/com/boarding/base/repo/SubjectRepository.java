package com.boarding.base.repo;

import com.boarding.base.entity.SubjectEntity;
import com.boarding.base.entity.SubjectTreeEntity;
import com.boarding.base.entity.UniversityEntity;

import java.util.List;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午7:19
 * @Pkg PACKAGE_NAME
 * @Desc description
 */
public interface SubjectRepository extends BaseRepository {

    Long insertSelective(SubjectEntity subject);

    Integer updateSelective(SubjectEntity subject);

    SubjectTreeEntity queryAndBuildTree();

    SubjectEntity getUniqueByCode(String subjectCode, Integer subjectType , Long parentId);

    SubjectEntity getUniqueByName(String subjectName, Integer subjectType , Long parentId);
}
