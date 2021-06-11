package com.boarding.base.repository;

import com.boarding.base.dal.RecruitmentDO;
import com.boarding.base.dao.RecruitmentDAO;
import com.boarding.base.entity.RecruitmentEntity;
import com.boarding.base.repo.RecruitmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午7:21
 * @Pkg com.boarding.base.repo
 * @Desc description
 */
@Repository
public class RecruitmentRepositoryImpl implements RecruitmentRepository {

    @Resource
    private RecruitmentDAO recruitmentDAO;

    @Override
    public Long insertSelective(RecruitmentEntity recruitment) {
        RecruitmentDO d = trans2RecruitmentDO(recruitment);
        recruitmentDAO.insertSelective(d);
        return d.getId();
    }

    @Override
    public Integer updateSelective(RecruitmentEntity recruitment) {
        return recruitmentDAO.updateSelectiveById(trans2RecruitmentDO(recruitment));
    }

    private RecruitmentEntity trans2RecruitmentEntity(RecruitmentDO d) {
        RecruitmentEntity entity = new RecruitmentEntity();
        BeanUtils.copyProperties(d, entity);
        return entity;
    }

    private RecruitmentDO trans2RecruitmentDO(RecruitmentEntity entity) {
        RecruitmentDO d = new RecruitmentDO();
        BeanUtils.copyProperties(entity, d);
        return d;
    }
}
