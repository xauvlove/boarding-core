package com.boarding.response;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @Date 2021/06/07 22:06
 * @Author ling yue
 * @Package com.boarding.response
 * @Desc
 */
@Data
public class SubjectResponse implements Serializable {


    private List<Category> categories = new ArrayList<>();

    @Data
    public static class Category {

        /**
         * id
         */
        private Long id;

        /**
         * 学科名称
         */
        private String name;

        /**
         * 学科代码、编号
         */
        private String code;

        /**
         * 学科类型
         */
        private Integer type;

        private List<FirstSubject> firstSubjects = new ArrayList<>();

       @Data
       public static class FirstSubject {

           /**
            * id
            */
           private Long id;

           /**
            * 学科名称
            */
           private String name;

           /**
            * 学科代码、编号
            */
           private String code;

           /**
            * 学科类型
            */
           private Integer type;
       }
   }
}
