package com.boarding.base.repository;

import com.boarding.base.dal.SubjectDO;
import com.boarding.base.dao.SubjectDAO;
import com.boarding.base.entity.SubjectEntity;
import com.boarding.base.entity.SubjectTreeEntity;
import com.boarding.base.enums.SubjectTypeEnum;
import com.boarding.base.repo.SubjectRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
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

    private static final Cache<String, SubjectEntity> subjectCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .expireAfterAccess(30, TimeUnit.DAYS)
            .softValues().build();

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
    public SubjectEntity getUnique(String subjectCode, Integer subjectType, Long parentId) {
        List<SubjectEntity> subjects = loadAll();
        String uniqueKey = StringUtils.joinWith("-", subjectCode, subjectType, parentId);
        Map<String, SubjectEntity> subjectMap = subjects.stream().collect(
                Collectors.toMap(subject -> StringUtils.joinWith("-", subject.getCode(), subject.getType(), subject.getParentId()), Function.identity()));
        return subjectMap.get(uniqueKey);
    }

    @Override
    public Integer batchInsert(List<SubjectEntity> universities) {
        List<SubjectDO> collect = universities.stream().map(this::trans2SubjectDO).collect(Collectors.toList());
        return subjectDAO.batchInsert(collect);
    }

    @Override
    public SubjectTreeEntity queryAndBuildTree() {

        // 创建根节点
        SubjectTreeEntity root = new SubjectTreeEntity();
        root.setId(0L);
        root.setType(-1);
        root.setParentId(-1L);
        root.setName("root");
        root.setCode("root");

        // 加载所有节点
        List<SubjectEntity> subjects = loadAll();
        // 全部转换为 tree 节点
        List<SubjectTreeEntity> treeNodes = subjects.stream().map(this::trans2SubjectTreeNode).collect(Collectors.toList());
        Map<Long, SubjectTreeEntity> treeMap = treeNodes.stream().collect(Collectors.toMap(SubjectTreeEntity::getId, Function.identity()));

        // 添加所有 门类 节点
        List<SubjectTreeEntity> categorySubjects = treeNodes.stream().filter(subject -> SubjectTypeEnum.CATEGORY.getType().equals(subject.getType())).collect(Collectors.toList());
        root.getChildTreeNodes().addAll(categorySubjects);

        // 添加 一级学科 节点
        List<SubjectTreeEntity> firstSubjects = treeNodes.stream().filter(subject -> SubjectTypeEnum.FIRST_SUBJECT.getType().equals(subject.getType())).collect(Collectors.toList());
        for (SubjectTreeEntity firstSubjectTreeNode : firstSubjects) {
            Long parentId = firstSubjectTreeNode.getParentId();
            SubjectTreeEntity parentSubject = treeMap.get(parentId);
            parentSubject.getChildTreeNodes().add(firstSubjectTreeNode);
        }

        // 添加 二级学科 节点
        List<SubjectTreeEntity> secondarySubjects = treeNodes.stream().filter(subject -> SubjectTypeEnum.SECONDARY_SUBJECT.getType().equals(subject.getType())).collect(Collectors.toList());
        for (SubjectTreeEntity secondarySubjectTreeNode : secondarySubjects) {
            Long parentId = secondarySubjectTreeNode.getParentId();
            SubjectTreeEntity parentSubject = treeMap.get(parentId);
            parentSubject.getChildTreeNodes().add(secondarySubjectTreeNode);
        }

        return root;
    }

    public List<SubjectEntity> loadAll() {
        return cacheLoadAll();
    }

    private List<SubjectEntity> cacheLoadAll() {
        List<SubjectEntity> subjects;
        long size = subjectCache.size();
        if (size <= 0L) {
            subjects = readFromDB();
            saveCache(subjects);
        } else {
            return new ArrayList<>(subjectCache.asMap().values());
        }
        return subjects;
    }

    private void saveCache(List<SubjectEntity> subjects) {
        if (CollectionUtils.isEmpty(subjects)){
            return;
        }
        subjectCache.invalidateAll();
        Map<String, SubjectEntity> subjectMap = subjects.stream().collect(
                Collectors.toMap(subject ->  StringUtils.joinWith("-", subject.getName(), subject.getType(), subject.getParentId()),
                        subject -> subject, (s1, s2) -> s2));
        subjectCache.putAll(subjectMap);
    }

    private List<SubjectEntity> readFromDB() {
        List<SubjectEntity> subjects = new ArrayList<>();
        for (int start = 0;;) {
            List<SubjectDO> dos = subjectDAO.limitedQuery(start, limit);
            start = start + limit;
            if (CollectionUtils.isEmpty(dos)) {
                break;
            }
            List<SubjectEntity> entities = dos.stream().map(this::transSubjectEntity).collect(Collectors.toList());
            subjects.addAll(entities);
        }
        return subjects;
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

    private SubjectTreeEntity trans2SubjectTreeNode(SubjectEntity e) {
        SubjectTreeEntity entity = new SubjectTreeEntity();
        entity.setCode(e.getCode());
        entity.setName(e.getName());
        entity.setType(e.getType());
        entity.setId(e.getId());
        entity.setParentId(e.getParentId());
        return entity;
    }
}
