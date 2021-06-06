package com.boarding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author ling yue
 * @Date 2021/6/6 下午6:00
 * @Pkg com.boarding
 * @Desc description
 * mybatis 配置文件
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("com.boarding.base.dao")
public class DALConfig {}