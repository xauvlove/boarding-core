package com.boarding.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.boarding.api.InnerUniversity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ling yue
 * @Date 2021/6/8 下午7:32
 * @Pkg com.boarding.api.controller
 * @Desc description
 */
@RestController
@RequestMapping("/boarding/inner")
public class InnerController {

    @GetMapping("/doWriteDB")
    public String writeUniversity() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/lingyue/apps/codes/lingyue/" +
                "boarding/files/boarding-data-files-master/_2021/具有招生资格的大学.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = "";

        List<InnerUniversity> list = new ArrayList<>();
        while((line = bufferedReader.readLine())!= null) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            InnerUniversity university = JSONObject.parseObject(line, InnerUniversity.class);
            System.out.println();
        }
        return "";
    }
}
