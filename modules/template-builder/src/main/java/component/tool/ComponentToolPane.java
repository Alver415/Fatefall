package component.tool;

import component.Component;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;
import org.controlsfx.property.editor.DefaultPropertyEditorFactory;

import java.util.List;

public class ComponentToolPane extends Pane {

    private final Component component;

    public ComponentToolPane(Component component) {
        this.component = component;
        paddingProperty().set(Insets.EMPTY);

        VBox vBox = new VBox();

        //Standard Layout
        TitledPane layoutSection = new TitledPane("Standard Layout", new HBox(
                createTranslateControls(),
                createAnchorControls(),
                createButtonGrid()));
        layoutSection.setExpanded(true);
        vBox.getChildren().add(layoutSection);


        //Properties
        PropertySheet propertySheet = new PropertySheet();
        propertySheet.propertyEditorFactory().set(new DefaultPropertyEditorFactory());
        List<PropertySheet.Item> items = BeanPropertyUtils.getProperties(component);
        propertySheet.getItems().addAll(items);

        propertySheet.setPadding(Insets.EMPTY);
        vBox.getChildren().add(propertySheet);
        vBox.setMaxHeight(600);
        getChildren().add(vBox);
    }


    private VBox createTranslateControls() {
        TextField tx = new TextField();
        tx.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        tx.textProperty().bindBidirectional(component.translateXProperty(), new NumberStringConverter());

        TextField ty = new TextField();
        ty.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        ty.textProperty().bindBidirectional(component.translateYProperty(), new NumberStringConverter());

        TextField tz = new TextField();
        tz.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        tz.textProperty().bindBidirectional(component.translateZProperty(), new NumberStringConverter());

        return new VBox(tx, ty, tz);
    }

    private GridPane createAnchorControls() {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 5, 5, 5));
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r != 0 ^ c != 0) {
                    TextField control = new TextField();
                    control.setStyle("-fx-border:1; -fx-border-color:black;");
                    control.setPrefSize(30, 30);

                    if (c == -1)
                        control.textProperty().bindBidirectional(component.leftProperty(), new NumberStringConverter());
                    if (c == 1)
                        control.textProperty().bindBidirectional(component.rightProperty(), new NumberStringConverter());
                    if (r == -1)
                        control.textProperty().bindBidirectional(component.topProperty(), new NumberStringConverter());
                    if (r == 1)
                        control.textProperty().bindBidirectional(component.bottomProperty(), new NumberStringConverter());

                    gridPane.add(control, c + 1, r + 1);
                }
            }
        }
        return gridPane;
    }

    private GridPane createButtonGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 5, 5, 5));

        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                Button button = new Button();
                button.getStylesheets().add("app/grid-button.css");
                button.setPrefSize(30, 30);
                final int row = r;
                final int col = c;
                button.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    if (event.isPrimaryButtonDown()) {
                        int factor = event.isControlDown() ? -1 : 1;
                        if (event.isAltDown()) {
                            adjustAnchor(row, col, factor);
                        } else {
                            adjustPosition(row, col, factor);
                        }
                    }
                });
                gridPane.add(button, row + 1, col + 1);
            }
        }
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }

    private void adjustAnchor(int row, int col, int factor) {
        if (row == 0 && col == 0) {
            component.leftProperty().set(component.getLeft() - factor);
            component.rightProperty().set(component.getRight() - factor);
            component.topProperty().set(component.getTop() - factor);
            component.bottomProperty().set(component.getBottom() - factor);
        } else {
            if (row == -1) {
                component.leftProperty().set(component.getLeft() + row * factor);
            } else if (row == 1) {
                component.rightProperty().set(component.getRight() - row * factor);
            }
            if (col == -1) {
                component.topProperty().set(component.getTop() + col * factor);
            } else if (col == 1) {
                component.bottomProperty().set(component.getBottom() - col * factor);
            }
        }
    }

    private void adjustPosition(int row, int col, int factor) {
        if (row == 0 && col == 0) {
            component.translateZProperty().set(component.getTranslateZ() + factor);
        } else {
            component.translateXProperty().set(component.getTranslateX() + row * factor);
            component.translateYProperty().set(component.getTranslateY() + col * factor);
        }
    }

}
