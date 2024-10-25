package com.alver.fatefall.scryfall.api;

import com.alver.fatefall.fx.app.view.entity.card.template.ImageTemplate;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.utils.TreeProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.scene.image.Image;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class ScryfallCardDeserializer extends StdDeserializer<CardFX<?,?>> {

	private final String defaultCardBackUrl = Objects.requireNonNull(CardApi.class.getResource("magic_card_back.png")).toExternalForm();

	public ScryfallCardDeserializer() {
		super(CardFX.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CardFX deserialize(JsonParser parser, DeserializationContext context)
			throws IOException {
		JsonNode json = parser.getCodec().readTree(parser);

		CardFX card = new CardFX();
		card.setJson(json.toPrettyString());
		card.setName(json.findValue("name").asText());

		String frontUrl, backUrl;
		card.setData(context.readTreeAsValue(json, TreeProperty.class));
		if (!json.path("card_faces").isEmpty()) {
			JsonNode front = json.path("card_faces").get(0);
			card.getFront().setData(context.readTreeAsValue(front, TreeProperty.class));
			frontUrl = front.path("image_uris").path("normal").asText();

			JsonNode back = json.path("card_faces").get(1);
			card.getBack().setData(context.readTreeAsValue(back, TreeProperty.class));
			backUrl = back.path("image_uris").path("normal").asText();
		} else {
			frontUrl = json.path("image_uris").path("normal").asText();
			backUrl = defaultCardBackUrl;
		}

		card.getFront().setTemplate(new ImageTemplate(new Image(frontUrl, true)));
		card.getBack().setTemplate(new ImageTemplate(new Image(backUrl, true)));

		return card;
	}
}