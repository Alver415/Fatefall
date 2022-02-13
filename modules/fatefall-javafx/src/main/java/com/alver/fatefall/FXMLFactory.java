package com.alver.fatefall;

import javafx.util.Builder;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.Callable;

public class FXMLFactory implements Callback<Class<?>, Object>, BuilderFactory {

    @Autowired
    ApplicationContext context;

    @Override
    public Builder<?> getBuilder(Class<?> type) {
        return new Builder<Object>() {
            @Override
            public Object build() {
                return context.getBean(type);
            }
        };
    }
    @Override
    public Object call(Class<?> type) {
        return context.getBean(type);
    }
}
