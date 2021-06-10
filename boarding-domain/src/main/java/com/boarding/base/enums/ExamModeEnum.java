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
public enum ExamModeEnum {

    NORMAL(1, "统考"),

    ;

    private Integer type;

    private String desc;

}
