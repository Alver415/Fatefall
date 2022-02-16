package com.alver.fatefall.services;

import com.alver.fatefall.ApplicationExceptionHandler;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
public class AsyncService {

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    private final ScheduledThreadPoolExecutor executor =
            new ScheduledThreadPoolExecutor(8, r -> {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setUncaughtExceptionHandler(applicationExceptionHandler);
                t.setDaemon(true);
                return t;
            });

    public void runAsync(Runnable runnable) {
        executor.submit(wrap(runnable));
    }

    /**
     * Wrap the runnable in a try/catch to report the error.
     * Otherwise, it is only added to the returned Future and potentially goes unhandled.
     */
    private Runnable wrap(Runnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        };
    }

    public void runFx(Runnable runnable) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(runnable);
        } else {
            runnable.run();
        }
    }

}
