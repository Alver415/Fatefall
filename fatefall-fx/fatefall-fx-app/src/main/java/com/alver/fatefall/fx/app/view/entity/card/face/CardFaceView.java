package com.alver.fatefall.fx.app.view.entity.card.face;

import com.alver.fatefall.fx.app.view.entity.card.template.ImageTemplate;
import com.alver.fatefall.fx.app.view.entity.card.template.PlaceholderTemplate;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.model.Source;
import com.alver.fatefall.fx.core.model.TemplateFX;
import com.alver.fatefall.fx.core.utils.FXUtils;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.alver.fatefall.fx.core.utils.TreePropertyBuilder;
import com.alver.springfx.SpringFXLoader;
import com.alver.springfx.annotations.Prototype;
import com.alver.springfx.model.FXMLControllerAndView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Map;

@Prototype
public class CardFaceView extends Control {
	private static final Logger log = LoggerFactory.getLogger(CardFaceView.class);

	protected final FXMLControllerAndView<PlaceholderTemplate, Node> placeholderTemplate;
	protected final FXMLControllerAndView<ImageTemplate, Node> imageTemplate;
	private final BeanFactory beanFactory;

	@Autowired
	public CardFaceView(
			FXMLControllerAndView<PlaceholderTemplate, Node> placeholderTemplate,
			FXMLControllerAndView<ImageTemplate, Node> imageTemplate, BeanFactory beanFactory) {
		this.placeholderTemplate = placeholderTemplate;
		this.imageTemplate = imageTemplate;
		this.beanFactory = beanFactory;

		initializeContextMenu();

		cardFaceProperty().flatMap(CardFaceFX::templateProperty).addListener((observable, oldValue, newValue) -> {
			FXUtils.runFx(() -> {
				content.set(placeholderTemplate.view());
				if (newValue == null) return;
				try {
					String imageUrl = newValue.getImageUrl();
					String fxmlUrl = newValue.getFxmlUrl();

					if (fxmlUrl != null) {
						SpringFXLoader loader = this.beanFactory.getBean(SpringFXLoader.class);
						TreeProperty<Object> data = TreePropertyBuilder.buildAndBind(Map.of(
								Source.CARD, cardFace.map(CardFaceFX::getCard).orElse(new CardFX()).getValue().dataProperty(),
								Source.CARD_FACE, cardFace.get().dataProperty(),
								Source.TEMPLATE, cardFace.get().getTemplate().dataProperty()));

						loader.setLocation(new URL(fxmlUrl));
						loader.getNamespace().put("data", data);
						Node root = loader.load();
						content.set(root);
					} else if (imageUrl != null) {
						imageTemplate.controller().setImage(new Image(imageUrl));
						content.set(imageTemplate.view());
					} else {
						content.set(placeholderTemplate.view());
					}
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			});
		});
	}
	private void initializeContextMenu() {
		ContextMenu contextMenu = new ContextMenu();
		Menu templateSelection = new Menu("Template");
		TemplateFX placeholderTemplate = new TemplateFX(1L);
		placeholderTemplate.setName("Placeholder Template");
		MenuItem item1 = new MenuItem();
		item1.textProperty().bind(placeholderTemplate.nameProperty());
		item1.setOnAction(action -> getCardFace().setTemplate(placeholderTemplate));
		templateSelection.getItems().add(item1);

		TemplateFX imageTemplate = new TemplateFX(2L);
		imageTemplate.setImageUrl("https://cards.scryfall.io/large/front/7/b/7b215968-93a6-4278-ac61-4e3e8c3c3943.jpg?1566971561");
		imageTemplate.setName("Image Template");
		MenuItem item2 = new MenuItem();
		item2.textProperty().bind(imageTemplate.nameProperty());
		item2.setOnAction(action -> getCardFace().setTemplate(imageTemplate));

		TemplateFX fxmlTemplate = new TemplateFX(2L);
		fxmlTemplate.setFxmlUrl("file:C:\\projects\\Fatefall\\fatefall-fx\\fatefall-fx-app\\src\\main\\resources\\com\\alver\\fatefall\\template\\mtg\\MTGCardTemplate.fxml");
		fxmlTemplate.setName("FXML Template");
		MenuItem item3 = new MenuItem();
		item3.textProperty().bind(fxmlTemplate.nameProperty());
		item3.setOnAction(action -> getCardFace().setTemplate(fxmlTemplate));
		templateSelection.getItems().setAll(item1, item2, item3);

		contextMenu.getItems().setAll(templateSelection);
		setContextMenu(contextMenu);
	}

	protected final ObjectProperty<CardFaceFX> cardFace = new SimpleObjectProperty<>(this, "cardFace");
	public ObjectProperty<CardFaceFX> cardFaceProperty() {
		return cardFace;
	}
	public CardFaceFX getCardFace() {
		return cardFace.get();
	}
	public void setCardFace(CardFaceFX cardFace) {
		this.cardFace.set(cardFace);
	}

	protected final ObjectProperty<Node> content = new SimpleObjectProperty<>(this, "content");
	public Node getContent() {
		return content.get();
	}
	public void loadFxml(String fxml) {
		try {
			SpringFXLoader loader = beanFactory.getBean(SpringFXLoader.class);
			TreeProperty<Object> data = TreePropertyBuilder.buildAndBind(Map.of(
					Source.CARD, cardFace.get().getCard().dataProperty(),
					Source.CARD_FACE, cardFace.get().dataProperty(),
					Source.TEMPLATE, cardFace.get().getTemplate().dataProperty()));
			loader.getNamespace().put("data", data);
			content.set(loader.load(new ByteArrayInputStream(fxml.getBytes())));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new CardFaceSkin(this);
	}

	public static class CardFaceSkin extends SkinBase<CardFaceView> {

		private static final LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
				new Stop(0, Color.WHITE),
				new Stop(0, Color.BLACK));
		private static final CornerRadii radii = new CornerRadii(0);
		private static final BackgroundFill fill = new BackgroundFill(gradient, radii, Insets.EMPTY);
		private static final Background background = new Background(fill);

		protected CardFaceSkin(CardFaceView control) {
			super(control);

			StackPane wrapper = new StackPane(control.content.get());
			control.content.addListener((obs, old, curr) -> wrapper.getChildren().setAll(curr));

			wrapper.setBackground(background);
			getChildren().setAll(wrapper);
		}
	}

}
