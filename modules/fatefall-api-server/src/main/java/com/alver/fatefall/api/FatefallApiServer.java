package com.alver.fatefall.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.alver")
public class FatefallApiServer {
    public static void main(String[] args) {
        SpringApplication.run(FatefallApiServer.class, args);
    }
}