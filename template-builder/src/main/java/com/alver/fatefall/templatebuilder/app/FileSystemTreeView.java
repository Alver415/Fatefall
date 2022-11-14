package com.alver.fatefall.templatebuilder.app;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class FileSystemTreeView extends TreeView<File> {

    protected Set<Consumer<File>> hooks = new HashSet<>();

    public Property<File> directory = new SimpleObjectProperty<>();

    private static final File[] EMPTY = new File[0];

    public FileSystemTreeView() {
        directory.addListener((observableValue, oldValue, newValue) -> {
            TreeItem<File> root = new TreeItem<>(newValue);
            setRoot(root);
            for (File file : listFiles(newValue)) {
                createTree(root, file);
            }
        });

        // Creates the cell factory.
        setCellFactory(t -> {
            TreeCell<File> cell = new TreeCell<>() {
                @Override
                public void updateItem(File item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getName());
                    setUnderline(!empty && item.getName().endsWith(".fxml"));
                }
            };
            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    File file = cell.getItem();
                    if (file != null && file.isFile()) {
                        hooks.forEach(c -> c.accept(file));
                    }
                }
            });
            return cell;
        });
    }

    private static File[] listFiles(File directory) {
        return Optional.ofNullable(directory.listFiles()).orElse(EMPTY);
    }

    public File getDirectory() {
        return directory.getValue();
    }

    public void setDirectory(File directory) {
        this.directory.setValue(directory);
    }

    public static void createTree(TreeItem<File> parent, File file) {
        if (file.isDirectory()) {
            TreeItem<File> treeItem = new TreeItem<>(file);
            parent.getChildren().add(treeItem);
            for (File child : listFiles(file)) {
                createTree(treeItem, child);
            }
        } else if (file.isFile()) {
            parent.getChildren().add(new TreeItem<>(file));
        } else {
            throw new RuntimeException("File is not directory or a file: " + file);
        }
    }


    public void addFileSelectHook(Consumer<File> hook) {
        hooks.add(hook);
    }
}
