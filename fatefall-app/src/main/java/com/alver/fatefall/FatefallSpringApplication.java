package com.alver.fatefall;

import com.alver.fatefall.app.services.FXAsyncUtils;
import com.alver.fatefall.app.splash.ApplicationProgressListener;
import com.alver.fatefall.app.splash.SplashUtil;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

@SpringBootApplication
public class FatefallSpringApplication {

    private static final Logger log = LoggerFactory.getLogger(FatefallSpringApplication.class);

    public static void main(String... args) {
        FXAsyncUtils.runAsync(() -> Application.launch(FatefallFXApplication.class));
        FatefallFXApplication application = FatefallFXApplication.waitForInstance();
        ApplicationProgressListener listener = new ApplicationProgressListener();
        SplashUtil.showSplash(listener);

        ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
            applicationContext.addApplicationListener(
                    (ApplicationListener<ApplicationReadyEvent>) event -> SplashUtil.hideSplash());
            applicationContext.registerBean(ApplicationProgressListener.class, () -> listener);
            applicationContext.registerBean(FatefallFXApplication.class, () -> application);
        };

        new SpringApplicationBuilder(FatefallSpringApplication.class)
                .initializers(initializer)
                .run();
        log.info("Fatefall Started.");
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String registeredBeans = String.join(", ", ctx.getBeanDefinitionNames());
            log.trace("Registered Beans: {}", registeredBeans);
        };
    }

    @Bean
    public ObjectMapper getObjectMapper(List<Module> modules) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(modules);
        return objectMapper;
    }

}