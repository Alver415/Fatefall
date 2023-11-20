package com.alver.fatefall.fx.app.view.entity.card.face;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.app.view.entity.card.template.TemplateController;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.model.Source;
import com.alver.fatefall.fx.core.utils.FXAsyncUtils;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.alver.fatefall.fx.core.utils.TreePropertyBuilder;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.annotations.FXMLPrototype;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;

@FXMLPrototype
public class CardFaceController {
	private static final Logger log = LoggerFactory.getLogger(CardFaceController.class);

	@FXML
	private Pane root;

	protected final ObjectProperty<CardFX> card = new SimpleObjectProperty<>();
	protected final ObjectProperty<CardFaceFX> cardFace = new SimpleObjectProperty<>();
	protected final DoubleProperty width = new SimpleDoubleProperty(250);
	protected final DoubleProperty height = new SimpleDoubleProperty(350);
	protected final DoubleProperty arcWidth = new SimpleDoubleProperty(25);
	protected final DoubleProperty arcHeight = new SimpleDoubleProperty(25);

	protected final BeanFactory beanFactory;

	@Autowired
	public CardFaceController(BeanFactory beanFactory, FatefallProperties properties) {
		this.beanFactory = beanFactory;
		FXAsyncUtils.runFx(() -> {
			width.bind(properties.getCardBaseWidth());
			height.bind(properties.getCardBaseHeight());
			arcWidth.bind(properties.getCardBaseArcWidth());
			arcHeight.bind(properties.getCardBaseArcHeight());
		});
	}

	public void initialize() {
		cardFace.addListener((observable, oldValue, newValue) -> {
			FXAsyncUtils.runAsync(() -> {
				if (newValue == null){
					FXAsyncUtils.runFx(() -> root.getChildren().setAll(List.of()));
					return;
				}
				SpringFXLoader loader = beanFactory.getBean(SpringFXLoader.class);
				try {
					String imageUrl = newValue.getTemplate().getImageUrl();
					String fxmlUrl = newValue.getTemplate().getFxmlUrl();
					URL fxml = fxmlUrl != null ? new URL(fxmlUrl) :
							imageUrl != null ? TemplateController.class.getResource("ImageTemplate.fxml") :
									TemplateController.class.getResource("PlaceholderTemplate.fxml");
					loader.setLocation(fxml);

					TreeProperty<Object> data = TreePropertyBuilder.buildAndBind(List.of(
							new Pair<>(Source.CARD, card.get().getData()),
							new Pair<>(Source.CARD_FACE, cardFace.get().getData()),
							new Pair<>(Source.TEMPLATE, cardFace.get().getTemplate().getData())));

					loader.getNamespace().put("data", data);

					Node faceNode = loader.load();
					TemplateController controller = loader.getController();
					controller.imageProperty().set(imageUrl == null ? null : new Image(imageUrl));
					FXAsyncUtils.runFx(() -> root.getChildren().setAll(faceNode));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			});
		});
		cardFace.set(null);
	}

	public CardFX getCard() {
		return card.get();
	}

	public ObjectProperty<CardFX> cardProperty() {
		return card;
	}

	public void setCard(CardFX card) {
		this.card.set(card);
	}

	public ObjectProperty<CardFaceFX> cardFaceProperty() {
		return cardFace;
	}

	public CardFaceFX getCardFace() {
		return cardFace.get();
	}

	public void setCardFace(CardFaceFX cardFace) {
		this.cardFace.set(cardFace);
	}

	public double getWidth() {
		return width.get();
	}

	public DoubleProperty widthProperty() {
		return width;
	}

	public void setWidth(double widthProperty) {
		this.width.set(widthProperty);
	}

	public double getHeight() {
		return height.get();
	}

	public DoubleProperty heightProperty() {
		return height;
	}

	public void setHeight(double heightProperty) {
		this.height.set(heightProperty);
	}

	public double getArcWidth() {
		return arcWidth.get();
	}

	public DoubleProperty arcWidthProperty() {
		return arcWidth;
	}

	public void setArcWidth(double arcWidthProperty) {
		this.arcWidth.set(arcWidthProperty);
	}

	public double getArcHeight() {
		return arcHeight.get();
	}

	public DoubleProperty arcHeightProperty() {
		return arcHeight;
	}

	public void setArcHeight(double arcHeightProperty) {
		this.arcHeight.set(arcHeightProperty);
	}

}