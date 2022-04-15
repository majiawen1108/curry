package com.example.curry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.curry.mapper")
public class CurryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurryApplication.class, args);
    }

}
