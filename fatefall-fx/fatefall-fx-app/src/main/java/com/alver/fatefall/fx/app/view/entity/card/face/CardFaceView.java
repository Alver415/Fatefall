package com.alver.fatefall.fx.app.view.entity.card.face;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.model.Source;
import com.alver.fatefall.fx.core.utils.FXUtils;
import com.alver.fatefall.fx.core.utils.SimpleClip;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.alver.fatefall.fx.core.utils.TreePropertyBuilder;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.annotations.Prototype;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.SubScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.Map;

@Prototype
public class CardFaceView extends SubScene {
	private static final Logger log = LoggerFactory.getLogger(CardFaceView.class);

	protected final CardFaceRenderer renderer;

	@Autowired
	public CardFaceView(CardFaceRenderer renderer, FatefallProperties properties) {
		super(new Group(), 400, 400);
		this.renderer = renderer;
		userAgentStylesheetProperty().bind(properties.getSubSceneStylesheetSelection()
				.map(s -> FatefallProperties.getStylesheetByNameMap().get(s)));

		//Replace binding with card.width and card.height properties instead of using properties for default.
		widthProperty().bind(properties.getCardScaledWidth());
		heightProperty().bind(properties.getCardScaledHeight());
		setClip(new SimpleClip(
				properties.getCardScaledWidth(),
				properties.getCardScaledHeight(),
				properties.getCardScaledArcWidth(),
				properties.getCardScaledArcHeight()));

		cardFace.subscribe(value -> {
			if (value == null) FXUtils.runFx(() -> setRoot(renderer.loadDefaultCardFace()));
			else FXUtils.runAsync(() -> setRoot(renderer.loadFXML(value)));
			getRoot().setId(isFrontFace() ? "front" : "back");
		});
	}

	private boolean isFrontFace() {
		return cardFaceProperty().flatMap(CardFaceFX::cardProperty)
				.flatMap(CardFX::frontProperty)
				.getValue() == getCardFace();
	}


	//region Properties

	protected final ObjectProperty<CardFaceFX> cardFace = new SimpleObjectProperty<>(this, "cardFace");

	public ObjectProperty<CardFaceFX> cardFaceProperty() {
		return cardFace;
	}

	public CardFaceFX getCardFace() {
		return cardFaceProperty().get();
	}

	public void setCardFace(CardFaceFX cardFace) {
		this.cardFaceProperty().set(cardFace);
	}

	public CardFaceRenderer getRenderer() {
		return renderer;
	}

	public void loadFxml(String fxml) {
		FXUtils.runFx(() -> setRoot(renderer.loadDefaultCardFace()));
		SpringFXLoader loader = renderer.loaderSupplier.get();
		try {
			CardFaceFX cardFace = getCardFace();
			TreeProperty<Object> data = TreePropertyBuilder.buildAndBind(Map.of(
					Source.CARD, cardFace.getCard().dataProperty(),
					Source.CARD_FACE, cardFace.dataProperty(),
					Source.TEMPLATE, cardFace.getTemplate().dataProperty()));

			loader.getNamespace().put("data", data);

			ByteArrayInputStream fxmlBytes = new ByteArrayInputStream(fxml.getBytes());
			setRoot(loader.load(fxmlBytes));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	//endregion Properties

}
