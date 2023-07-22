package com.alver.fatefall;

import com.alver.fatefall.app.FatefallApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FatefallLauncher {

    public static void main(String... args) {
        Application.launch(FatefallApplication.class);
    }

}