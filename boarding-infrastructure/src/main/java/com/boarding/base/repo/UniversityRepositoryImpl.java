package com.boarding.base.repo;

import com.boarding.base.dal.UniversityDO;
import com.boarding.base.dao.UniversityDAO;
import com.boarding.base.entity.UniversityEntity;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.collections.CollectionUtils;
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
public class UniversityRepositoryImpl implements UniversityRepository {

    private static final Integer limit = 300;

    @Resource
    private UniversityDAO universityDAO;

    private static final Cache<String, UniversityEntity> universityCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .expireAfterAccess(30, TimeUnit.DAYS)
            .softValues().build();

    @Override
    public UniversityEntity query() {
        return new UniversityEntity();
    }

    @Override
    public List<UniversityEntity> loadAll() {
        return cacheLoadAll();
    }

    private List<UniversityEntity> cacheLoadAll() {
        List<UniversityEntity> universities;
        long size = universityCache.size();
        if (size <= 0L){
            universities = readFromDB();
            saveCache(universities);
        } else {
            return new ArrayList<>(universityCache.asMap().values());
        }
        return loadAll();
    }

    private void saveCache(List<UniversityEntity> universities) {
        Map<String, UniversityEntity> universityMap = universities.stream().collect(
                Collectors.toMap(UniversityEntity::getUniversityName, Function.identity(), (u1, u2) -> u2));
        universityCache.putAll(universityMap);
    }

    private List<UniversityEntity> readFromDB() {
        List<UniversityEntity> universities = new ArrayList<>();
        for (int start = 0;;) {
            List<UniversityDO> dos = universityDAO.limitedQuery(start, limit);
            if (CollectionUtils.isEmpty(dos)) {
                break;
            }
            List<UniversityEntity> entities = dos.stream().map(this::trans2UniversityEntity).collect(Collectors.toList());
            universities.addAll(entities);
        }
        return universities;
    }

    private UniversityEntity trans2UniversityEntity(UniversityDO d) {
        UniversityEntity entity = new UniversityEntity();
        BeanUtils.copyProperties(d, entity);
        return entity;
    }
}
