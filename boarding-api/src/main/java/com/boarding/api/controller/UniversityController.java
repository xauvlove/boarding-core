package com.boarding.api.controller;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.boarding.api.service.SubjectService;
import com.boarding.request.UniversityRequest;
import com.boarding.response.SubjectResponse;
import com.boarding.response.UniversityResponse;
import com.boarding.api.service.UniversityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午6:09
 * @Pkg com.boarding.api.controller
 * @Desc description
 */
@RestController
@RequestMapping("/university")
public class UniversityController {

    @Resource
    private UniversityService universityService;


    @Resource
    private SubjectService subjectService;

    @GetMapping("/list/byKeywords")
    public String queryUniversityByKeyword(UniversityRequest universityRequest) {
        return JSON.toJSONString(universityService.query(universityRequest));
    }

    @GetMapping("/subjects")
    public ResponseEntity<SubjectResponse> getAllFirstDirectionNames(){

        return ResponseEntity.ok(subjectService.query());

}
}
