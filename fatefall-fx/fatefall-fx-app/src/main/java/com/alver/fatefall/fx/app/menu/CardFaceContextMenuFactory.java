package com.alver.fatefall.fx.app.menu;

import com.alver.fatefall.core.api.EntityApi;
import com.alver.fatefall.fx.core.interfaces.AppController;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.model.TemplateFX;
import com.alver.springfx.SpringFX;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardFaceContextMenuFactory extends EntityContextMenuFactory<CardFaceFX> {

	private final SpringFX springFX;
	private final AppController appController;
	private final EntityApi<TemplateFX> templateApi;

	@Autowired
	public CardFaceContextMenuFactory(
			EntityApi<CardFaceFX> cardFaceApi,
			EntityApi<TemplateFX> templateApi,
			SpringFX springFX, AppController appController) {
		super(cardFaceApi);
		this.springFX = springFX;
		this.appController = appController;
		this.templateApi = templateApi;
	}

	public ContextMenu buildContextMenu(CardFaceFX cardFace) {
		ContextMenu contextMenu = super.buildContextMenu(cardFace);
		Menu setTemplate = new Menu("Set Template");
		for (TemplateFX template : templateApi.getAll()) {
			MenuItem item = new MenuItem();
			item.textProperty().bind(template.nameProperty());
			item.setOnAction(a -> cardFace.setTemplate(template));
		}
		contextMenu.getItems().set(0, setTemplate);
		return contextMenu;
	}
}
