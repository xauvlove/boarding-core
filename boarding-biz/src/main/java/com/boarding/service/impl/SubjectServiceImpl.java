package com.boarding.service.impl;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.alibaba.fastjson.JSON;
import com.boarding.api.service.SubjectService;
import com.boarding.base.entity.SubjectTreeEntity;
import com.boarding.base.repo.SubjectRepository;
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
    public String query() {
        SubjectTreeEntity entity = subjectRepository.queryAndBuildTree();
        return JSON.toJSONString(entity);
    }
}
