package com.boarding.api.service;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.boarding.request.UniversityRequest;
import com.boarding.response.UniversityResponse;

/**
 * @Date 2021/06/07 22:45
 * @Author ling yue
 * @Package com.boarding.service
 * @Desc
 */
public interface UniversityService {

    UniversityResponse query(UniversityRequest universityRequest);
}
