package com.alver.fatefall.poker.plugin;

import com.alver.fatefall.fx.app.component.mainstage.ApplicationController;
import com.alver.fatefall.fx.core.interfaces.AppEvent;
import com.alver.fatefall.fx.core.model.TemplateFX;
import com.alver.fatefall.poker.plugin.template.PokerCardBackController;
import com.alver.fatefall.poker.plugin.template.PokerCardFrontController;
import com.alver.fatefall.poker.plugin.template.Rank;
import com.alver.fatefall.poker.plugin.template.Suit;
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

	public static final String FRONT_FXML = Objects.requireNonNull(
			PokerCardFrontController.class.getResource("front.fxml")).toExternalForm();

	public static final String BACK_FXML = Objects.requireNonNull(
			PokerCardBackController.class.getResource("back.fxml")).toExternalForm();

	public static final Image BACK_IMAGE = new Image(Objects.requireNonNull(
			PokerCardBackController.class.getResource("back.png")).toExternalForm());


	@Autowired
	@Lazy
	protected ApplicationController applicationController;

	@Override
	public void handle(ActionEvent event) {
		PokerCard card = new PokerCard();

		card.getFront().setRank(Rank.ACE);
		card.getFront().setSuit(Suit.SPADE);

		card.getBack().setImage(BACK_IMAGE);

		TemplateFX frontTemplate = new TemplateFX();
		frontTemplate.setFxmlUrl(FRONT_FXML);
		card.getFront().setTemplate(frontTemplate);

		TemplateFX backTemplate = new TemplateFX();
		backTemplate.setFxmlUrl(BACK_FXML);
		card.getBack().setTemplate(backTemplate);

		applicationController.createCard(card);
	}
	@Override
	public String getTitle() {
		return "Create Poker Card";
	}
}
