package com.gtdollar.gtbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GtbapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GtbapiApplication.class, args);
    }
}
