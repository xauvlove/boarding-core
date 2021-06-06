package com.boarding.base.enums;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Date 2021/06/06 21:59
 * @Author ling yue
 * @Package com.boarding.base.enums
 * @Desc
 */
@Getter
@AllArgsConstructor
public enum LearningModeEnum {

    FULL_TIME(1, "全日制"),

    PART_TIME(2, "非全日制"),

    ;

    private Integer type;

    private String desc;

}
