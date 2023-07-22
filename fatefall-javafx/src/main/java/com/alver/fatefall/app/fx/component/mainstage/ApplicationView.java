package com.alver.fatefall.app.fx.component.mainstage;

import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.app.fx.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.app.fx.view.FXMLAutoLoad;
import com.alver.fatefall.app.fx.component.settings.FatefallPreferences;
import com.alver.fatefall.app.services.ActionEventHandler;
import com.alver.fatefall.app.services.ComponentFactory;
import com.alver.fatefall.app.services.DialogManager;
import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.Field;
import com.alver.fatefall.data.entity.Workspace;
import com.alver.fxmlsaver.FXMLSaver;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import jdk.jfr.Label;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@FXMLAutoLoad
@Component
public class ApplicationView extends BorderPane {

	@Autowired
	protected ObservableList<Workspace> workspaces;
	@Autowired
	protected EntityApi<Workspace> workspaceApi;
	@Autowired
	protected WorkspaceCreateAction workspaceCreateAction;
	@Autowired
	protected PluginManager pluginManager;
	@Autowired
	protected ComponentFactory componentFactory;
	@Autowired
	protected FatefallPreferences preferences;

	/**
	 * FXML Injection
	 */
	@FXML
	protected ListView<Workspace> listView;
	@FXML
	protected TabPane tabPane;
	@FXML
	protected Menu pluginMenu;

	@FXML
	private void initialize() {
		listView.setCellFactory(workspaceCellFactory);
		listView.setItems(workspaces);
		List<Menu> menuList = pluginManager.getPlugins().stream().map(this::buildMenu).toList();
		pluginMenu.getItems().setAll(menuList);
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
			boolean nameAlreadyExists = workspaces.stream()
					.map(Workspace::getName)
					.anyMatch(n -> Objects.equals(n, name));
			if (nameAlreadyExists) {
				throw new RuntimeException("A collection with that name already exists.");
			}
			Workspace workspace = new Workspace();
			workspace.setName(name);
			workspaces.add(workspace);
		});
	}

	@FXML
	private void createCard() {
		Workspace selectedItem = listView.getSelectionModel().getSelectedItem();
		Card card = new Card();
		Field field = new Field();
		field.setName("attr_1");
		field.setValue("This is an Attribute.");
		card.addField(field);
		field = new Field();
		field.setName("attr_2");
		field.setValue("This is another Attribute.");
		card.addField(field);
		selectedItem.addCards(card);
	}

	protected void openCollection(Workspace workspace) {
		tabPane.getTabs().stream()
				.filter(tab -> tab.getText().equals(workspace.getName()))
				.findFirst()
				.ifPresentOrElse((tab) -> {
					tabPane.getSelectionModel().select(tab);
				}, () -> {
					tabPane.getSelectionModel().select(addCollectionTab(workspace));
				});
	}

	@FXML
	protected void create(ActionEvent event) {
		workspaceCreateAction.handle(event);
	}

	@FXML
	protected void save() {
		Workspace selectedWorkspace = listView.getSelectionModel().getSelectedItem();
		if (selectedWorkspace.getId() == null) {
			workspaceApi.create(selectedWorkspace);
		} else {
			workspaceApi.update(selectedWorkspace.getId(), selectedWorkspace);
		}
	}

	@FXML
	protected void delete() {
		Workspace selectedWorkspace = listView.getSelectionModel().getSelectedItem();
		if (selectedWorkspace.getId() != null) {
			workspaces.remove(selectedWorkspace);
			workspaceApi.delete(selectedWorkspace.getId());
		}
	}

	private final Callback<ListView<Workspace>, ListCell<Workspace>> workspaceCellFactory = (z) -> {
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
		save.setOnAction(a -> {
			Workspace workspace = cell.getItem();
			Workspace saved = workspace.getId() == null ?
					workspaceApi.create(workspace) :
					workspaceApi.update(workspace.getId(), workspace);
		});
		contextMenu.getItems().add(save);

		cell.setContextMenu(contextMenu);
		return cell;
	};
}