package com.boarding.api.controller;

import com.boarding.service.UniversityService;
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

    @GetMapping("/get")
    public String get() {
        return universityService.query();
    }
}
