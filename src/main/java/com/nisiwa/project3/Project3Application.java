package com.nisiwa.project3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.nisiwa.project3.mapper")
public class Project3Application {

    public static void main(String[] args) {
        SpringApplication.run(Project3Application.class, args);
    }

}
