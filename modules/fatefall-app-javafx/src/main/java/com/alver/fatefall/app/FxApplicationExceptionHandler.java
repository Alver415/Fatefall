package com.alver.fatefall.app;

import com.alver.fatefall.app.services.DialogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class FxApplicationExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DialogService.class);

    @Autowired
    private DialogService alertService;

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        LOGGER.error("Uncaught Exception from Thread '%s'".formatted(thread.getName()), throwable);
        alertService.error("Error", throwable.getMessage(), getStackTraceAsString(throwable));
    }
    private String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
