package app;

import com.sun.javafx.property.PropertyReference;
import component.Component;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.DoubleStringConverter;

import java.util.Map;

public class MainController {

    @FXML
    protected Parent root;
    @FXML
    protected BorderPane templateTab;
    @FXML
    protected BorderPane cardTab;

    @FXML
    protected TableView<Component> templatePropertyTableView;
    @FXML
    protected TableView<Component> cardPropertyTableView;

    @FXML
    public void initialize() {
        reload();
        root.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode().equals(KeyCode.ENTER)) {
                reload();
            }
        });

        setupCardTableView(templatePropertyTableView);
        setupCardTableView(cardPropertyTableView);
    }

    private void setupCardTableView(TableView<Component> tableView) {
        tableView.setEditable(true);

        TableColumn<Component, String> id = createStringColumn("id");

        TableColumn<Component, Double> translateX = createDoubleColumn("translateX");
        TableColumn<Component, Double> translateY = createDoubleColumn("translateY");
        TableColumn<Component, Double> translateZ = createDoubleColumn("translateZ");

        TableColumn<Component, Double> top = createDoubleColumn("top");
        TableColumn<Component, Double> left = createDoubleColumn("right");
        TableColumn<Component, Double> bottom = createDoubleColumn("bottom");
        TableColumn<Component, Double> right = createDoubleColumn("left");
        tableView.getColumns().setAll(id, translateX, translateY, translateZ, top, left, bottom, right);

    }

    private static TableColumn<Component, Boolean> createBooleanColumn(String name) {
        PropertyReference<Boolean> propertyReference = new PropertyReference<>(Component.class, name);

        TableColumn<Component, Boolean> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(name));
        column.setCellFactory(CheckBoxTableCell.forTableColumn(column));
        column.setOnEditCommit(event -> {
            try {
                int row = event.getTablePosition().getRow();
                ObservableList<Component> items = event.getTableView().getItems();
                propertyReference.set(items.get(row), event.getNewValue());
            } catch (Exception e) {
                event.consume();
                e.printStackTrace();
            }
        });

        return column;
    }

    private static TableColumn<Component, String> createStringColumn(String name) {
        TableColumn<Component, String> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(name));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(event -> {
            try {
                int row = event.getTablePosition().getRow();
                ObservableList<Component> items = event.getTableView().getItems();
                PropertyReference<String> propertyReference = new PropertyReference<>(Component.class, name);
                propertyReference.set(items.get(row), event.getNewValue());
            } catch (Exception e) {
                event.consume();
                e.printStackTrace();
            }
        });
        return column;
    }

    private static TableColumn<Component, Double> createDoubleColumn(String name) {
        TableColumn<Component, Double> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(name));
        column.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        column.setOnEditCommit(event -> {
            try {
                int row = event.getTablePosition().getRow();
                ObservableList<Component> items = event.getTableView().getItems();
                PropertyReference<Double> propertyReference = new PropertyReference<>(Component.class, name);
                propertyReference.set(items.get(row), event.getNewValue());
            } catch (Exception e) {
                event.consume();
                e.printStackTrace();
            }
        });
        return column;
    }

    public void reload() {
        CardEditor templateEditor = new CardEditor();
        templateEditor.toggleBoxes();
        templateTab.setCenter(templateEditor);

        Map<String, Component> templateComponents = templateEditor.getComponentsMap();
        templatePropertyTableView.getItems().setAll(templateComponents.values());


        CardEditor cardEditor = new CardEditor();
        cardTab.setCenter(cardEditor);

        Map<String, Component> cardComponents = cardEditor.getComponentsMap();
        cardPropertyTableView.getItems().setAll(cardComponents.values());
        cardPropertyTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for (Map.Entry<String, Component> entry : templateComponents.entrySet()) {
            Component templateComponent = entry.getValue();
            Component cardComponent = cardComponents.get(entry.getKey());

            //TODO: Make this dynamic.
            cardComponent.translateXProperty().bindBidirectional(templateComponent.translateXProperty());
            cardComponent.translateYProperty().bindBidirectional(templateComponent.translateYProperty());
            cardComponent.translateZProperty().bindBidirectional(templateComponent.translateZProperty());

            cardComponent.topProperty().bindBidirectional(templateComponent.topProperty());
            cardComponent.rightProperty().bindBidirectional(templateComponent.rightProperty());
            cardComponent.bottomProperty().bindBidirectional(templateComponent.bottomProperty());
            cardComponent.leftProperty().bindBidirectional(templateComponent.leftProperty());

            cardComponent.blendModeProperty().bindBidirectional(templateComponent.blendModeProperty());
        }
    }
}