package com.alver.fatefall;

import com.alver.fatefall.app.services.FXAsyncUtils;
import com.alver.fatefall.app.splash.ApplicationProgressListener;
import com.alver.fatefall.app.splash.SplashService;
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


        ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {

            ApplicationProgressListener applicationProgressListener = new ApplicationProgressListener();
            FXAsyncUtils.runAsync(() -> Application.launch(FatefallFXApplication.class));
            FatefallFXApplication application = FatefallFXApplication.waitForInstance();
            SplashService splashService = new SplashService();
            splashService.showSplash(applicationProgressListener);

            ac.registerBean(ApplicationProgressListener.class, () -> applicationProgressListener);
            ac.registerBean(SplashService.class, () -> splashService);
            ac.registerBean(FatefallFXApplication.class, () -> application);
            ac.registerBean(WebAPI.class, application::getWebAPI);
            ac.registerBean(HostServices.class, application::getHostServices);
            ac.registerBean(Application.Parameters.class, application::getParameters);
        };

        SpringApplicationBuilder builder = new SpringApplicationBuilder(FatefallSpringApplication.class);
        builder.initializers(initializer);
        builder.run();

    }

    @Bean
    public ObjectMapper getObjectMapper(List<Module> modules) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(modules);
        return objectMapper;
    }
}