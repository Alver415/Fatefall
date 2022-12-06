package com.alver.fatefall.app;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FatefallLauncher {

    public static void main(String... args) {
        Application.launch(FatefallApplication.class);
    }

}