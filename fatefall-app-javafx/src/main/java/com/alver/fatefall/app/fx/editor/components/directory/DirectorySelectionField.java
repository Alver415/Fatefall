package com.alver.fatefall.app.fx.editor.components.directory;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.File;

public class DirectorySelectionField extends CustomTextField {

    public DirectorySelectionField() {
        textProperty().bindBidirectional(directoryProperty(), new StringDirectoryConverter());

        Button button = new Button("Select");
        button.setOnAction(a -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setInitialDirectory(getDirectory().isDirectory() ? getDirectory() : getDirectory().getParentFile());

            File directory = directoryChooser.showDialog(getScene().getWindow());
            if (directory != null) {
                setText(directory.toString());
            }
        });
        setRight(button);
    }

    protected ObjectProperty<File> directory = new SimpleObjectProperty<>(this, "directory", null);

    public ObjectProperty<File> directoryProperty() {
        return directory;
    }

    public File getDirectory() {
        return directory.get();
    }

    public void setDirectory(File directory) {
        this.directory.set(directory);
    }
}
