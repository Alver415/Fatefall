package com.alver.fatefall.app.fx.component.mainstage;

import com.alver.fatefall.app.services.ActionEventHandler;
import com.alver.fatefall.app.services.DialogManager;
import com.alver.fatefall.data.entity.Workspace;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Extension
public class WorkspaceCreateAction implements ActionEventHandler {

	@Autowired
	public DialogManager dialogManager;

	@Autowired
	protected ObservableList<Workspace> workspaces;

	@Override
	public String getName() {
		return "Create Workspace";
	}
	@Override
	public void handle(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		Optional<String> name = dialog.showAndWait();
		if (name.isEmpty()) {
			return;
		}
		Workspace workspace = new Workspace();
		workspace.setName(name.get());
		workspaces.add(workspace);
	}
}
