package com.alver.fatefall.app.services;

import javafx.application.Platform;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncService {

    private static final ScheduledThreadPoolExecutor executor =
            new ScheduledThreadPoolExecutor(8, r -> {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            });

    public static void runAsync(Runnable runnable) {
        executor.submit(wrap(runnable));
    }

    public static void runAsync(Runnable runnable, long delay) {
        executor.schedule(wrap(runnable), delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Wrap the runnable in a try/catch to report the error.
     * Otherwise, it is only added to the returned Future and potentially goes unhandled.
     */
    private static Runnable wrap(Runnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        };
    }

    public static void runFx(Runnable runnable) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(runnable);
        } else {
            runnable.run();
        }
    }

}
