package com.alver.fatefall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/fatefall-jnlp.properties")
public class JnlpServerApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder(JnlpServerApplication.class).run();
    }

}
