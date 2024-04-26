package com.alver.fatefall.fx.core.utils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StageManager {

    private final StringProperty titleProperty;
    private final ObjectProperty<Image> iconProperty;

    @Autowired
    private StageManager(
            @Qualifier("titleProperty") StringProperty titleProperty,
            @Qualifier("iconProperty") ObjectProperty<Image> iconProperty) {
        this.titleProperty = titleProperty;
        this.iconProperty = iconProperty;
    }

    //region Node

    public Stage create(Node node) {
        return node instanceof Parent parent ?
                create(this.titleProperty, parent) :
                create(this.titleProperty, node);
    }

    public Stage create(String title, Node node) {
        SimpleStringProperty titleProperty = new SimpleStringProperty(title);
        return node instanceof Parent parent ?
                create(titleProperty, parent) :
                create(titleProperty, node);
    }

    public Stage create(StringProperty titleProperty, Node node) {
        Parent parent = new BorderPane(node);
        return create(titleProperty, parent);
    }

    //endregion
    //region Parent

    public Stage create(Parent root) {
        return create(this.titleProperty, root);
    }

    public Stage create(String title, Parent root) {
        SimpleStringProperty titleProperty = new SimpleStringProperty(title);
        return create(titleProperty, root);
    }

    public Stage create(StringProperty titleProperty, Parent root) {
        return create(titleProperty, new Scene(root));
    }

    //endregion
    //region Scene

    public Stage create(Scene scene) {
        return create(titleProperty, scene);
    }

    public Stage create(String title, Scene scene) {
        SimpleStringProperty titleProperty = new SimpleStringProperty(title);
        return create(titleProperty, scene);
    }

    public Stage create(StringProperty titleProperty, Scene scene) {
        Stage stage = new Stage();
        stage.setScene(scene);
        return create(titleProperty, stage);
    }

    //endregion
    //region Stage

    public Stage create(Stage stage) {
        return create(titleProperty, stage);
    }

    public Stage create(String title, Stage stage) {
        return create(new SimpleStringProperty(title), stage);
    }

    public Stage create(StringProperty titleProperty, Stage stage) {
        stage.titleProperty().bind(titleProperty);
        stage.getIcons().setAll(iconProperty.get());
        iconProperty.addListener(((observable, oldValue, newValue) -> stage.getIcons().setAll(iconProperty.get())));

        Screen screen = Screen.getScreens().get(Screen.getScreens().size() - 1);
        stage.setX(screen.getBounds().getMinX());
        stage.setY(screen.getBounds().getMinY());
        stage.setMaximized(true);

        stage.centerOnScreen();
        return stage;
    }

    //endregion
}
