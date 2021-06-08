package com.boarding.service.impl;

import com.boarding.Constants;
import com.boarding.api.service.UniversityService;
import com.boarding.base.entity.UniversityEntity;
import com.boarding.base.repo.UniversityRepository;
import com.boarding.request.UniversityRequest;
import com.boarding.response.UniversityResponse;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午6:37
 * @Pkg com.boarding.service
 * @Desc description
 */
@Service
public class UniversityServiceImpl implements UniversityService {

    @Resource
    private UniversityRepository universityRepository;

    public UniversityResponse query(UniversityRequest universityRequest) {
        UniversityResponse response = new UniversityResponse();
        List<UniversityEntity> universities = universityRepository.loadAll();
        if (CollectionUtils.isEmpty(universities)) {
            return response;
        }
        String keyword = universityRequest.getKeyword();
        List<UniversityEntity> sortedCandidates = findAndSortUniversityNameMatch(universities, keyword);
        injectUniversityResponse(response, sortedCandidates);
        return response;
    }

    private List<UniversityEntity> findAndSortUniversityNameMatch(List<UniversityEntity> universities, String keyword) {

        if (StringUtils.isBlank(keyword)) {
            return cutProperSize(universities);
        }
        char[] words = keyword.toCharArray();

        List<UniversityEntity> matchCandidates = universities.stream().filter(universityEntity -> {
            char[] nameChars = universityEntity.getUniversityName().toCharArray();
            for (char nameChar : nameChars) {
                for (char word : words) {
                    if (nameChar == word) {
                        return true;
                    }
                }
            }
            return false;
        }).sorted((u1, u2) -> {
            char[] u1NameChars = u1.getUniversityName().toCharArray();
            char[] u2NameChars = u2.getUniversityName().toCharArray();

            int u1Score = 0;
            int u2Score = 0;

            for (char u1NameChar : u1NameChars) {
                for (char word : words) {
                    if (u1NameChar == word) {
                        u1Score++;
                    }
                }
            }
            for (char u2NameChar : u2NameChars) {
                for (char word : words) {
                    if (u2NameChar == word) {
                        u2Score++;
                    }
                }
            }
            int diff = (u2Score - u1Score);
            // 如果匹配字数相同，则按照高校名字长度排序
            // 名字越长 匹配度越低
            if (diff == 0) {
                diff = u2Score * 100 / u2NameChars.length - u2Score * 100 / u1NameChars.length;
            }
            return diff;
        }).collect(Collectors.toList());

        return cutProperSize(matchCandidates);
    }

    private List<UniversityEntity> cutProperSize(List<UniversityEntity> universities) {
        if (CollectionUtils.isEmpty(universities)) {
            return Lists.newArrayList();
        }
        if (universities.size() < Constants.pageSize) {
            return universities;
        }
        return universities.subList(0, Constants.pageSize);
    }

    private void injectUniversityResponse(UniversityResponse response, List<UniversityEntity> universities) {

        for (UniversityEntity university : universities) {
            UniversityResponse.UniversityVO vo = new UniversityResponse.UniversityVO();
            BeanUtils.copyProperties(university, vo);
            response.getUniversityVO().add(vo);
        }
    }
}
