package com.boarding.base.dao;

import com.boarding.base.dal.SharedDO;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午5:55
 * @Pkg com.boarding.base.dao
 * @Desc description
 */
public interface SharedDAO {

    Long insertSelective(SharedDO sharedDO);

    Integer updateSelectiveById(SharedDO sharedDO);
}
