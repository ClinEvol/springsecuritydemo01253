package com.example.springsecuritydemo0125;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springsecuritydemo0125.mapper")
public class Springsecuritydemo0125Application {

    public static void main(String[] args) {
        SpringApplication.run(Springsecuritydemo0125Application.class, args);
    }

}
