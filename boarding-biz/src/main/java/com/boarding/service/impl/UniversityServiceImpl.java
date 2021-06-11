package com.boarding.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.boarding.base.entity.RecruitmentEntity;
import com.boarding.base.entity.SubjectEntity;
import com.boarding.base.enums.ExamModeEnum;
import com.boarding.base.enums.LearningModeEnum;
import com.boarding.base.enums.MasterTypeEnum;
import com.boarding.base.enums.SubjectTypeEnum;
import com.boarding.base.repo.RecruitmentRepository;
import com.boarding.base.repo.SubjectRepository;
import com.boarding.cons.BaseConstants;
import com.boarding.api.service.UniversityService;
import com.boarding.base.entity.UniversityEntity;
import com.boarding.base.repo.UniversityRepository;
import com.boarding.cons.UniversityProjectLevel;
import com.boarding.request.UniversityRequest;
import com.boarding.response.UniversityResponse;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

    @Resource
    private SubjectRepository subjectRepository;

    @Resource
    private RecruitmentRepository recruitmentRepository;

    @Override
    public void write() {
        try {
            //File dir = new File("D:\\dev\\projects\\doing\\数据文件\\_2021\\大学研究生招生情况-学硕");
            //File dir = new File("D:\\dev\\projects\\doing\\数据文件\\_2021\\大学研究生招生情况-专硕");
            File dir = new File("");
            File[] files = dir.listFiles();
            assert files != null;
            for (File file : files) {
                String line = "";
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                while((line=bufferedReader.readLine())!=null) {
                    if (StringUtils.isBlank(line)) {
                        continue;
                    }
                    line = line.trim();
                    UniversityRecruitDetail detail = JSONObject.parseObject(line, UniversityRecruitDetail.class);
                    RecruitmentEntity recruitmentEntity = new RecruitmentEntity();
                    UniversityEntity university = universityRepository.getByUniversityName(detail.getUniversityName());
                    recruitmentEntity.setUniversityId(university.getUniversityId());
                    recruitmentEntity.setUniversityName(university.getUniversityName());
                    String learningMode = detail.getLearningMode();
                    Integer masterType = null;
                    if (learningMode.equals("全日制")) {
                        masterType =  MasterTypeEnum.FULL_TIME_ACADEMIC.getType();
                    }
                    if (learningMode.equals("非全日制")) {
                        masterType =  MasterTypeEnum.PART_TIME_ACADEMIC.getType();
                    }
                    if (masterType == null) {
                        System.out.println("---------error master type");
                        System.exit(-1);
                    }
                    recruitmentEntity.setMasterType(masterType);

                    SubjectEntity categoryByCode = subjectRepository.getUniqueByCode(detail.getFirstSubjectCode(), SubjectTypeEnum.CATEGORY.getType(), 0L);
                    recruitmentEntity.setCategoryCode(categoryByCode.getCode());
                    recruitmentEntity.setCategoryName(categoryByCode.getName());

                    String firstSubjectCode = detail.getSecondarySubjectCode();

                    SubjectEntity firstSubject = subjectRepository.getUniqueByCode(firstSubjectCode, SubjectTypeEnum.FIRST_SUBJECT.getType(), categoryByCode.getId());

                    recruitmentEntity.setFirstSubjectCode(firstSubject.getCode());
                    recruitmentEntity.setFirstSubjectName(firstSubject.getName());

                    //SubjectEntity secondarySubject = subjectRepository.getUniqueByCode(firstSubjectCode, SubjectTypeEnum.FIRST_SUBJECT.getType(), categoryByCode.getId());
                    recruitmentEntity.setSecondarySubjectCode(detail.getDisciplineCode());
                    recruitmentEntity.setSecondarySubjectName(detail.getDisciplineName());

                    recruitmentEntity.setDisciplineCode(detail.getDirectionCode());
                    recruitmentEntity.setDisciplineName(detail.getDirectionName());

                    recruitmentEntity.setInstituteCode(detail.getInstituteCode());
                    recruitmentEntity.setInstituteName(detail.getInstituteName());

                    Integer learningModeCode = null;
                    if (learningMode.equals("全日制")) {
                        learningModeCode = LearningModeEnum.FULL_TIME.getType();
                    }
                    if (learningMode.equals("非全日制")) {
                        learningModeCode = LearningModeEnum.PART_TIME.getType();
                    }
                    recruitmentEntity.setLearningMode(learningModeCode);

                    String testMode = detail.getTestMode();
                    Integer examMode = null;
                    if (testMode.equals("统考")) {
                        examMode = ExamModeEnum.NORMAL.getType();
                    }
                    if (Objects.isNull(examMode)) {
                        System.out.println("---------- exam mode error-------------");
                    }
                    recruitmentEntity.setExamMode(examMode);

                    recruitmentEntity.setExamOutlineSite(detail.getTestOutLine());
                    recruitmentEntity.setCrossSubject(detail.getCrossSubject());

                    String recruitmentNeedTest = detail.getRecruitmentNeedTest();
                    char[] recruitmentNeedTestChars = recruitmentNeedTest.toCharArray();
                    StringBuilder recruitmentNumberStr = new StringBuilder();
                    for (char recruitmentNeedTestChar : recruitmentNeedTestChars) {
                        if (recruitmentNeedTestChar >= '0' && recruitmentNeedTestChar <= '9') {
                            recruitmentNumberStr.append(recruitmentNeedTestChar);
                        }
                    }
                    Integer recruitmentNumber = Integer.parseInt(recruitmentNumberStr.toString());
                    recruitmentEntity.setRecruitmentByExam(recruitmentNumber);
                    recruitmentEntity.setRecruitmentByExamInfo(recruitmentNeedTest);

                    String[] split = recruitmentNeedTest.split("：");
                    String recruitmentMode = split[0];

                    recruitmentEntity.setRecruitmentMode(recruitmentMode);
                    recruitmentEntity.setRemark(detail.getRemark());
                    try {
                        recruitmentRepository.insertSelective(recruitmentEntity);
                    } catch (DuplicateKeyException e) {
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }
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
            int u1MatchDiffScore;
            int u2MatchDiffScore;

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
            if (matchScore != 0) {
                return matchScore;
            }

            matchScore = u2MatchScore * 100 / u2NameChars.length - u1MatchScore * 100 / u1NameChars.length;
            // 如果匹配字符还是相同的 按照学校等级进行排序
            if (matchScore != 0) {
                return matchScore;
            }

            // 按照学校等级进行排序
            if (u2.getProjectFirstClassUniversity()) {
                return UniversityProjectLevel.projectClassicUniversity;
            }
            if (u1.getProjectFirstClassUniversity()) {
                return -UniversityProjectLevel.projectClassicUniversity;
            }
            if (u2.getProjectNef()) {
                return UniversityProjectLevel.projectNEF;
            }
            if (u1.getProjectNef()) {
                return -UniversityProjectLevel.projectNEF;
            }
            if (u2.getProjectFirstClassSubject()) {
                return UniversityProjectLevel.projectClassicSubject;
            }
            if (u1.getProjectFirstClassSubject()) {
                return -UniversityProjectLevel.projectClassicSubject;
            }
            if (u2.getProjectToo()) {
                return UniversityProjectLevel.projectTOO;
            }
            if (u1.getProjectToo()) {
                return -UniversityProjectLevel.projectTOO;
            }
            return 0;
        }).collect(Collectors.toList());

        return cutProperSize(matchCandidates);
    }

    private List<UniversityEntity> cutProperSize(List<UniversityEntity> universities) {
        if (CollectionUtils.isEmpty(universities)) {
            return Lists.newArrayList();
        }
        if (universities.size() < BaseConstants.pageSize) {
            return universities;
        }
        return universities.subList(0, BaseConstants.pageSize);
    }

    private void injectUniversityResponse(UniversityResponse response, List<UniversityEntity> universities) {

        for (UniversityEntity university : universities) {
            UniversityResponse.UniversityVO vo = new UniversityResponse.UniversityVO();
            BeanUtils.copyProperties(university, vo);
            response.getUniversityVO().add(vo);
        }
    }

    @Data
    public static class UniversityRecruitDetail {

        /**
         * 大学名称
         */
        private String universityName;

        /**
         * 一级学科代码
         */
        private String firstSubjectCode;

        /**
         * 二级学科代码
         */
        private String secondarySubjectCode;

        /**
         * 考试方式：
         *      统考
         */
        private String testMode;

        /**
         * 学习方式 ：全日制 非全日制
         */
        private String learningMode;

        /**
         * 研究所名称
         */
        private String instituteName;

        /**
         * 研究所编码
         */
        private String instituteCode;

        /**
         * 专业名称
         */
        private String disciplineName;

        /**
         * 专业代码
         */
        private String disciplineCode;

        /**
         * 研究方向
         */
        private String directionName;

        private String directionCode;

        /**
         * 指导老师
         */
        private String supervisor;

        /**
         * 招生人数  不包括推免 exempt
         */
        private String recruitmentNeedTest;

        /**
         * 总招生人数
         */
        private String recruitment;

        /**
         * 考试大纲
         */
        private String testOutLine;

        /**
         * 跨专业
         */
        private String crossSubject;

        /**
         * 备注
         */
        private String remark;
    }
}
