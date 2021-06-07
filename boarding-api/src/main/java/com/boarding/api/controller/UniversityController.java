package com.boarding.api.controller;

import com.boarding.request.UniversityRequest;
import com.boarding.response.UniversityResponse;
import com.boarding.service.UniversityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/list/byKeyWords")
    public UniversityResponse get(UniversityRequest universityRequest) {
        return universityService.query(universityRequest);
    }
}
