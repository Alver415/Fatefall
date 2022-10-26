package app;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    protected Parent root;
    @FXML
    protected BorderPane templateTab;
    @FXML
    protected BorderPane cardTab;

    @FXML
    protected TableView<CardProperty> templatePropertyTableView;
    @FXML
    protected TableView<CardProperty> cardPropertyTableView;

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

    private void setupCardTableView(TableView<CardProperty> tableView) {
        TableColumn<CardProperty, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().add(idCol);

        TableColumn<CardProperty, String> valueCol = new TableColumn<>("Value");
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueCol.setCellFactory(TextFieldTableCell.forTableColumn());
        valueCol.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            ObservableList<CardProperty> items = event.getTableView().getItems();
            items.get(row).value.setValue(event.getNewValue());
        });
        tableView.getColumns().add(valueCol);
        tableView.setEditable(true);
    }

    public void reload() {
        reloadTemplatePane();
        reloadCardPane();
    }

    public void reloadTemplatePane() {
        CardEditor cardEditor = new CardEditor();
        cardEditor.toggleBoxes();
        templateTab.setCenter(cardEditor);

        templatePropertyTableView.getItems().clear();
        cardEditor.data.forEach((k, v) -> {
            v.setValue(null);
            CardProperty prop = new CardProperty();
            prop.id = k;
            prop.value = v;
            templatePropertyTableView.getItems().add(prop);
        });
    }

    public void reloadCardPane() {
        CardEditor cardEditor = new CardEditor();
        cardTab.setCenter(cardEditor);

        cardPropertyTableView.getItems().clear();
        cardEditor.data.forEach((k, v) -> {
            CardProperty prop = new CardProperty();
            prop.id = k;
            prop.value = v;
            cardPropertyTableView.getItems().add(prop);
        });
    }
}