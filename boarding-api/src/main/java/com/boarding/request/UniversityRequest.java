package com.boarding.request;

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

/**
 * @Date 2021/06/07 21:40
 * @Author ling yue
 * @Package com.boarding.request
 * @Desc
 */
@Data
public class UniversityRequest implements Serializable {

    private String keyword;
}
