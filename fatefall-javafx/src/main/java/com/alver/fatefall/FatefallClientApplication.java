package com.alver.fatefall;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@SpringBootApplication
@PropertySource("classpath:/fatefall-client.properties")
public class FatefallClientApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder(FatefallClientApplication.class).run();
    }

    @Bean
    public ObjectMapper getObjectMapper(List<Module> modules) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(modules);
        return objectMapper;
    }

}