package com.alver.fatefall.mtg.plugin;

import com.alver.fatefall.fx.app.component.mainstage.ApplicationController;
import com.alver.fatefall.fx.core.interfaces.AppEvent;
import javafx.event.ActionEvent;
import org.pf4j.Extension;
import org.pf4j.ExtensionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Extension
public class NewMagicCardAction implements AppEvent, ExtensionPoint {

	@Autowired
	@Lazy
	protected ApplicationController applicationController;

	@Override
	public void handle(ActionEvent event) {
		applicationController.createCard(new MagicCard());
	}
	@Override
	public String getTitle() {
		return "Create Magic Card";
	}
}
