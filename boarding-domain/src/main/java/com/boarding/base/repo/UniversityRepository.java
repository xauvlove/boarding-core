package com.boarding.base.repo;

import com.boarding.base.entity.UniversityEntity;

import java.util.List;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午7:19
 * @Pkg PACKAGE_NAME
 * @Desc description
 */
public interface UniversityRepository {

    List<UniversityEntity> loadAll();

    Integer batchInsert(List<UniversityEntity> universities);

    Long insertSelective(UniversityEntity university);

    Integer updateSelective(UniversityEntity university);
}
