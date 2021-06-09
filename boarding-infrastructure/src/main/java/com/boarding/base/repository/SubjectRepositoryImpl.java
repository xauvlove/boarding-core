package com.boarding.base.repository;

import com.boarding.base.dal.SubjectDO;
import com.boarding.base.dao.SubjectDAO;
import com.boarding.base.entity.SubjectEntity;
import com.boarding.base.repo.SubjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午7:21
 * @Pkg com.boarding.base.repo
 * @Desc description
 */
@Repository
public class SubjectRepositoryImpl implements SubjectRepository {

    @Resource
    private SubjectDAO subjectDAO;

    @Override
    public Long insertSelective(SubjectEntity university) {
        SubjectDO subjectDO = trans2SubjectDO(university);
        subjectDAO.insertSelective(subjectDO);
        return subjectDO.getId();
    }

    @Override
    public Integer updateSelective(SubjectEntity university) {
        return subjectDAO.updateSelectiveById(trans2SubjectDO(university));
    }

    @Override
    public Integer batchInsert(List<SubjectEntity> universities) {
        List<SubjectDO> collect = universities.stream().map(this::trans2SubjectDO).collect(Collectors.toList());
        return subjectDAO.batchInsert(collect);
    }

    private SubjectEntity transSubjectEntity(SubjectDO d) {
        SubjectEntity entity = new SubjectEntity();
        BeanUtils.copyProperties(d, entity);
        return entity;
    }

    private SubjectDO trans2SubjectDO(SubjectEntity e) {
        SubjectDO d = new SubjectDO();
        BeanUtils.copyProperties(e, d);
        return d;
    }
}
