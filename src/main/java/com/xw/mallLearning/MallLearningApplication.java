package com.xw.mallLearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xw.mallLearning.dao"})
public class MallLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallLearningApplication.class, args);
    }

}
