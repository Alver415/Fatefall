package com.alver.fatefall.fx.app.menu;

import com.alver.fatefall.core.api.EntityApi;
import com.alver.fatefall.fx.app.view.entity.card.CardEditorView;
import com.alver.fatefall.fx.core.interfaces.AppController;
import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.springfx.SpringFX;
import com.alver.springfx.model.FXMLControllerAndView;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardContextMenuFactory extends EntityContextMenuFactory<CardFX>{

	private final SpringFX springFX;
	private final AppController appController;

	@Autowired
	public CardContextMenuFactory(EntityApi<CardFX> cardApi, SpringFX springFX, AppController appController) {
		super(cardApi);
		this.springFX = springFX;
		this.appController = appController;
	}

	public ContextMenu buildContextMenu(CardFX card) {
		ContextMenu contextMenu = super.buildContextMenu(card);
		MenuItem edit = new MenuItem("Edit");
		edit.setOnAction(a -> {
			FXMLControllerAndView<CardEditorView, BorderPane> cnv = springFX.load(CardEditorView.class);
			cnv.controller().setCard(card);
			AppView appView = AppView.of(card.nameProperty(), cnv.view());
			appController.addView(appView);
		});
		contextMenu.getItems().set(0, edit);
		return contextMenu;
	}
}
