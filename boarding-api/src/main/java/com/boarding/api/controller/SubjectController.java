package com.boarding.api.controller;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.boarding.api.service.SubjectService;
import com.boarding.response.SubjectResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Date 2021/06/09 20:39
 * @Author ling yue
 * @Package com.boarding.api.controller
 * @Desc
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @GetMapping("/tree")
    public SubjectResponse get() {
        return subjectService.query();
    }
}
