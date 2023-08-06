package com.alver.fatefall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EntityScan("com.alver.fatefall.data.entity")
@PropertySource("classpath:/fatefall-server.properties")
public class FatefallServerApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder(FatefallServerApplication.class).run();
    }

}
