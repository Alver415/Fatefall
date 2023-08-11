package com.alver.fatefall.app.fx.view.console.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(LoggingUncaughtExceptionHandler.class);

    public LoggingUncaughtExceptionHandler(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        log.error("Uncaught Exception", throwable);
    }
}