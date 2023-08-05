package com.alver.fatefall.app.splash;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProgressListener implements BeanPostProcessor {

    private final ObservableMap<String, Long> startTime = FXCollections.observableHashMap();
    private final ObservableMap<String, Long> endTime = FXCollections.observableHashMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        startTime.put(beanName, System.currentTimeMillis());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        endTime.put(beanName, System.currentTimeMillis() - startTime.get(beanName));
        return bean;
    }

    public ObservableMap<String, Long> getObservableMap() {
        return endTime;
    }
}