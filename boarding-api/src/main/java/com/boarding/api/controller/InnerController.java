package com.boarding.api.controller;

import com.boarding.api.service.UniversityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Author ling yue
 * @Date 2021/6/8 下午7:32
 * @Pkg com.boarding.api.controller
 * @Desc description
 */
@RestController
@RequestMapping("/boarding/inner")
public class InnerController {

    @Resource
    private UniversityService universityService;

    @GetMapping("/doWriteDB")
    public String writeUniversity() throws Exception {
       universityService.write();
       return "";
    }
}
