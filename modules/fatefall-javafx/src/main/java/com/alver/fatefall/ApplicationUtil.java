package com.alver.fatefall;

import javafx.application.Platform;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ApplicationUtil {

    private static final ScheduledThreadPoolExecutor executor =
            new ScheduledThreadPoolExecutor(8, r -> {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            });

    public static void runAsync(Runnable runnable) {
        executor.submit(wrap(runnable));
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
