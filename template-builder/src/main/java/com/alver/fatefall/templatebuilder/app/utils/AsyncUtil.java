package com.alver.fatefall.templatebuilder.app.utils;

import javafx.concurrent.Task;

import java.util.function.Consumer;

public class AsyncUtil {

    public static <T> void async(ThrowingSupplier<T> supplier, Consumer<T> onSuccess){
        Task<T> task = new Task<>() {
            @Override
            protected T call() throws Exception {
                return supplier.get();
            }
            @Override
            protected void succeeded() {
                onSuccess.accept(getValue());
            }
        };
        new Thread(task).start();
    }

    @FunctionalInterface
    public interface ThrowingSupplier<T> {
        T get() throws Exception;
    }


}
