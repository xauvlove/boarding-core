package com.boarding.base.dao;

import com.boarding.base.dal.SubjectDO;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午5:55
 * @Pkg com.boarding.base.dao
 * @Desc description
 */
public interface SubjectDAO {

    Long insertSelective(SubjectDO subjectDO);

    Integer updateSelectiveById(SubjectDO subjectDO);
}
