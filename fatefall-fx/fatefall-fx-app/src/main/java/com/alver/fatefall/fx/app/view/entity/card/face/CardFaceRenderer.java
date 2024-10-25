package com.alver.fatefall.fx.app.view.entity.card.face;

import com.alver.fatefall.fx.app.view.entity.card.template.TemplateController;
import com.alver.fatefall.fx.core.model.CardFaceFX;
import com.alver.fatefall.fx.core.utils.BackgroundUtil;
import com.alver.springfx.SpringFXLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.function.Supplier;

public class CardFaceRenderer {

	private static final Logger log = LoggerFactory.getLogger(CardFaceRenderer.class);

	public final Supplier<SpringFXLoader> loaderSupplier;

	public CardFaceRenderer(Supplier<SpringFXLoader> loaderSupplier){
		this.loaderSupplier = loaderSupplier;
	}

	public <CardFace extends CardFaceFX> Parent loadFXML(CardFace face) {
		SpringFXLoader loader = loaderSupplier.get();
		try {
			String imageUrl = face.getTemplate().getImageUrl();
			String fxmlUrl = face.getTemplate().getFxmlUrl();
			URL fxml = fxmlUrl != null ? new URL(fxmlUrl) :
					imageUrl != null ? TemplateController.class.getResource("ImageTemplate.fxml") :
							TemplateController.class.getResource("PlaceholderTemplate.fxml");

			loader.setLocation(fxml);
			Parent node = loader.load();
			TemplateController<CardFace> controller = loader.getController();
			controller.setModel(face);
			return node;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return loadDefaultCardFace();
		}
	}

	public Parent loadDefaultCardFace() {
		Pane pane = new Pane();
		pane.setBackground(BackgroundUtil.checkeredBackground());
		return pane;
	}


}
