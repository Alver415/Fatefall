package com.alver.fatefall.utils;

import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FXAsyncUtils {

    private static final Logger log = LoggerFactory.getLogger(FXAsyncUtils.class);

    private static final ScheduledThreadPoolExecutor executor =
            new ScheduledThreadPoolExecutor(8, r -> {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            });

    public static void runAsync(UncheckedRunnable runnable) {
        executor.submit(wrap(runnable));
    }

    public static void runAsync(UncheckedRunnable runnable, long delay) {
        executor.schedule(wrap(runnable), delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Wrap the runnable in a try/catch to report the error.
     * Otherwise, it is only added to the returned Future and potentially goes unhandled.
     */
    private static Runnable wrap(UncheckedRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw e;
            }
        };
    }

    public static void runFx(UncheckedRunnable runnable) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(runnable);
        } else {
            runnable.run();
        }
    }

    @FunctionalInterface
    public interface UncheckedRunnable extends Runnable {
        default void run(){
            try {
                runUnchecked();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        void runUnchecked() throws Exception;
    }

}
