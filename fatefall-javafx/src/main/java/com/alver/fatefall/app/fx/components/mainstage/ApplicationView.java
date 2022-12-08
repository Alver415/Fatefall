package com.alver.fatefall.app.fx.components.mainstage;

import com.alver.fatefall.api.interfaces.CardCollectionView;
import com.alver.fatefall.api.interfaces.ComponentFactory;
import com.alver.fatefall.api.models.CardCollection;
import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import com.alver.fatefall.app.fx.components.about.AboutView;
import com.alver.fatefall.app.fx.components.plugins.PluginManagerView;
import com.alver.fatefall.app.fx.components.settings.SettingsView;
import com.alver.fatefall.app.services.Repository;
import com.alver.fatefall.plugin.PluginManager;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@FXMLAutoLoad
@Component
public class ApplicationView extends BorderPane {

    @Autowired
    protected SettingsView settingsView;
    @Autowired
    protected PluginManagerView pluginManagerView;
    @Autowired
    protected PluginManager pluginManager;
    @Autowired
    protected AboutView aboutView;
    @Autowired
    protected ComponentFactory componentFactory;

    /**
     * FXML Injection
     */
    @FXML
    protected ListView<CardCollection> collectionsList;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected Menu pluginMenu;
    @FXML
    protected MenuItem managePlugins;

    protected Repository repository = new Repository();

    @FXML
    public void initialize() {
        collectionsList.setCellFactory(cardCollectionCellFactory);
        collectionsList.setItems(FXCollections.observableList(repository.getCardCollections()));

        pluginManager.getPlugins().addListener((InvalidationListener) invalidation -> {
            List<MenuItem> menuItemList = new ArrayList<>();
            menuItemList.add(managePlugins);
            menuItemList.addAll(pluginManager.getPlugins().stream()
                    .map(plugin -> {
                        Menu menu = new Menu(plugin.getName());
                        List<MenuItem> menuItems = plugin.getActions().stream()
                                .map(action -> {
                                    MenuItem menuItem = new MenuItem(action.getName());
                                    menuItem.setOnAction(event -> action.execute());
                                    return menuItem;
                                }).toList();
                        menu.getItems().setAll(menuItems);
                        return menu;
                    }).toList());
            pluginMenu.getItems().setAll(menuItemList);
        });
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
    private void openAboutView() {
        createTab("About", aboutView);
    }

    @FXML
    private void openSettingsView() {
        createTab("Settings", settingsView);
    }

    @FXML
    private void openPluginManagerView() {
        createTab("Plugin Manager", pluginManagerView);
    }

    private Tab addCollectionTab(CardCollection cardCollection) {
        CardCollectionView cardCollectionView = componentFactory.buildCardCollectionView();
        cardCollectionView.setCardCollection(cardCollection);

        Tab tab = new Tab(cardCollection.getName());
        tab.setContent(cardCollectionView.getFxViewNode());

        tabPane.getTabs().add(tab);
        return tab;
    }

    public void newCollection() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Create a Collection");
        dialog.setContentText("Enter a name for the new collection.");
        dialog.showAndWait().ifPresent(this::createCollection);
    }

    private CardCollection createCollection(String name) {
        boolean nameAlreadyExists = collectionsList.getItems().stream()
                .map(CardCollection::getName)
                .anyMatch(n -> Objects.equals(n, name));
        if (nameAlreadyExists) {
            throw new RuntimeException("A collection with that name already exists.");
        }
        CardCollection cardCollection = new CardCollection();
        cardCollection.setName(name);
        collectionsList.getItems().add(cardCollection);
        return cardCollection;
    }

    public void saveCollection() {
        CardCollection selectedItem = collectionsList.getSelectionModel().getSelectedItem();
        repository.save(selectedItem);
    }

    private Callback<ListView<CardCollection>, ListCell<CardCollection>> cardCollectionCellFactory = (z) -> {
        ListCell<CardCollection> cell = new ListCell<>() {
            @Override
            protected void updateItem(CardCollection item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        };
        //When double-clicked, open that cardCollection.
        cell.setOnMouseClicked(e -> {
            if (!cell.isEmpty() && cell.getItem() != null && e.getClickCount() == 2) {
                openCollection(cell.getItem());
            }
        });
        ContextMenu contextMenu = new ContextMenu();
        MenuItem save = new MenuItem("Save");
        save.setOnAction(a -> repository.save(cell.getItem()));
        contextMenu.getItems().add(save);

        cell.setContextMenu(contextMenu);
        return cell;
    };

    private void openCollection(CardCollection cardCollection) {
        tabPane.getTabs().stream()
                .filter(tab -> tab.getText().equals(cardCollection.getName()))
                .findFirst()
                .ifPresentOrElse((tab) -> {
                    tabPane.getSelectionModel().select(tab);
                }, () -> {
                    tabPane.getSelectionModel().select(addCollectionTab(cardCollection));
                });
    }
}