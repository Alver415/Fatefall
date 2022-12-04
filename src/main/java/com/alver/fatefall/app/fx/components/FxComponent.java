package com.alver.fatefall.app.fx.components;

import com.alver.fatefall.app.services.AsyncService;
import javafx.fxml.FXMLLoader;

import java.net.URL;

/**
 * Utility interface to handle component spring auto-wiring and fxml loading.
 * Also provides default methods for runAsync and runFx.
 */
public interface FxComponent {

    default void initFxml() {
        initFxml(getClass());
    }

    default void initFxml(Class<?> clazz) {
        String fileName = clazz.getSimpleName() + ".fxml";
        URL fxml = clazz.getResource(fileName);
        if (fxml == null){
            throw new RuntimeException(String.format("Bad FXML for %s: %s", clazz, fileName));
        }
        initFxml(fxml);
    }

    /**
     * Must be called in each FxComponent's constructor to build the fxml node graph.
     */
    default void initFxml(URL fxml) {
        try {
            //Initialize FXML
            FXMLLoader loader = new FXMLLoader(fxml);
//            loader.setControllerFactory(new Callback<Class<?>, Object>() {
//                private boolean initialized = false;
//
//                @Override
//                public Object call(Class<?> type) {
//                    //If we haven't already assigned the controller, then use this FxComponent as the controller.
//                    if (!initialized && type.isAssignableFrom(FxComponent.this.getClass())) {
//                        //Set initialized so that we don't reuse the same controller more than once.
//                        initialized = true;
//                        return FxComponent.this;
//                    }
//                    //Otherwise, create a new controller reflectively through the no-arg constructor.
//                    try {
//                        return type.getDeclaredConstructor().newInstance();
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
