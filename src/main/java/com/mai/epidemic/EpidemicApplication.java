package com.mai.epidemic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mai.epidemic.mapper")
public class EpidemicApplication {
    public static void main(String[] args) {
        SpringApplication.run(EpidemicApplication.class, args);
    }
}
