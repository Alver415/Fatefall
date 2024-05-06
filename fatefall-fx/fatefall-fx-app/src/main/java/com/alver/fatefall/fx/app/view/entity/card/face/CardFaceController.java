package com.alver.fatefall.fx.app.view.entity.card.face;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.app.view.entity.card.template.TemplateController;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.model.Source;
import com.alver.fatefall.fx.core.utils.FXUtils;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Map;

import static com.alver.jfxtra.util.JFXUtils.runFX;

@FXMLPrototype
public class CardFaceController {
	private static final Logger log = LoggerFactory.getLogger(CardFaceController.class);
	protected final BeanFactory beanFactory;

	@FXML
	private Pane root;

	//region Properties
	protected final ObjectProperty<Node> content = new SimpleObjectProperty<>(this, "content");

	public ObjectProperty<Node> contentProperty() {
		return content;
	}

	public Node getContent() {
		return contentProperty().get();
	}

	public void setContent(Node node) {
		contentProperty().set(node);
	}

	protected final ObjectProperty<CardFaceFX> cardFace = new SimpleObjectProperty<>();
	protected final DoubleProperty width = new SimpleDoubleProperty(250);
	protected final DoubleProperty height = new SimpleDoubleProperty(350);
	protected final DoubleProperty arcWidth = new SimpleDoubleProperty(25);
	protected final DoubleProperty arcHeight = new SimpleDoubleProperty(25);

	protected TreeProperty<Object> data;

	//endregion Properties


	@Autowired
	public CardFaceController(BeanFactory beanFactory, FatefallProperties properties) {
		this.beanFactory = beanFactory;
		FXUtils.runFx(() -> {
			width.bind(properties.getCardBaseWidth());
			height.bind(properties.getCardBaseHeight());
			arcWidth.bind(properties.getCardBaseArcWidth());
			arcHeight.bind(properties.getCardBaseArcHeight());
		});
	}

	@FXML
	public void initialize() {
		contentProperty().subscribe(newValue -> runFX(() -> {
			root.getChildren().clear();
			if (newValue != null) {
				root.getChildren().setAll(newValue);
			}
		}));
		cardFace.subscribe(value -> {
			FXUtils.runAsync(() -> {
				if (value == null) {
					FXUtils.runFx(() -> root.getChildren().clear());
					return;
				}
				SpringFXLoader loader = beanFactory.getBean(SpringFXLoader.class);
				try {
					String imageUrl = value.getTemplate().getImageUrl();
					String fxmlUrl = value.getTemplate().getFxmlUrl();
					URL fxml = fxmlUrl != null ? new URL(fxmlUrl) :
							imageUrl != null ? TemplateController.class.getResource("ImageTemplate.fxml") :
									TemplateController.class.getResource("PlaceholderTemplate.fxml");
					loader.setLocation(fxml);

					data = TreePropertyBuilder.buildAndBind(Map.of(
							Source.CARD, value.getCard().dataProperty(),
							Source.CARD_FACE, value.dataProperty(),
							Source.TEMPLATE, value.getTemplate().dataProperty()));

					loader.getNamespace().put("data", data);

					setContent(loader.load());
					TemplateController controller = loader.getController();
					controller.imageProperty().set(imageUrl == null ? null : new Image(imageUrl));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			});
			root.setId(isFrontFace() ? "front" : "back");
		});
	}



	private boolean isFrontFace() {
		return cardFaceProperty().flatMap(CardFaceFX::cardProperty)
				.flatMap(CardFX::frontProperty)
				.getValue() == getCardFace();
	}

	public ObjectProperty<CardFaceFX> cardFaceProperty() {
		return cardFace;
	}

	public CardFaceFX getCardFace() {
		return cardFaceProperty().get();
	}

	public void setCardFace(CardFaceFX cardFace) {
		this.cardFaceProperty().set(cardFace);
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

	public void loadFxml(String fxml) {
		FXUtils.runFx(() -> root.getChildren().setAll());
		SpringFXLoader loader = beanFactory.getBean(SpringFXLoader.class);
		try {
			CardFaceFX cardFace = getCardFace();
			TreeProperty<Object> data = TreePropertyBuilder.buildAndBind(Map.of(
					Source.CARD, cardFace.getCard().dataProperty(),
					Source.CARD_FACE, cardFace.dataProperty(),
					Source.TEMPLATE, cardFace.getTemplate().dataProperty()));

			loader.getNamespace().put("data", data);

			ByteArrayInputStream fxmlBytes = new ByteArrayInputStream(fxml.getBytes());
			setContent(loader.load(fxmlBytes));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
