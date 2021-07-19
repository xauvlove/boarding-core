package com.boarding.service.impl;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.boarding.api.service.SubjectService;
import com.boarding.base.entity.SubjectTreeEntity;
import com.boarding.base.repo.SubjectRepository;
import com.boarding.response.SubjectResponse;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Date 2021/06/09 20:40
 * @Author ling yue
 * @Package com.boarding.service.impl
 * @Desc
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private SubjectRepository subjectRepository;

    @Override
    public SubjectResponse query() {
        SubjectTreeEntity entity = subjectRepository.queryAndBuildTree();
        SubjectResponse response = new SubjectResponse();

        List<SubjectTreeEntity> categories = entity.getChildTreeNodes();
        for (int i = 0; i< categories.size(); i++){
            SubjectTreeEntity firstSubjects = categories.get(i);

            SubjectResponse.Category category = new SubjectResponse.Category();

            category.setCode(firstSubjects.getCode());
            category.setId(firstSubjects.getId());
            category.setName(firstSubjects.getName());
            category.setType(firstSubjects.getType());


            List<SubjectResponse.Category.FirstSubject> secondSubjectList=new ArrayList<>();

            List<SubjectTreeEntity> secondarySubjects = firstSubjects.getChildTreeNodes();
            for (int j = 0; j<secondarySubjects.size(); j++){
               SubjectTreeEntity secondSubject =secondarySubjects.get(j);
                SubjectResponse.Category.FirstSubject firstSubject=new SubjectResponse.Category.FirstSubject();
                firstSubject.setCode(secondSubject.getCode());
                firstSubject.setName(secondSubject.getName());
                firstSubject.setId(secondSubject.getId());
                firstSubject.setType(secondSubject.getType());
                category.getFirstSubjects().add(firstSubject);

            }

            response.getCategories().add(category);
        }

        response.getCategories().sort((c1, c2) -> (int) (c1.getId() - c2.getId()));
        return response;
    }
}
