package com.boarding.response;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/06/07 22:06
 * @Author ling yue
 * @Package com.boarding.response
 * @Desc
 */
@Data
public class UniversityResponse implements Serializable {

    private List<UniversityVO> universityVO = new ArrayList<>();

    @Data
    public static class UniversityVO implements Serializable {
        /**
         * 高校 id
         */
        private Long universityId;

        /**
         * 高校 名称
         */
        private String universityName;

        /**
         * 是否有研究生院
         */
        private Boolean hasPostgraduateInstitute;

        /**
         * 高校从属关系：从属于 交易部，工信部
         */
        private String subordination;

        /**
         * 是否自主划线
         */
        private Boolean decidePassingScoreBySelf;

        /**
         * 在线咨询网站
         */
        private String questionSite;

        /**
         * 高校说明
         */
        private String notationSite;

        /**
         * 调剂信息
         */
        private String adjustmentSite;

        /**
         * 招生网站
         */
        private String recruitmentSite;

        /**
         * 高校所在地编码
         */
        private String locationCode;

        /**
         * 高校所在地名称：
         * 北京
         * 河北
         * 江苏
         * 天津
         */
        private String locationName;

        /**
         * 高校所在二级地 编码
         */
        private String subLocationCode;

        /**
         * 高校所在二级地 编码
         * 比如 武汉 石家庄
         */
        private String subLocationName;

        /**
         * 是否 985 工程院校
         */
        private Boolean projectNef;

        /**
         * 是否 211 工程院校
         */
        private Boolean projectToo;

        /**
         * 是否双一流高校
         */
        private Boolean projectFirstClassUniversity;

        /**
         * 是否双一流学科高校
         */
        private Boolean projectFirstClassSubject;
    }
}
