package com.alver.fatefall;

import com.alver.fatefall.app.splash.ApplicationProgressListener;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpro.webapi.WebAPI;
import javafx.application.Application;
import javafx.application.HostServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

@SpringBootApplication
public class FatefallSpringApplication {

    private static final Logger log = LoggerFactory.getLogger(FatefallSpringApplication.class);

    public static void main(String... args) {

        ApplicationProgressListener applicationProgressListener = new ApplicationProgressListener();
        FatefallFXApplication.setStartupObservable(applicationProgressListener);
        FatefallFXApplication.onStart(javaFXApp -> {
            ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
                ac.registerBean(ApplicationProgressListener.class, () -> applicationProgressListener);
                ac.registerBean(WebAPI.class, javaFXApp::getWebAPI);
                ac.registerBean(FatefallFXApplication.class, () -> javaFXApp);
                ac.registerBean(HostServices.class, javaFXApp::getHostServices);
                ac.registerBean(Application.Parameters.class, javaFXApp::getParameters);
            };

            SpringApplicationBuilder builder = new SpringApplicationBuilder(FatefallSpringApplication.class);
            builder.initializers(initializer);
            builder.run();
        });

        Application.launch(FatefallFXApplication.class);
    }

    @Bean
    public ObjectMapper getObjectMapper(List<Module> modules) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(modules);
        return objectMapper;
    }
}