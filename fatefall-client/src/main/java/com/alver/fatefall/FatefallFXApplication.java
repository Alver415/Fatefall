package com.alver.fatefall;

import com.alver.fatefall.app.fx.component.mainstage.ApplicationController;
import com.alver.fatefall.preloader.PreloaderBeanPostProcessor;
import com.alver.springfx.SpringFXLoader;
import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

import static org.pf4j.AbstractPluginManager.MODE_PROPERTY_NAME;
import static org.pf4j.AbstractPluginManager.PLUGINS_DIR_PROPERTY_NAME;
import static org.pf4j.RuntimeMode.DEPLOYMENT;
import static org.pf4j.RuntimeMode.DEVELOPMENT;

public class FatefallFXApplication extends Application {


    @Value("${title}")
    private String title;

    private ConfigurableApplicationContext applicationContext;
    private SpringFXLoader springFXLoader;
    private StageManager stageManager;

    @Override
    public void init() {
        PreloaderBeanPostProcessor preloaderBeanPostProcessor = new PreloaderBeanPostProcessor();
        notifyPreloader(preloaderBeanPostProcessor);

        ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
            applicationContext.registerBean(FatefallFXApplication.class, () -> this);
            applicationContext.registerBean(PreloaderBeanPostProcessor.class, () -> preloaderBeanPostProcessor);
        };

        //TODO: Find a better solution.
        //Problem is that PF4j initializes before you can change the mode programmatically or via spring injection.
        //So for now, we have to do it via system properties before the bean has a chance to init.
        ApplicationListener<ApplicationEnvironmentPreparedEvent> listener = event -> {
            ConfigurableEnvironment environment = event.getEnvironment();
            boolean devProfile = Arrays.asList(environment.getActiveProfiles()).contains("dev");
            if (devProfile) {
                System.setProperty(MODE_PROPERTY_NAME, DEVELOPMENT.toString());
                System.setProperty(PLUGINS_DIR_PROPERTY_NAME, "plugin-modules");
            } else {
                System.setProperty(MODE_PROPERTY_NAME, DEPLOYMENT.toString());
                System.setProperty(PLUGINS_DIR_PROPERTY_NAME, ".wd/plugins");
            }
        };

        applicationContext = new SpringApplicationBuilder(FatefallClientApplication.class)
                .listeners(listener)
                .initializers(initializer)
                .run();
        springFXLoader = applicationContext.getBean(SpringFXLoader.class);
        stageManager = applicationContext.getBean(StageManager.class);
    }

    @Override
    public void start(Stage stage) {
        StyleManager.getInstance().addUserAgentStylesheet("/com/alver/fatefall/app/application.css");

        stage = stageManager.create((Node) springFXLoader.loadView(ApplicationController.class));
        stage.setOnCloseRequest(e -> stop());
        stage.show();

    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

}