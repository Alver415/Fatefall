package com.alver.fatefall.templatebuilder.app.tool;

import com.alver.fatefall.templatebuilder.components.block.Block;
import com.alver.fatefall.templatebuilder.components.block.ImageBlock;
import com.alver.fatefall.templatebuilder.components.block.TextBlock;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;

import java.util.List;
import java.util.Optional;

public class EditorToolPane extends VBox {

    public EditorToolPane() {
        paddingProperty().set(Insets.EMPTY);
        target.addListener((observable, oldValue, newValue) -> {
            rebuild();
        });
    }

    public EditorToolPane(Node target) {
        this();
        setTarget(target);
    }

    public void rebuild() {
        getChildren().clear();
        //Block Layout
        if (getTarget() instanceof Block block) {
            TitledPane layoutSection = new TitledPane("Block Layout", new HBox(
                    createTranslateControls(block),
                    createAnchorControls(block),
                    createButtonGrid(block)));
            layoutSection.setExpanded(true);
            getChildren().add(layoutSection);
        }

        //Properties
        if (getTarget() instanceof TextBlock) {
            List<String> blockProps = List.of("text");

            PropertySheet propertySheet = new PropertySheet();
            propertySheet.setPadding(Insets.EMPTY);
            getChildren().add(new TitledPane("Properties", propertySheet));
            ObservableList<PropertySheet.Item> properties = BeanPropertyUtils.getProperties(
                    getTarget(),
                    propertyDescriptor -> blockProps.contains(propertyDescriptor.getName()));
            propertySheet.getItems().addAll(properties);

        }
        if (getTarget() instanceof ImageBlock) {

            List<String> blockProps = List.of("image", "firstImage", "secondImage");

            PropertySheet propertySheet = new PropertySheet();
            propertySheet.setPadding(Insets.EMPTY);
            getChildren().add(new TitledPane("Properties", propertySheet));
            ObservableList<PropertySheet.Item> properties = BeanPropertyUtils.getProperties(getTarget())
                    .filtered(p -> blockProps.contains(p.getName()));

            for (PropertySheet.Item item : properties) {
                if (!blockProps.contains(item.getName())) {
                    return;
                }
                ObjectProperty<Image> imageProperty = (ObjectProperty<Image>) item.getObservableValue().get();
                Image image = imageProperty.get();
                StringProperty url = new SimpleStringProperty(null, "url", image == null ? null : image.getUrl());

                url.addListener(((observable, oldValue, newValue) -> {
                    imageProperty.set(new Image(newValue));
                }));

                propertySheet.getItems().add(new PropertySheet.Item() {
                    @Override
                    public Class<?> getType() {
                        return String.class;
                    }

                    @Override
                    public String getCategory() {
                        return "Category";
                    }

                    @Override
                    public String getName() {
                        return item.getName();
                    }

                    @Override
                    public String getDescription() {
                        return item.getDescription();
                    }

                    @Override
                    public Object getValue() {
                        return url.get();
                    }

                    @Override
                    public void setValue(Object value) {
                        url.set((String) value);
                    }

                    @Override
                    public Optional<ObservableValue<? extends Object>> getObservableValue() {
                        return Optional.of(url);
                    }
                });
            }
        }

//        propertySheet.getItems().addAll(properties);

//        propertySheet.getItems().addAll(BeanPropertyUtils.getProperties(getTarget()));

    }


    private PropertySheet createTranslateControls(Block block) {
        PropertySheet propertySheet = new PropertySheet();
        propertySheet.setPadding(Insets.EMPTY);
        propertySheet.setModeSwitcherVisible(false);
        propertySheet.setSearchBoxVisible(false);
        ObservableList<PropertySheet.Item> translateProps = BeanPropertyUtils.getProperties(block, p -> p.getName().startsWith("translate"));
        propertySheet.getItems().addAll(translateProps);

        return propertySheet;
    }

    private GridPane createAnchorControls(Block block) {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 5, 5, 5));
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r != 0 ^ c != 0) {
                    TextField control = new TextField();
                    control.setStyle("-fx-border:1; -fx-border-color:black;");
                    control.setMinSize(30, 30);
                    control.setMaxSize(30, 30);
                    control.setPrefSize(30, 30);

                    if (c == -1)
                        control.textProperty().bindBidirectional(block.leftProperty(), new NumberStringConverter());
                    if (c == 1)
                        control.textProperty().bindBidirectional(block.rightProperty(), new NumberStringConverter());
                    if (r == -1)
                        control.textProperty().bindBidirectional(block.topProperty(), new NumberStringConverter());
                    if (r == 1)
                        control.textProperty().bindBidirectional(block.bottomProperty(), new NumberStringConverter());

                    gridPane.add(control, c + 1, r + 1);
                }
            }
        }
        return gridPane;
    }

    private GridPane createButtonGrid(Block block) {
        GridPane gridPane = new GridPane();

        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                Button button = new Button();
                button.setMinSize(30, 30);
                button.setMaxSize(30, 30);
                button.setPrefSize(30, 30);
                final int row = r;
                final int col = c;
                button.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    if (event.isPrimaryButtonDown()) {
                        int factor = event.isControlDown() ? -1 : 1;
                        if (event.isAltDown()) {
                            if (row == 0 && col == 0) {
                                block.leftProperty().set(block.getLeft() - factor);
                                block.rightProperty().set(block.getRight() - factor);
                                block.topProperty().set(block.getTop() - factor);
                                block.bottomProperty().set(block.getBottom() - factor);
                            } else {
                                if (row == -1) {
                                    block.leftProperty().set(block.getLeft() + row * factor);
                                } else if (row == 1) {
                                    block.rightProperty().set(block.getRight() - row * factor);
                                }
                                if (col == -1) {
                                    block.topProperty().set(block.getTop() + col * factor);
                                } else if (col == 1) {
                                    block.bottomProperty().set(block.getBottom() - col * factor);
                                }
                            }
                        } else {
                            if (row == 0 && col == 0) {
                                block.translateZProperty().set(block.getTranslateZ() + factor);
                            } else {
                                block.translateXProperty().set(block.getTranslateX() + row * factor);
                                block.translateYProperty().set(block.getTranslateY() + col * factor);
                            }
                        }
                    }
                });
                gridPane.add(button, row + 1, col + 1);
            }
        }
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }

    protected ObjectProperty<Node> target = new SimpleObjectProperty<>(this, "target", null);

    public ObjectProperty<Node> targetProperty() {
        return target;
    }

    public Node getTarget() {
        return target.get();
    }

    public void setTarget(Node target) {
        this.target.set(target);
    }

}
