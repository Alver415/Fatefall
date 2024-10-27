package com.alver.fatefall.fx.app.view.entity.card.template;

import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.model.TemplateFX;
import com.alver.springfx.SpringFXLoader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class FXMLTemplate extends TemplateFX {

	private static final Logger log = LoggerFactory.getLogger(FXMLTemplate.class);
	private final ObjectProperty<URL> fxml = new SimpleObjectProperty<>(this, "fxml");

	public FXMLTemplate() {
	}
	public FXMLTemplate(URL fxml) {
		setFxml(fxml);
	}

	public ObjectProperty<URL> fxmlProperty() {
		return this.fxml;
	}

	public URL getFxml() {
		return this.fxmlProperty().get();
	}

	public void setFxml(URL value) {
		this.fxmlProperty().set(value);
	}

	@Override
	public Parent build(CardFaceFX<?> cardFace) {
		try {
			SpringFXLoader loader = new SpringFXLoader();
			loader.setLocation(getFxml());
			// FIXME: Temporary hack to recognize classes from plugins.
			// Without this line, loader fails to find controller classes defined in plugin jars.
			loader.setClassLoader(cardFace.getClass().getClassLoader());
			loader.getNamespace().put("card", cardFace.getCard());
			loader.getNamespace().put("face", cardFace);
			loader.getNamespace().put("template", this);

			Parent node = loader.load();
			TemplateController<CardFaceFX<?>> controller = loader.getController();
			controller.setModel(cardFace);
			return node;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return new Label("ERROR");
		}
	}
}
