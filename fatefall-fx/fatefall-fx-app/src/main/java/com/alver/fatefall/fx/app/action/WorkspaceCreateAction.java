package com.alver.fatefall.fx.app.action;

import com.alver.fatefall.fx.core.interfaces.AppEvent;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import org.pf4j.Extension;
import org.pf4j.ExtensionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Extension
public class WorkspaceCreateAction implements AppEvent, ExtensionPoint {

	@Autowired
	protected ObservableList<WorkspaceFX> workspaces;

	@Override
	public void handle(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		Optional<String> name = dialog.showAndWait();
		if (name.isEmpty()) {
			return;
		}
		WorkspaceFX workspace = new WorkspaceFX();
		workspace.setName(name.get());
		workspaces.add(workspace);
	}
	@Override
	public String getTitle() {
		return "Create Workspace";
	}
}
