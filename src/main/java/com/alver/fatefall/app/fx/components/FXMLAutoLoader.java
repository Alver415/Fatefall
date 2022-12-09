package com.alver.fatefall.app.fx.components;

import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadListener;
import javafx.scene.Node;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

//TODO: Implement alternative ways to configure FXMLAutoLoad fields.

@Component
public class FXMLAutoLoader implements BeanPostProcessor, ApplicationContextAware {

    protected ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        FXMLAutoLoad annotation = bean.getClass().getAnnotation(FXMLAutoLoad.class);
        if (annotation != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getLocation(bean, annotation));
            loader.setRoot(getRoot(bean, annotation));
            loader.setController(getController(bean, annotation));
            loader.setControllerFactory(getControllerFactory(bean, annotation));
            loader.setBuilderFactory(getBuilderFactory(bean, annotation));
            loader.setResources(getResources(bean, annotation));
            loader.setLoadListener(getLoadListener(bean, annotation));
            loader.setClassLoader(getClassLoader(bean, annotation));
            loader.setCharset(getCharset(bean, annotation));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bean;
    }

    private URL getLocation(Object bean, FXMLAutoLoad annotation) {
        boolean locationNotProvided = Objects.equals(annotation.location(), FXMLAutoLoad.NOT_PROVIDED);
        String location = locationNotProvided ?
                bean.getClass().getName().replace(".", "/") + ".fxml" :
                annotation.location();
        URL resource = bean.getClass().getClassLoader().getResource(location);
        return Objects.requireNonNull(resource, location);
    }

    private Object getRoot(Object bean, FXMLAutoLoad annotation) {
        return bean instanceof Node ? bean : null;
    }

    private Object getController(Object bean, FXMLAutoLoad annotation) {
        return bean;
    }

    private Callback<Class<?>, Object> getControllerFactory(Object bean, FXMLAutoLoad annotation) {
        return context::getBean;
    }

    private BuilderFactory getBuilderFactory(Object bean, FXMLAutoLoad annotation) {
        return null;
    }

    private ResourceBundle getResources(Object bean, FXMLAutoLoad annotation) {
        String baseName = bean.getClass().getName();
        try {
            return ResourceBundle.getBundle(baseName);
        } catch (MissingResourceException e) {
            Logger logger = LogManager.getLogger(FXMLLoader.class);
            logger.trace("Failed to find Resource Bundle: " + baseName, e);
            return null;
        }
    }

    private LoadListener getLoadListener(Object bean, FXMLAutoLoad annotation) {
        return null;
    }

    private ClassLoader getClassLoader(Object bean, FXMLAutoLoad annotation) {
        return bean.getClass().getClassLoader();
    }

    private Charset getCharset(Object bean, FXMLAutoLoad annotation) {
        return Charset.defaultCharset();
    }

}
