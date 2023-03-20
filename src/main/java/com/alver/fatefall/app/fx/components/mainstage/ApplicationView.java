package com.alver.fatefall.app.fx.components.mainstage;

import com.alver.fatefall.api.interfaces.ActionEventHandler;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.interfaces.WorkspaceView;
import com.alver.fatefall.api.models.Element;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.Workspace;
import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import com.alver.fatefall.app.fx.components.settings.FatefallPreferences;
import com.alver.fatefall.app.persistence.repositories.WorkspaceRepository;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@FXMLAutoLoad
@Component
public class ApplicationView extends BorderPane {

    @Autowired
    protected PluginManager pluginManager;
    @Autowired
    protected ComponentFactory componentFactory;
    @Autowired
    protected ObservableList<Workspace> workspaceList;
    @Autowired
    protected WorkspaceRepository workspaceRepository;
    @Autowired
    protected FatefallPreferences preferences;


    /**
     * FXML Injection
     */
    @FXML
    protected ListView<Workspace> workspaceListView;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected Menu pluginMenu;

    @FXML
    private void initialize() {
        workspaceListView.setCellFactory(workspaceCellFactory);
        workspaceListView.setItems(workspaceList);

        List<Menu> menuList = pluginManager.getPlugins().stream().map(this::buildMenu).toList();
        pluginMenu.getItems().setAll(menuList);
        MenuItem testItem = new MenuItem("Test");
        testItem.setOnAction(a -> {
            workspaceRepository.findAll().forEach(workspaceList::add);
        });
        pluginMenu.getItems().add(testItem);
    }

    private Menu buildMenu(PluginWrapper plugin) {
        Menu menu = new Menu(plugin.getPluginId());
        List<MenuItem> menuItems = pluginManager
                .getExtensions(ActionEventHandler.class, plugin.getPluginId())
                .stream().map(this::buildMenuItem).toList();
        menu.getItems().setAll(menuItems);
        return menu;
    }

    private MenuItem buildMenuItem(ActionEventHandler action) {
        MenuItem menuItem = new MenuItem(action.getName());
        menuItem.setOnAction(action);
        return menuItem;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public ListView<Workspace> getWorkspaceList() {
        return workspaceListView;
    }

    private void createTab(String text, Node node) {
        Tab tab = new Tab();
        tab.setText(text);
        tab.setContent(node);
        tabPane.getTabs().add(tab);
    }

    @FXML
    private void openPreferences() {
        preferences.show();
    }

    private Tab addCollectionTab(Workspace workspace) {
        WorkspaceView<?> workspaceView = componentFactory.buildWorkspaceView();
        workspaceView.setWorkspace(workspace);

        Tab tab = new Tab(workspace.getName());
        tab.setContent(workspaceView.getFxViewNode());

        tabPane.getTabs().add(tab);
        return tab;
    }

    @FXML
    private void openCreateCollectionDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Create a Collection");
        dialog.setContentText("Enter a name for the new collection.");
        dialog.showAndWait().ifPresent(name -> {
            boolean nameAlreadyExists = workspaceListView.getItems().stream()
                    .map(Workspace::getName)
                    .anyMatch(n -> Objects.equals(n, name));
            if (nameAlreadyExists) {
                throw new RuntimeException("A collection with that name already exists.");
            }
            Workspace workspace = new Workspace();
            workspace.setName(name);
            workspaceListView.getItems().add(workspace);
        });
    }


    @FXML
    private void createCard() {
        Workspace selectedItem = workspaceListView.getSelectionModel().getSelectedItem();
        Card card = new Card();
        Element attribute = new Element();
        attribute.setName("attr_1");
        attribute.setValue("This is an Attribute.");
        card.addElement(attribute);
        attribute = new Element();
        attribute.setName("attr_2");
        attribute.setValue("This is another Attribute.");
        card.addElement(attribute);
        selectedItem.addCard(card);
    }



    private Callback<ListView<Workspace>, ListCell<Workspace>> workspaceCellFactory = (z) -> {
        ListCell<Workspace> cell = new ListCell<>() {
            @Override
            protected void updateItem(Workspace item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        };
        //When double-clicked, open that workspace.
        cell.setOnMouseClicked(e -> {
            if (!cell.isEmpty() && cell.getItem() != null && e.getClickCount() == 2) {
                openCollection(cell.getItem());
            }
        });
        ContextMenu contextMenu = new ContextMenu();
        MenuItem save = new MenuItem("Save");
        save.setOnAction(a -> workspaceRepository.save(cell.getItem()));
        contextMenu.getItems().add(save);

        cell.setContextMenu(contextMenu);
        return cell;
    };

    private void openCollection(Workspace workspace) {
        tabPane.getTabs().stream()
                .filter(tab -> tab.getText().equals(workspace.getName()))
                .findFirst()
                .ifPresentOrElse((tab) -> {
                    tabPane.getSelectionModel().select(tab);
                }, () -> {
                    tabPane.getSelectionModel().select(addCollectionTab(workspace));
                });
    }
}