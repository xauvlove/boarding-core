package com.boarding.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boarding.Constants;
import com.boarding.api.InnerShengfen;
import com.boarding.api.InnerUniversity;
import com.boarding.api.service.UniversityService;
import com.boarding.base.entity.UniversityEntity;
import com.boarding.base.repo.UniversityRepository;
import com.boarding.request.UniversityRequest;
import com.boarding.response.UniversityResponse;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.*;
import java.util.*;
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

    @Override
    public void write() {
        FileInputStream fileInputStreamUniversity = null;
        FileInputStream fileInputStreamLocation = null;
        try {
            fileInputStreamUniversity = new FileInputStream("D:\\dev\\projects\\doing\\数据文件\\_2021\\具有招生资格的大学.txt");
            fileInputStreamLocation = new FileInputStream("D:\\dev\\projects\\doing\\数据文件\\_2021\\省份代号.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReaderShengfen = new BufferedReader(new InputStreamReader(fileInputStreamLocation));
        String line = "";
        List<InnerShengfen> innerShengfens = new ArrayList<>();

        try {
            line = bufferedReaderShengfen.readLine();
            innerShengfens = JSONArray.parseArray(line, InnerShengfen.class);
        } catch (Exception e){

        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStreamUniversity));
        line = "";

        List<InnerUniversity> list = new ArrayList<>();

        List<UniversityEntity> universityEntities = new ArrayList<>();
        while(true) {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (StringUtils.isBlank(line)) {
                continue;
            }
            InnerUniversity university = JSONObject.parseObject(line, InnerUniversity.class);
            UniversityEntity dbUniversity = new UniversityEntity();
            dbUniversity.setUniversityId(Long.parseLong(university.getUniversityId()));
            dbUniversity.setUniversityName(university.getUniversityName());
            dbUniversity.setHasPostgraduateInstitute(university.getHasPostgraduateInstitute());
            dbUniversity.setSubordination(university.getUniversitySubordination());
            dbUniversity.setDecidePassingScoreSelf(university.getPassingScoreBySelf());
            dbUniversity.setQuestionSite(university.getQuestionOnline());
            dbUniversity.setNotationSite(university.getUniversityNotation());
            dbUniversity.setAdjustmentSite(university.getAdjustmentRegulation());
            dbUniversity.setRecruitmentSite(university.getRecruitRegulation());
            dbUniversity.setLocationName(university.getUniversityLocation());
            dbUniversity.setLocationCode(getLocationCode(innerShengfens, university.getUniversityLocation()));
            dbUniversity.setSubLocationCode("");
            dbUniversity.setSubLocationCode("");
            universityEntities.add(dbUniversity);
        }
        for (UniversityEntity universityEntity : universityEntities) {
            try {
                Long id = universityRepository.insertSelective(universityEntity);
                System.out.println(id);

            } catch (DuplicateKeyException e){
                e.printStackTrace();
            }

        }
    }

    private String getLocationCode(List<InnerShengfen> innerShengfens, String location) {
        for (InnerShengfen innerShengfen : innerShengfens) {
            if (innerShengfen.getMc().startsWith(location)){
                return innerShengfen.getDm();
            }
        }
        return "";
    }

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
            // 过滤
            List<Character> nameCharList = Chars.asList(nameChars);
            List<Character> keywordList = Chars.asList(words);
            List<Character> subtract = new ArrayList<>(CollectionUtils.subtract(keywordList, nameCharList));
            if (CollectionUtils.isNotEmpty(subtract)) {
                return false;
            }
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

            int u1MatchScore = 0;
            int u2MatchScore = 0;
            int u1MatchDiffScore = 0;
            int u2MatchDiffScore = 0;

            Set<Character> u1MatchDiffSet = new HashSet<>();
            Set<Character> u2MatchDiffSet = new HashSet<>();

            for (char u1NameChar : u1NameChars) {
                for (char word : words) {
                    if (u1NameChar == word) {
                        u1MatchDiffSet.add(word);
                        u1MatchScore++;
                    }
                }
            }
            for (char u2NameChar : u2NameChars) {
                for (char word : words) {
                    if (u2NameChar == word) {
                        u2MatchDiffSet.add(word);
                        u2MatchScore++;
                    }
                }
            }
            // 如果匹配字数相同，则按照高校名字长度排序
            // 名字越长 匹配度越低

            u1MatchDiffScore = u1MatchDiffSet.size();
            u2MatchDiffScore = u2MatchDiffSet.size();
            int matchScore = u2MatchDiffScore - u1MatchDiffScore;

            if (matchScore == 0) {
                matchScore = u2MatchScore * 100 / u2NameChars.length - u1MatchScore * 100 / u1NameChars.length;
            }
            return matchScore;
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
