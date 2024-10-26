package com.alver.fatefall.fx.app.view.entity.card.template;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.model.TemplateFX;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class ImageTemplate extends TemplateFX {

	private static final Logger log = LoggerFactory.getLogger(ImageTemplate.class);
	private final ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image");

	public ImageTemplate() {
		this(null);
	}
	public ImageTemplate(Image image) {
		setImage(image);
	}

	public ObjectProperty<Image> imageProperty() {
		return this.image;
	}

	public Image getImage() {
		return this.imageProperty().get();
	}

	public void setImage(Image value) {
		this.imageProperty().set(value);
	}

	@Override
	public Parent build(CardFaceFX<?> cardFace) {
		ImageView imageView = new ImageView();

		//FIXME: Temporary hack to bind to imageProperty that we don't have knowledge of in this scope.
		try {
			Property<Image> imageProperty = (Property<Image>) cardFace.getClass().getDeclaredMethod("imageProperty").invoke(cardFace);
			imageView.imageProperty().bind(imageProperty);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			log.error(e.getMessage(), e);
		}
		imageView.fitWidthProperty().bind(cardFace.cardProperty().flatMap(CardFX::widthProperty));
		imageView.fitHeightProperty().bind(cardFace.cardProperty().flatMap(CardFX::heightProperty));
		return new StackPane(imageView);
	}
}
