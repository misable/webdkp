package com.example.webdkp;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.webdkp.dao")
@SpringBootApplication
public class WebdkpApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebdkpApplication.class, args);
    }

}
