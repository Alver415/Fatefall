package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ToolBar;

@FXMLPrototype
public class CardEditorView {

	@FXML
	private ToolBar toolBar;
	@FXML
	private Accordion leftMenu;
	@FXML
	private CardView cardView;
	@FXML
	private Accordion rightMenu;

	public void setCard(CardFX card){
		cardView.setCard(card);
	}

}
