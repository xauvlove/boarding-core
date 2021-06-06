package com.boarding.service;

import com.boarding.base.repo.UniversityRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午6:37
 * @Pkg com.boarding.service
 * @Desc description
 */
@Service
public class UniversityService {

    @Resource
    private UniversityRepository universityRepository;

    public String query() {
        return universityRepository.query().toString();
    }
}
