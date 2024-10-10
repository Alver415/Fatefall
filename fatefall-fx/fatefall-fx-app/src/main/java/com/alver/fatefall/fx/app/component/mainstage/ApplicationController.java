package com.alver.fatefall.fx.app.component.mainstage;

import com.alver.fatefall.core.api.WorkspacesApi;
import com.alver.fatefall.fx.app.action.WorkspaceCreateAction;
import com.alver.fatefall.fx.app.component.settings.PreferencesController;
import com.alver.fatefall.fx.app.view.console.ConsoleController;
import com.alver.fatefall.fx.core.interfaces.AppController;
import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import com.alver.fatefall.fx.core.utils.StageManager;
import com.alver.fsfx.FileSystemEntry;
import com.alver.fsfx.FileSystemFX;
import com.alver.fsfx.view.ModelCache;
import com.alver.fsfx.view.folder.FileSystemNodeTreeItem;
import com.alver.fsfx.view.folder.FileSystemTreeView;
import com.alver.fsfx.view.mvc.Controller;
import com.alver.fsfx.view.mvc.Model;
import com.alver.jfxtra.lib.component.console.ConsoleView;
import com.alver.jfxtra.lib.io.SystemIO;
import com.alver.springfx.SpringFX;
import com.alver.springfx.annotations.FXMLComponent;
import com.panemu.tiwulfx.control.dock.DetachableTab;
import com.panemu.tiwulfx.control.dock.DetachableTabPane;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@FXMLComponent
public class ApplicationController implements AppController {

	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
	protected final ObservableList<WorkspaceFX> workspaces;
	protected final WorkspacesApi<WorkspaceFX> workspaceApi;
	protected final WorkspaceCreateAction workspaceCreateAction;
	protected final PreferencesController preferences;
	protected final PluginMenu pluginMenu;
	protected final SpringFX springFX;
	protected final StageManager stageManager;
	protected final BeanFactory beanFactory;
	protected final FileSystemFX fileSystem;
	protected final ModelCache modelCache;


	protected Stage consoleStage;
	protected Stage logsStage;

	/**
	 * FXML Injection
	 */
	@FXML
	protected FileSystemTreeView treeView;
	@FXML
	protected DetachableTabPane tabPane;
	@FXML
	protected MenuBar menuBar;

	@Autowired
	public ApplicationController(
			FileSystemFX fileSystem,
			ModelCache modelCache,
			ObservableList<WorkspaceFX> workspaces,
			WorkspacesApi<WorkspaceFX> workspaceApi,
			WorkspaceCreateAction workspaceCreateAction,
			PreferencesController preferences,
			PluginMenu pluginMenu,
			SpringFX springFX,
			StageManager stageManager, BeanFactory beanFactory) {
		this.fileSystem = fileSystem;
		this.modelCache = modelCache;
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

		treeView.setRoot(new FileSystemNodeTreeItem(fileSystem.getRoot()));
		treeView.setOnSyncRequested(e -> e.getEntry().requestSync());
		treeView.setOnOpenRequested(e -> {
			tabPane.addTab("test", getView(e.getEntry()));
		});
	}

	private <M extends Model> Node getView(FileSystemEntry entry) {
		try {
			M model = modelCache.getModel(entry);
			URL fxml = modelCache.getFXML(model.getClass());
			FXMLLoader loader = new FXMLLoader(fxml);
			Node view = loader.load();
			Controller<M> controller = loader.getController();
			controller.setModel(model);
			return view;
		} catch (IOException e) {
			log.warn(e.getMessage(), e);
			return new Label("ERROR: " + e.getMessage());
		}
	}

	@EventListener
	public void onReadyEventListener(ApplicationReadyEvent event) {
		refresh();
	}

	@FXML
	private void refresh() {
		List<WorkspaceFX> fetched = workspaceApi.getAll();

		// TODO: Fix me
		// Hack: Need to manually assign parent relationship from CardFace to Card.
		// Json deserializer isn't currently configured correctly to handle it.
		for (WorkspaceFX workspaceFX : fetched) {
			workspaceFX.getCards().forEach(cardFX -> {
				cardFX.getFront().setCard(cardFX);
				cardFX.getBack().setCard(cardFX);
			});
		}
		workspaces.setAll(fetched);
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
					tab.setContent(node);
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

}