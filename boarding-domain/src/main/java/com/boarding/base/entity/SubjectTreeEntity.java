package com.boarding.base.entity;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.boarding.base.enums.SubjectTypeEnum;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/06/09 20:04
 * @Author ling yue
 * @Package com.boarding.base.entity
 * @Desc
 */
@Data
public class SubjectTreeEntity {

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
     * @see SubjectTypeEnum
     */
    private Integer type;

    /**
     * parentId
     */
    private Long parentId;

    /**
     * 子学科
     */
    private List<SubjectTreeEntity> childTreeNodes = new ArrayList<>();
}
