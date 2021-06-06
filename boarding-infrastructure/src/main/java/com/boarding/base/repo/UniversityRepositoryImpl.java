package com.boarding.base.repo;

import com.boarding.base.dal.UniversityDO;
import com.boarding.base.dao.UniversityDAO;
import com.boarding.base.entity.UniversityEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午7:21
 * @Pkg com.boarding.base.repo
 * @Desc description
 */
@Repository
public class UniversityRepositoryImpl implements UniversityRepository {

    @Resource
    private UniversityDAO universityDAO;

    @Override
    public UniversityEntity query() {
        UniversityDO universityDO = universityDAO.queryOne();
        UniversityEntity entity = new UniversityEntity();
        BeanUtils.copyProperties(universityDO, entity);
        return entity;
    }
}
