package com.alver.fatefall.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApplicationExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationExceptionHandler.class);

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        LOGGER.error("Uncaught Exception from Thread '%s'".formatted(thread.getName()), throwable);
    }
}
