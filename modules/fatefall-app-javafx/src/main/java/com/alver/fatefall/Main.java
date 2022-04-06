package com.alver.fatefall;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class Main {

    public static ApplicationContext APPLICATION_CONTEXT;

    public static void main(String... args){
        try {
            APPLICATION_CONTEXT = SpringApplication.run(FatefallApplication.class);
            Application.launch(FatefallApplication.class);
        }catch (Exception e){
            throw e;
        }
    }
}
