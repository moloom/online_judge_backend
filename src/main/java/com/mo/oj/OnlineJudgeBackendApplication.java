package com.mo.oj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class OnlineJudgeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineJudgeBackendApplication.class, args);
    }

}
