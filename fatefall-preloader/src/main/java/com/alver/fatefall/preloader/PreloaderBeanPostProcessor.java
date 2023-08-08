package com.alver.fatefall.preloader;

import javafx.application.Preloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PreloaderBeanPostProcessor implements BeanPostProcessor, Preloader.PreloaderNotification {


    private final ObservableMap<String, BeanState<?>> beanStates = FXCollections.observableHashMap();

    public ObservableMap<String, BeanState<?>> getObservableBeanStates() {
        return beanStates;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (requestExit) throw new RuntimeException("Exit Requested");
        BeanState<Object> beanState = new BeanState<>();
        beanState.setBeanName(beanName);
        beanState.setBean(bean);
        beanState.setStartTime(System.currentTimeMillis());
        beanStates.put(beanName, beanState);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (requestExit) throw new RuntimeException("Exit Requested");
        BeanState<?> beanState = beanStates.get(beanName);
        beanState.setEndTime(System.currentTimeMillis());
        return bean;
    }

    private boolean requestExit = false;

    public void requestExit() {
        this.requestExit = true;
    }
}