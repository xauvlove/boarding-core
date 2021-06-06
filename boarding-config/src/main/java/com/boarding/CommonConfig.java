package com.boarding;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午5:59
 * @Pkg com.boarding
 * @Desc description
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@Import(DALConfig.class)
public class CommonConfig {
}
