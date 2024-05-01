package com.alver.fatefall.fx.app.component.mainstage;

import com.alver.fatefall.core.api.WorkspacesApi;
import com.alver.fatefall.core.entity.Workspace;
import com.alver.fatefall.fx.app.action.WorkspaceCreateAction;
import com.alver.fatefall.fx.app.component.settings.PreferencesController;
import com.alver.fatefall.fx.app.view.console.ConsoleController;
import com.alver.fatefall.fx.app.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.fx.core.interfaces.AppController;
import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.EntityFX;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import com.alver.fatefall.fx.core.utils.StageManager;
import com.alver.jfxtra.lib.component.console.ConsoleView;
import com.alver.jfxtra.lib.io.SystemIO;
import com.alver.springfx.SpringFX;
import com.alver.springfx.annotations.FXMLComponent;
import com.panemu.tiwulfx.control.dock.DetachableTab;
import com.panemu.tiwulfx.control.dock.DetachableTabPane;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Objects;

@FXMLComponent
public class ApplicationController implements AppController {

	protected final ObservableList<WorkspaceFX> workspaces;
	protected final WorkspacesApi<WorkspaceFX> workspaceApi;
	protected final WorkspaceCreateAction workspaceCreateAction;
	protected final PreferencesController preferences;
	protected final PluginMenu pluginMenu;
	protected final SpringFX springFX;
	protected final StageManager stageManager;
	protected final BeanFactory beanFactory;

	protected Stage consoleStage;
	protected Stage logsStage;

	/**
	 * FXML Injection
	 */
	@FXML
	protected EntityTreeView treeView;
	@FXML
	protected DetachableTabPane tabPane;
	@FXML
	protected MenuBar menuBar;

	@Autowired
	public ApplicationController(
			ObservableList<WorkspaceFX> workspaces,
			WorkspacesApi<WorkspaceFX> workspaceApi,
			WorkspaceCreateAction workspaceCreateAction,
			PreferencesController preferences,
			PluginMenu pluginMenu,
			SpringFX springFX,
			StageManager stageManager, BeanFactory beanFactory) {
		this.workspaces = workspaces;
		this.workspaceApi = workspaceApi;
		this.workspaceCreateAction = workspaceCreateAction;
		this.preferences = preferences;
		this.pluginMenu = pluginMenu;
		this.springFX = springFX;
		this.stageManager = stageManager;
		this.beanFactory = beanFactory;
	}

	@FXML
	private void initialize() {
		menuBar.getMenus().add(pluginMenu);

		tabPane.setOnClosedPassSibling((sibling) -> tabPane = sibling);

		consoleStage = stageManager.create("Console", new ConsoleView(SystemIO.console));
		logsStage = stageManager.create("Logs", (Node) springFX.load(ConsoleController.class).view());

	}

	@EventListener
	public void onReadyEventListener(ApplicationReadyEvent event) {
		refresh();
	}

	@FXML
	private void refresh() {
		workspaces.setAll(workspaceApi.getAll());
	}

	public void registerView(AppView appView) {
		selectTab(appView.title(), appView.node());
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	private void selectTab(ObservableValue<String> title, Node node) {
		tabPane.getTabs().stream().filter(
				tab -> tab.getText().equals(title.getValue())).findAny().ifPresentOrElse(
				tab -> {
					tabPane.getSelectionModel().select(tab);
				},
				() -> {
					Tab tab = new DetachableTab();
					tab.textProperty().bind(title);
					tab.setContent(node);
					tabPane.getTabs().add(tab);
					tabPane.getSelectionModel().select(tab);
				}
		);
	}

	@FXML
	private void openPreferences() {
		preferences.show();
	}

	@FXML
	private void openLogs() {
		logsStage.show();
	}

	@FXML
	private void openConsole() {
		consoleStage.show();
	}

	private Tab addCollectionTab(WorkspaceFX workspace) {

		WorkspaceView workspaceView = beanFactory.getBean(WorkspaceView.class);
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
		TreeItem<EntityFX> selectedItem = treeView.getSelectionModel().getSelectedItem();
		if (selectedItem.getValue() instanceof WorkspaceFX workspaceFX) {
			CardFX card = new CardFX();
			card.setName("New Card Name");
			card.setJson("New Card Data");
			workspaceFX.addCards(card);
		} else {
			throw new RuntimeException("Please select a workspace");
		}
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
		TreeItem<EntityFX> selected = treeView.getSelectionModel().getSelectedItem();
		if (selected.getValue() instanceof WorkspaceFX workspaceFX) {
			if (workspaceFX.getId() == null) {
				workspaceApi.create(workspaceFX);
			} else {
				workspaceApi.update(workspaceFX.getId(), workspaceFX);
			}
			refresh();
		}
	}

	@FXML
	protected void delete() {
		TreeItem<EntityFX> selected = treeView.getSelectionModel().getSelectedItem();
		if (selected.getValue() instanceof WorkspaceFX workspaceFX) {
			if (workspaceFX.getId() != null) {
				workspaceApi.delete(workspaceFX.getId());
				refresh();
			}
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