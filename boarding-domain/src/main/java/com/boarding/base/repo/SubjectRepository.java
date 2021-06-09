package com.boarding.base.repo;

import com.boarding.base.entity.SubjectEntity;
import java.util.List;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午7:19
 * @Pkg PACKAGE_NAME
 * @Desc description
 */
public interface SubjectRepository {

    Integer batchInsert(List<SubjectEntity> subjects);

    Long insertSelective(SubjectEntity subject);

    Integer updateSelective(SubjectEntity subject);
}
