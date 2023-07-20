package com.alver.fatefall.app.editor.components.path;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.File;
import java.nio.file.Path;

public class PathSelectionField extends CustomTextField {
    private FileChooser.ExtensionFilter filter;

    public PathSelectionField() {
        textProperty().bindBidirectional(pathProperty(), new StringPathConverter());

        Button button = new Button("Select");
        button.setOnAction(a -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(getPath().toFile().isDirectory() ? getPath().toFile() : getPath().getParent().toFile());
            fileChooser.setInitialFileName(getPath().toFile().isFile() ? getPath().getFileName().toString() : null);

            ObservableList<String> extensions = getExtensions();
            String description = "Extensions: %s".formatted(String.join(", ", extensions));
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(description, extensions);
            fileChooser.setSelectedExtensionFilter(filter);

            File file = fileChooser.showOpenDialog(getScene().getWindow());
            if (file != null) {
                setText(file.toString());
            }
        });
        setRight(button);
    }

    protected ListProperty<String> extensions = new SimpleListProperty<>(this, "extensions", FXCollections.observableArrayList());

    public ListProperty<String> extensionsProperty() {
        return extensions;
    }

    public ObservableList<String> getExtensions() {
        return extensions.get();
    }

    public void setExtensions(ObservableList<String> extensions) {
        this.extensions.set(extensions);
    }

    protected ObjectProperty<Path> path = new SimpleObjectProperty<>(this, "path", null);

    public ObjectProperty<Path> pathProperty() {
        return path;
    }

    public Path getPath() {
        return path.get();
    }

    public void setPath(Path path) {
        this.path.set(path);
    }
}
