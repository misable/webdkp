package com.example.webdkp;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.example.webdkp.dao")
@SpringBootApplication
@EnableTransactionManagement
public class WebdkpApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebdkpApplication.class, args);
    }

}
