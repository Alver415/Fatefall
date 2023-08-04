package com.alver.fatefall;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FatefallSpringApplication {

    public static void main(String... args) {
        Application.launch(FatefallFXApplication.class);
    }

    @Bean
    public ObjectMapper getObjectMapper(List<Module> modules) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(modules);
        return objectMapper;
    }
}