package com.alver.fatefall.app.fx.component.mainstage;

import com.alver.fatefall.StageManager;
import com.alver.fatefall.action.WorkspaceCreateAction;
import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.app.fx.component.settings.PreferencesController;
import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.alver.fatefall.app.fx.view.console.ConsoleController;
import com.alver.fatefall.app.fx.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.data.entity.Workspace;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.annotations.FXMLComponent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Objects;

@FXMLComponent
public class ApplicationController {


    protected final ObservableList<WorkspaceFX> workspaces;
    protected final EntityApi<WorkspaceFX> workspaceApi;
    protected final WorkspaceCreateAction workspaceCreateAction;
    protected final PreferencesController preferences;
    protected final PluginMenu pluginMenu;
    protected final SpringFXLoader springFXLoader;
    protected final StageManager stageManager;

    /**
     * FXML Injection
     */
    @FXML
    protected ListView<WorkspaceFX> listView;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected MenuBar menuBar;

    @Autowired
    public ApplicationController(
            ObservableList<WorkspaceFX> workspaces,
            EntityApi<WorkspaceFX> workspaceApi,
            WorkspaceCreateAction workspaceCreateAction,
            PreferencesController preferences,
            PluginMenu pluginMenu,
            SpringFXLoader springFXLoader,
            StageManager stageManager) {
        this.workspaces = workspaces;
        this.workspaceApi = workspaceApi;
        this.workspaceCreateAction = workspaceCreateAction;
        this.preferences = preferences;
        this.pluginMenu = pluginMenu;
        this.springFXLoader = springFXLoader;
        this.stageManager = stageManager;
    }

    @FXML
    private void initialize() {
        listView.setCellFactory(workspaceCellFactory);
        listView.setItems(workspaces);
        menuBar.getMenus().add(pluginMenu);
    }

    @EventListener
    public void onReadyEventListener(ApplicationReadyEvent event) {
        refresh();
    }

    @FXML
    private void refresh() {
        workspaces.setAll(workspaceApi.getAll());
    }

    public TabPane getTabPane() {
        return tabPane;
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

    @FXML
    private void openConsole() {
        Object view = springFXLoader.load(ConsoleController.class).view();
        stageManager.create("Console", (Node) view).show();
    }

    private Tab addCollectionTab(WorkspaceFX workspace) {

        WorkspaceView workspaceView = new WorkspaceView();
        workspaceView.setWorkspace(workspace);

        Tab tab = new Tab(workspace.getName());
        tab.setContent(workspaceView);

        tabPane.getTabs().add(tab);
        return tab;
    }

    @FXML
    private void openCreateCollectionDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Create a Collection");
        dialog.setContentText("Enter a name for the new collection.");
        dialog.showAndWait().ifPresent(name -> {
            boolean nameAlreadyExists = workspaces.stream()
                    .map(Workspace::getName)
                    .anyMatch(n -> Objects.equals(n, name));
            if (nameAlreadyExists) {
                throw new RuntimeException("A collection with that name already exists.");
            }
            WorkspaceFX workspace = new WorkspaceFX();
            workspace.setName(name);
            workspaces.add(workspace);
        });
    }

    @FXML
    private void createCard() {
        WorkspaceFX selectedItem = listView.getSelectionModel().getSelectedItem();
        CardFX card = new CardFX();
        card.setName("New Card Name");
        card.setData("New Card Data");
        selectedItem.addCards(card);
    }

    protected void openCollection(WorkspaceFX workspace) {
        tabPane.getTabs().stream()
                .filter(tab -> tab.getText().equals(workspace.getName()))
                .findFirst()
                .ifPresentOrElse(
                        (tab) -> tabPane.getSelectionModel().select(tab),
                        () -> tabPane.getSelectionModel().select(addCollectionTab(workspace)));
    }

    @FXML
    protected void create(ActionEvent event) {
        workspaceCreateAction.handle(event);
    }

    @FXML
    protected void save() {
        WorkspaceFX selectedWorkspace = listView.getSelectionModel().getSelectedItem();
        if (selectedWorkspace.getId() == null) {
            WorkspaceFX newWorkspace = workspaceApi.create(selectedWorkspace);
            listView.getItems().remove(selectedWorkspace);
            listView.getItems().add(newWorkspace);
        } else {
            workspaceApi.update(selectedWorkspace.getId(), selectedWorkspace);
        }
    }

    @FXML
    protected void delete() {
        WorkspaceFX selectedWorkspace = listView.getSelectionModel().getSelectedItem();
        if (selectedWorkspace.getId() != null) {
            workspaces.remove(selectedWorkspace);
            workspaceApi.delete(selectedWorkspace.getId());
        }
    }

    private final Callback<ListView<WorkspaceFX>, ListCell<WorkspaceFX>> workspaceCellFactory = (z) -> {
        ListCell<WorkspaceFX> cell = new ListCell<>() {
            @Override
            protected void updateItem(WorkspaceFX item, boolean empty) {
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
        save.setOnAction(a -> saveAction(cell));
        contextMenu.getItems().add(save);

        cell.setContextMenu(contextMenu);
        return cell;
    };

    private void saveAction(ListCell<WorkspaceFX> cell) {
        WorkspaceFX workspace = cell.getItem();
        WorkspaceFX saved = workspace.getId() == null ?
                workspaceApi.create(workspace) :
                workspaceApi.update(workspace.getId(), workspace);
        workspaces.remove(workspace);
        workspaces.add(saved);
    }
}