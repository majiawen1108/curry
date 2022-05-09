package com.example.curry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication
@MapperScan(basePackages = "com.example.curry.mapper")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class CurryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurryApplication.class, args);
    }

}
