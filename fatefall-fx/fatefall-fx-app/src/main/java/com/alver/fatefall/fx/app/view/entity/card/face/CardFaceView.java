package com.alver.fatefall.fx.app.view.entity.card.face;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.utils.BackgroundUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardFaceView extends SubScene {
	private static final Logger log = LoggerFactory.getLogger(CardFaceView.class);

	public CardFaceView() {
		super(new Group(), 400, 400);

		cardFaceProperty().subscribe(cardFace -> {
			if (cardFace == null) {
				setRoot(buildPlaceholder());
			} else {
				setRoot(cardFace.getTemplate().build(cardFace));
			}
			getRoot().setId(isFrontFace() ? "front" : "back");
		});
	}

	private static StackPane buildPlaceholder() {
		StackPane placeholder = new StackPane(new Label("NULL"));
		placeholder.setBorder(Border.stroke(Color.GRAY));
		placeholder.setBackground(BackgroundUtil.checkeredBackground());
		return placeholder;
	}

	private boolean isFrontFace() {
		return cardFaceProperty()
				.flatMap(CardFaceFX::cardProperty)
				.flatMap(CardFX::frontProperty)
				.getValue() == getCardFace();
	}


	//region Properties

	protected final ObjectProperty<CardFaceFX<?>> cardFace = new SimpleObjectProperty<>(this, "cardFace");

	public ObjectProperty<CardFaceFX<?>> cardFaceProperty() {
		return cardFace;
	}

	public CardFaceFX<?> getCardFace() {
		return cardFaceProperty().get();
	}

	public void setCardFace(CardFaceFX<?> cardFace) {
		this.cardFaceProperty().set(cardFace);
	}

	//endregion Properties

}
