package com.alver.fatefall.templatebuilder.app;

import com.alver.fatefall.templatebuilder.components.block.*;
import com.alver.fatefall.templatebuilder.components.editor.file.FileSelectionField;
import com.alver.fatefall.templatebuilder.components.editor.image.ImageSelectionEditor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class TemplateBuilder extends Stage {

    @FXML
    public BorderPane borderPane;
    @FXML
    public FileSelectionField templateSelector;
    @FXML
    public FileSelectionField cardSelector;
    @FXML
    protected PropertySheet templateProperties;
    @FXML
    protected PropertySheet cardProperties;
    @FXML
    protected CardEditor editor;


    @FXML
    public void initialize() {
        templateSelector.getExtensions().add("*.fxml");
        templateSelector.setOnAction(a -> loadTemplate(templateSelector.getFile()));

        cardSelector.getExtensions().add("*.card");
        cardSelector.setOnAction(a -> loadCard(cardSelector.getFile()));

        borderPane.setBackground(new Background(new BackgroundImage(
                ImageUtil.getTransparencyGrid(16, 16),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        )));

        addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode().equals(KeyCode.S)) {
                promptSaveCard();
            } else if (event.isControlDown() && event.getCode().equals(KeyCode.L)) {
                promptLoadCard();
            }
        });
    }

    private ObservableList<PropertySheet.Item> updateCardProperties(Card card) {
        ObservableList<PropertySheet.Item> items = FXCollections.observableArrayList();
        for (Block<?> block : card.getBlocks()) {
            if (block.getId() == null) {
                continue;
            }
            try {
                if (block instanceof TextBlock) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("text", TextBlock.class);
                    propertyDescriptor.setDisplayName(block.getId());
                    propertyDescriptor.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                    items.add(new BeanProperty(block, propertyDescriptor));

                } else if (block instanceof CompositeImageBlock) {
                    PropertyDescriptor firstPropertyDescriptor = new PropertyDescriptor("firstImage", CompositeImageBlock.class);
                    firstPropertyDescriptor.setDisplayName(block.getId());
                    firstPropertyDescriptor.setPropertyEditorClass(ImageSelectionEditor.class);
                    firstPropertyDescriptor.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                    items.add(new BeanProperty(block, firstPropertyDescriptor));

                    PropertyDescriptor secondPropertyDescriptor = new PropertyDescriptor("secondImage", CompositeImageBlock.class);
                    firstPropertyDescriptor.setDisplayName(block.getId());
                    secondPropertyDescriptor.setPropertyEditorClass(ImageSelectionEditor.class);
                    secondPropertyDescriptor.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                    items.add(new BeanProperty(block, secondPropertyDescriptor));

                } else if (block instanceof ImageBlock) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("image", ImageBlock.class);
                    propertyDescriptor.setDisplayName(block.getId());
                    propertyDescriptor.setPropertyEditorClass(ImageSelectionEditor.class);
                    propertyDescriptor.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                    items.add(new BeanProperty(block, propertyDescriptor));

                } else {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("value", Block.class) {
                        @Override
                        public Class<?> getPropertyType() {
                            return String.class;
                        }
                    };
                    propertyDescriptor.setDisplayName(block.getId());
                    propertyDescriptor.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                    items.add(new BeanProperty(block, propertyDescriptor));
                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    private ObservableList<PropertySheet.Item> updateTemplateProperties(Card card) {
        ObservableList<PropertySheet.Item> items = FXCollections.observableArrayList();
        for (Block<?> block : card.getBlocks()) {
            if (block.getId() == null) {
                continue;
            }
            try {
                //Translations
                PropertyDescriptor translateX = new PropertyDescriptor("translateX", TextBlock.class);
                translateX.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                items.add(new BeanProperty(block, translateX));

                PropertyDescriptor translateY = new PropertyDescriptor("translateY", TextBlock.class);
                translateY.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                items.add(new BeanProperty(block, translateY));

                PropertyDescriptor translateZ = new PropertyDescriptor("translateZ", TextBlock.class);
                translateZ.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                items.add(new BeanProperty(block, translateZ));

                //Anchors
                PropertyDescriptor top = new PropertyDescriptor("top", TextBlock.class);
                top.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                items.add(new BeanProperty(block, top));

                PropertyDescriptor right = new PropertyDescriptor("right", TextBlock.class);
                right.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                items.add(new BeanProperty(block, right));

                PropertyDescriptor bottom = new PropertyDescriptor("bottom", TextBlock.class);
                bottom.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                items.add(new BeanProperty(block, bottom));

                PropertyDescriptor left = new PropertyDescriptor("left", TextBlock.class);
                left.setValue(BeanProperty.CATEGORY_LABEL_KEY, block.getDisplayName());
                items.add(new BeanProperty(block, left));
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
        return items;
    }


    private void promptLoadCard() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(Path.of("cards").toFile());
        File file = fileChooser.showOpenDialog(this);
        if (file != null) {
            loadCard(file);
        }
    }

    private void loadCard(File file) {
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
                            imageComponent.setImageUrl(value == null ? "" : value);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ObservableList<PropertySheet.Item> items = updateCardProperties(editor.getCard());
            cardProperties.getItems().setAll(items);
            lockToScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void promptSaveCard() {
        FileChooser fileChooser = new FileChooser();
        File wd = Path.of("").toAbsolutePath().toFile();
        File base = cardSelector.getFile();
        fileChooser.setInitialDirectory(base.isDirectory() ? base : wd);
        File file = fileChooser.showSaveDialog(this);
        if (file != null) {
            saveCard(file);
        }
    }

    private void saveCard(File file) {
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
                properties.put(id, imageComponent.getUrl() == null
                        ? "" : imageComponent.getUrl());
            }
        }

        try {
            if (file.exists() || file.getParentFile().mkdirs()) {
                properties.store(new FileOutputStream(file), null);
            } else {
                throw new IOException("Failed to save card.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static URL fxml;

    public void loadTemplate(File file) {
        try {
            fxml = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(fxml);
            Card template = loader.load();
            ObservableList<PropertySheet.Item> properties = updateTemplateProperties(template);
            templateProperties.getItems().setAll(properties);

            editor.setCard(template);
            lockToScene();
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void lockToScene() {
        sizeToScene();
    }

    public TemplateBuilder() throws IOException {
        super();
        URL fxml = TemplateBuilder.class.getResource("TemplateBuilder.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }
}