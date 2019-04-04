package com.sgcai.boot.api.bootapi;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class BootApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApiApplication.class, args);
    }

}

