package component;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableMap;
import javafx.css.Styleable;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.BlendMode;

import java.io.IOException;
import java.net.URL;

public interface Component extends Styleable {

    default void load() {
        String fxml = getClass().getSimpleName() + ".fxml";
        URL url = getClass().getResource(fxml);
        load(url);
    }

    default void load(URL fxml) {
        if (fxml == null) return;
        try {
            getStyleClass().add("component");

            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //region Standard Properties
    ObservableMap<Object, Object> getProperties();
    StringProperty idProperty();
    DoubleProperty viewOrderProperty();
    ObjectProperty<BlendMode> blendModeProperty();
    //endregion

    //region Layout Properties
    DoubleProperty translateXProperty();
    DoubleProperty translateYProperty();
    DoubleProperty translateZProperty();

    DoubleProperty topProperty();
    DoubleProperty rightProperty();
    DoubleProperty bottomProperty();
    DoubleProperty leftProperty();
    //endregion

    //region Default Property Implementations

    default double getTranslateX() {
        return translateXProperty().get();
    }
    default void setTranslateX(double translateX) {
        translateXProperty().set(translateX);
    }
    default double getTranslateY() {
        return translateYProperty().get();
    }
    default void setTranslateY(double translateY) {
        translateYProperty().set(translateY);
    }
    default double getTranslateZ() {
        return translateZProperty().get();
    }
    default void setTranslateZ(double translateZ) {
        translateZProperty().set(translateZ);
    }


    default Double getTop() {
        return topProperty().get();
    }
    default void setTop(Double top) {
        topProperty().set(top);
    }
    default Double getRight() {
        return rightProperty().get();
    }
    default void setRight(Double right) {
        rightProperty().set(right);
    }
    default Double getBottom() {
        return bottomProperty().get();
    }
    default void setBottom(Double bottom) {
        bottomProperty().set(bottom);
    }
    default Double getLeft() {
        return leftProperty().get();
    }
    default void setLeft(Double left) {
        leftProperty().set(left);
    }
}
