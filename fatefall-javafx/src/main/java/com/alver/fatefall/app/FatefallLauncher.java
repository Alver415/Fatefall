package com.alver.fatefall.app;

import com.alver.fatefall.plugin.PluginManager;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FatefallLauncher {

    public static void main(String... args) {
        Application.launch(FatefallApplication.class);
    }

    @Bean
    public PluginManager getPluginManager(){
        return new PluginManager();
    }
}