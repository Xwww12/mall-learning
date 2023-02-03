package com.xw.mallLearning.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xw.mallLearning.mbg.mapper")
public class MybatisConfig {
}
