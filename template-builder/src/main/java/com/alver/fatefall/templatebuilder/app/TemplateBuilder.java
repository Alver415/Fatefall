package com.alver.fatefall.templatebuilder.app;

import com.alver.fatefall.templatebuilder.components.block.*;
import com.alver.fatefall.templatebuilder.components.properties.FileSelectionField;
import com.alver.fatefall.templatebuilder.components.properties.ImageSelectionEditor;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanProperty;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Properties;

public class TemplateBuilder extends Application {

    @FXML
    public BorderPane root;
    @FXML
    public FileSelectionField fxmlFileSelector;
    @FXML
    protected CardEditor editor;
    @FXML
    protected PropertySheet templateProperties;
    @FXML
    protected PropertySheet cardProperties;

    @FXML
    public void initialize() {
        fxmlFileSelector.getExtensions().add("*.fxml");
        fxmlFileSelector.setOnAction(a -> {
            reloadCard();
        });

        root.setBackground(new Background(new BackgroundImage(
                ImageUtil.getTransparencyGrid(16, 16),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        )));

        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode().equals(KeyCode.S)) {
                promptSave();
            } else if (event.isControlDown() && event.getCode().equals(KeyCode.L)) {
                promptLoad();
            }
        });

        editor.cardProperty().addListener((observable, oldValue, newValue) -> {
            updatePropertySheet();
        });
        editor.cardProperty().addListener((observable, oldValue, newValue) -> {
            updatePropertySheet();
        });
    }

    private void updatePropertySheet() {
        ObservableList<PropertySheet.Item> items = cardProperties.getItems();
        items.clear();
        for (Block<?> block : editor.getCard().getBlocks()) {
            if (block.getId() == null) {
                continue;
            }
            try {
                if (block instanceof TextBlock) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("text", TextBlock.class);
                    propertyDescriptor.setDisplayName(block.getDisplayName());
                    items.add(new BeanProperty(block, propertyDescriptor));

                } else if (block instanceof CompositeImageBlock) {
                    PropertyDescriptor firstPropertyDescriptor = new PropertyDescriptor("firstImage", CompositeImageBlock.class);
                    firstPropertyDescriptor.setPropertyEditorClass(ImageSelectionEditor.class);
                    firstPropertyDescriptor.setDisplayName(block.getDisplayName() + " - First");
                    items.add(new BeanProperty(block, firstPropertyDescriptor));

                    PropertyDescriptor secondPropertyDescriptor = new PropertyDescriptor("secondImage", CompositeImageBlock.class);
                    secondPropertyDescriptor.setPropertyEditorClass(ImageSelectionEditor.class);
                    secondPropertyDescriptor.setDisplayName(block.getDisplayName() + " - Second");
                    items.add(new BeanProperty(block, secondPropertyDescriptor));

                } else if (block instanceof ImageBlock) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("image", ImageBlock.class);
                    propertyDescriptor.setPropertyEditorClass(ImageSelectionEditor.class);
                    propertyDescriptor.setDisplayName(block.getDisplayName());
                    items.add(new BeanProperty(block, propertyDescriptor));

                } else {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("value", Block.class) {
                        @Override
                        public Class<?> getPropertyType() {
                            return String.class;
                        }
                    };
                    propertyDescriptor.setDisplayName(block.getDisplayName() + ".value");
                    items.add(new BeanProperty(block, propertyDescriptor));
                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
    }

    private void promptLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(Path.of("cards").toFile());
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
            for (Block<?> block : editor.getCard().getBlocks()) {
                String id = block.getId();
                if (id == null) {
                    continue;
                }
                try {
                    if (block instanceof TextBlock textComponent) {
                        if (properties.containsKey(id)) {
                            String value = (String) properties.get(id);
                            textComponent.setText(value);
                        }
                    } else if (block instanceof CompositeImageBlock compositeImageComponent) {
                        if (properties.containsKey(id + ".first")) {
                            String first = (String) properties.get(id + ".first");
                            compositeImageComponent.setFirstImageUrl(first);
                            String second = (String) properties.get(id + ".second");
                            compositeImageComponent.setSecondImageUrl(second);
                        }
                    } else if (block instanceof ImageBlock imageComponent) {
                        if (properties.containsKey(id)) {
                            String value = (String) properties.get(id);
                            imageComponent.setImageUrl(value);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void promptSave() {
        Properties properties = new Properties();
        for (Block<?> block : editor.getCard().getBlocks()) {
            String id = block.getId();
            if (id == null) {
                continue;
            }
            if (block instanceof TextBlock textComponent) {
                properties.put(id, textComponent.getText());
            } else if (block instanceof CompositeImageBlock compositeImageComponent) {
                properties.put(id + ".first", compositeImageComponent.getFirstImage().getUrl());
                properties.put(id + ".second", compositeImageComponent.getSecondImage().getUrl());
            } else if (block instanceof ImageBlock imageComponent) {
                properties.put(id, imageComponent.getImage() == null ? "" : imageComponent.getImage().getUrl());
            }
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(Path.of("cards").toFile());
        File file = fileChooser.showSaveDialog(root.getScene().getWindow());
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            properties.store(new FileOutputStream(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(TemplateBuilder.class.getSimpleName());

        URL fxml = TemplateBuilder.class.getResource("TemplateBuilder.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        sizeToSceneAndLockMin(stage);
        stage.centerOnScreen();
    }

    public static URL fxml;

    public void reloadCard() {
        try {
            fxml = fxmlFileSelector.getFile().toURI().toURL();
            FXMLLoader loader = new FXMLLoader(fxml);
            Card card = loader.load();
            editor.setCard(card);
            Stage stage = (Stage) editor.getScene().getWindow();
            sizeToSceneAndLockMin(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void sizeToSceneAndLockMin(Stage stage) {
        stage.sizeToScene();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

}