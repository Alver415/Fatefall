package com.alver.fatefall.poker.plugin.action;

import com.alver.fatefall.fx.app.component.mainstage.ApplicationController;
import com.alver.fatefall.fx.core.interfaces.AppEvent;
import com.alver.fatefall.fx.core.model.TemplateFX;
import com.alver.fatefall.poker.plugin.model.PokerCard;
import com.alver.fatefall.poker.plugin.model.Rank;
import com.alver.fatefall.poker.plugin.model.Suit;
import com.alver.fatefall.poker.plugin.template.PokerCardBackController;
import com.alver.fatefall.poker.plugin.template.PokerCardFrontController;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import org.pf4j.Extension;
import org.pf4j.ExtensionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Extension
public class NewPokerCardAction implements AppEvent, ExtensionPoint {

	@Autowired
	@Lazy
	protected ApplicationController applicationController;

	@Override
	public void handle(ActionEvent event) {
		PokerCard card = new PokerCard();
		applicationController.createCard(card);
	}
	@Override
	public String getTitle() {
		return "Create Poker Card";
	}
}
