package com.boarding.base.dao;

import com.boarding.base.dal.UniversityDO;
import java.util.List;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午5:55
 * @Pkg com.boarding.base.dao
 * @Desc description
 */
public interface UniversityDAO {

    List<UniversityDO> query();
}
