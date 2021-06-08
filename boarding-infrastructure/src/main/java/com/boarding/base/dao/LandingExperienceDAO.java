package com.boarding.base.dao;

import com.boarding.base.dal.LandingExperienceDO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午5:55
 * @Pkg com.boarding.base.dao
 * @Desc description
 */
public interface LandingExperienceDAO {

    Long insertSelective(LandingExperienceDO landingExperienceDO);

    Integer updateSelectiveById(LandingExperienceDO landingExperienceDO);

    List<LandingExperienceDO> limitedQuery(@Param("start") Integer startId, @Param("limit") Integer limit);
}
