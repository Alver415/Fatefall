package app;

import com.sun.javafx.property.PropertyReference;
import components.BaseComponent;
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
    protected TableView<BaseComponent> templatePropertyTableView;
    @FXML
    protected TableView<BaseComponent> cardPropertyTableView;

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

    private void setupCardTableView(TableView<BaseComponent> tableView) {
        tableView.setEditable(true);

        TableColumn<BaseComponent, Boolean> selected = createBooleanColumn("selected");
        TableColumn<BaseComponent, String> id = createStringColumn("id");
        TableColumn<BaseComponent, String> value = createStringColumn("value");

        TableColumn<BaseComponent, Double> translateX = createDoubleColumn("translateX");
        TableColumn<BaseComponent, Double> translateY = createDoubleColumn("translateY");
        TableColumn<BaseComponent, Double> translateZ = createDoubleColumn("translateZ");

        TableColumn<BaseComponent, Double> top = createDoubleColumn("top");
        TableColumn<BaseComponent, Double> left = createDoubleColumn("right");
        TableColumn<BaseComponent, Double> bottom = createDoubleColumn("bottom");
        TableColumn<BaseComponent, Double> right = createDoubleColumn("left");
        tableView.getColumns().setAll(selected, id, value, translateX, translateY, translateZ, top, left, bottom, right);

    }

    private static TableColumn<BaseComponent, Boolean> createBooleanColumn(String name) {
        PropertyReference<Boolean> propertyReference = new PropertyReference<>(BaseComponent.class, name);

        TableColumn<BaseComponent, Boolean> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(name));
        column.setCellFactory(CheckBoxTableCell.forTableColumn(column));
        column.setOnEditCommit(event -> {
            try {
                int row = event.getTablePosition().getRow();
                ObservableList<BaseComponent> items = event.getTableView().getItems();
                propertyReference.set(items.get(row), event.getNewValue());
            } catch (Exception e) {
                event.consume();
                e.printStackTrace();
            }
        });

        return column;
    }

    private static TableColumn<BaseComponent, String> createStringColumn(String name) {
        TableColumn<BaseComponent, String> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(name));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(event -> {
            try {
                int row = event.getTablePosition().getRow();
                ObservableList<BaseComponent> items = event.getTableView().getItems();
                PropertyReference<String> propertyReference = new PropertyReference<>(BaseComponent.class, name);
                propertyReference.set(items.get(row), event.getNewValue());
            } catch (Exception e) {
                event.consume();
                e.printStackTrace();
            }
        });
        return column;
    }

    private static TableColumn<BaseComponent, Double> createDoubleColumn(String name) {
        TableColumn<BaseComponent, Double> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(name));
        column.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        column.setOnEditCommit(event -> {
            try {
                int row = event.getTablePosition().getRow();
                ObservableList<BaseComponent> items = event.getTableView().getItems();
                PropertyReference<Double> propertyReference = new PropertyReference<>(BaseComponent.class, name);
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

        Map<String, BaseComponent> templateComponents = templateEditor.getComponentsMap();
        templatePropertyTableView.getItems().setAll(templateComponents.values());


        CardEditor cardEditor = new CardEditor();
        cardTab.setCenter(cardEditor);

        Map<String, BaseComponent> cardComponents = cardEditor.getComponentsMap();
        cardPropertyTableView.getItems().setAll(cardComponents.values());
        cardPropertyTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (BaseComponent component : cardPropertyTableView.getItems()){
            component.selectedProperty().addListener((obs, o, n) ->{
                if (n) {
                    cardPropertyTableView.getSelectionModel().select(component);
                } else {
                    int index = cardPropertyTableView.getItems().indexOf(component);
                    cardPropertyTableView.getSelectionModel().clearSelection(index);
                }
            });
        }

        for (Map.Entry<String, BaseComponent> entry : templateComponents.entrySet()) {
            BaseComponent templateComponent = entry.getValue();
            BaseComponent cardComponent = cardComponents.get(entry.getKey());

            //TODO: Make this dynamic.
            cardComponent.selectedProperty().bindBidirectional(templateComponent.selectedProperty());
            cardComponent.valueProperty().bindBidirectional(templateComponent.valueProperty());

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