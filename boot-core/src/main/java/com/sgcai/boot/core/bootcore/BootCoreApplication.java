package com.sgcai.boot.core.bootcore;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@EnableDubbo
@SpringBootApplication
public class BootCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootCoreApplication.class, args);
    }

}

