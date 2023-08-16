package com.alver.fatefall.app.fx.component.mainstage;

import com.alver.fatefall.action.ActionEventHandler;
import com.alver.fatefall.action.WorkspaceCreateAction;
import com.alver.fatefall.api.entity.EntityApi;
import com.alver.fatefall.app.fx.component.settings.FatefallPreferences;
import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.alver.fatefall.app.fx.view.FXMLAutoLoad;
import com.alver.fatefall.app.fx.view.console.ConsoleView;
import com.alver.fatefall.app.fx.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.app.ComponentFactory;
import com.alver.fatefall.data.entity.Workspace;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@FXMLAutoLoad
@Component
public class ApplicationView extends BorderPane {

	@Autowired
	protected ObservableList<WorkspaceFX> workspaces;
	@Autowired
	protected EntityApi<WorkspaceFX> workspaceApi;
	@Autowired
	protected WorkspaceCreateAction workspaceCreateAction;
	@Autowired
	protected ComponentFactory componentFactory;
	@Autowired
	protected FatefallPreferences preferences;
	@Autowired
	protected ConsoleView consoleView;

	/**
	 * FXML Injection
	 */
	@FXML
	protected ListView<WorkspaceFX> listView;
	@FXML
	protected TabPane tabPane;
	@FXML
	protected Menu pluginMenu;

	@FXML
	private void initialize() {
		listView.setCellFactory(workspaceCellFactory);
		listView.setItems(workspaces);
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
		if (consoleView.getScene() == null) {
			Stage stage = new Stage();
			Scene scene = new Scene(consoleView);
			stage.setScene(scene);
		}
		((Stage)consoleView.getScene().getWindow()).show();

	}

	private Tab addCollectionTab(WorkspaceFX workspace) {
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
		save.setOnAction(a -> {
			WorkspaceFX workspace = cell.getItem();
			WorkspaceFX saved = workspace.getId() == null ?
					workspaceApi.create(workspace) :
					workspaceApi.update(workspace.getId(), workspace);
			workspaces.remove(workspace);
			workspaces.add(saved);
		});
		contextMenu.getItems().add(save);

		cell.setContextMenu(contextMenu);
		return cell;
	};

	public void buildPluginMenu(PluginManager pluginManager) {
		List<Menu> menuList = pluginManager.getPlugins().stream()
				.map(plugin -> {
					Menu menu = new Menu(plugin.getPluginId());
					List<MenuItem> menuItems = pluginManager
							.getExtensions(ActionEventHandler.class, plugin.getPluginId())
							.stream().map(action -> {
								MenuItem menuItem = new MenuItem(action.getTitle());
								menuItem.setOnAction(action);
								return menuItem;
							}).toList();
					menu.getItems().setAll(menuItems);
					return menu;
				}).toList();
		pluginMenu.getItems().setAll(menuList);
	}
}