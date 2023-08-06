package com.alver.fatefall.app.splash;

import javafx.application.Preloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PreloaderBeanPostProcessor implements BeanPostProcessor, Preloader.PreloaderNotification {

    private final ObservableMap<String, BeanState<?>> beanStateMap = FXCollections.observableHashMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        BeanState<Object> beanState = new BeanState<>();
        beanState.setBeanName(beanName);
        beanState.setBean(bean);
        beanState.setStartTime(System.currentTimeMillis());
        beanStateMap.put(beanName, beanState);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        BeanState<?> beanState = beanStateMap.get(beanName);
        beanState.setEndTime(System.currentTimeMillis());
        return bean;
    }

    public ObservableMap<String, BeanState<?>> getObservableMap() {
        return beanStateMap;
    }


}