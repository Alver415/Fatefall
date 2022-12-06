package com.alver.fatefall.app.fx.components;

import com.alver.fatefall.app.services.AsyncService;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadListener;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility interface to handle component spring auto-wiring and fxml loading.
 * Also provides default methods for runAsync and runFx.
 */
public interface FXMLLoadable {

    /**
     * Must be called in each FxComponent's constructor to build the fxml node graph.
     */
    default void loadFxml() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getLocation());
            loader.setRoot(getRoot());
            loader.setController(getController());
            loader.setControllerFactory(getControllerFactory());
            loader.setBuilderFactory(getBuilderFactory());
            loader.setResources(getResources());
            loader.setLoadListener(getLoadListener());
            loader.setClassLoader(getClassLoader());
            loader.setCharset(getCharset());
            loader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default URL getLocation() {
        return getClass().getResource(getClass().getSimpleName() + ".fxml");
    }

    default Object getRoot() {
        return this;
    }

    default Object getController() {
        return this;
    }

    default Callback<Class<?>, Object> getControllerFactory() {
        return null;
    }

    default ResourceBundle getResources() {
        String baseName = getClass().getName();
        try {
            return ResourceBundle.getBundle(baseName);
        } catch (MissingResourceException e) {
            Logger logger = LogManager.getLogger(FXMLLoadable.class);
            logger.trace("Failed to load Resource Bundle: " + baseName, e);
            return null;
        }
    }

    default ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }

    default BuilderFactory getBuilderFactory() {
        return null;
    }

    default Charset getCharset() {
        return Charset.defaultCharset();
    }

    default LoadListener getLoadListener() {
        return null;
    }


    default void runAsync(Runnable runnable) {
        AsyncService.runAsync(runnable);
    }

    default void runAsync(Runnable runnable, long delay) {
        AsyncService.runAsync(runnable, delay);
    }

    default void runFx(Runnable runnable) {
        AsyncService.runFx(runnable);
    }

}
