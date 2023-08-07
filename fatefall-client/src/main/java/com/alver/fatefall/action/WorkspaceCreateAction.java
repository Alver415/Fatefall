package com.alver.fatefall.action;

import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Extension
public class WorkspaceCreateAction extends ActionEventHandlerImpl {

	@Autowired
	protected ObservableList<WorkspaceFX> workspaces;

	public WorkspaceCreateAction(){
		super("Create Workspace");
	}

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
}
