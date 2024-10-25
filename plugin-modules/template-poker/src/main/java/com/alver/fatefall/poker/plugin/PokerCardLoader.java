package com.alver.fatefall.poker.plugin;

import com.alver.fatefall.fx.core.interfaces.EntityLoader;
import com.alver.fatefall.poker.plugin.model.PokerCard;
import com.alver.fatefall.poker.plugin.model.Rank;
import com.alver.fatefall.poker.plugin.model.Suit;
import com.alver.fsfx.FileSystemEntry;
import com.alver.fsfx.util.ByteBufferToStringConverter;
import com.alver.fsfx.util.Converter;
import com.alver.fsfx.util.StringToJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import javafx.beans.property.*;
import org.pf4j.Extension;
import org.pf4j.ExtensionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Extension
public class PokerCardLoader implements EntityLoader<PokerCard>, ExtensionPoint {

	private static final List<Object> list = new ArrayList<>();
	private static final ByteBufferToStringConverter BYTES_TO_STRING = new ByteBufferToStringConverter();
	private static final StringToJsonConverter STRING_TO_JSON  = new StringToJsonConverter();
	private static final Converter<ByteBuffer, JsonNode> BYTES_TO_JSON = BYTES_TO_STRING.compound(STRING_TO_JSON);
	private static final Logger log = LoggerFactory.getLogger(PokerCardLoader.class);

	@Override
	public PokerCard load(FileSystemEntry entry) {
		PokerCard card = new PokerCard();

		ObjectProperty<JsonNode> json = new SimpleObjectProperty<>(card, "json");
		BYTES_TO_JSON.inverted().bindBidirectional(json, entry.contentProperty());

		Converter<String, Rank> rankConverter = Converter.of(Rank::fromSymbol, Rank::getSymbol);
		StringProperty rankString = new SimpleStringProperty(this, "rankString");
		rankConverter.bindBidirectional(rankString, card.getFront().rankProperty());
		bindPropertyToJson(json, rankString);

		Converter<String, Suit> suitConverter = Converter.of(Suit::fromSymbol, Suit::getSymbol);
		StringProperty suitString = new SimpleStringProperty(this, "suitString");
		suitConverter.bindBidirectional(suitString, card.getFront().suitProperty());
		bindPropertyToJson(json, suitString);

		list.add(rankString);
		return card;
	}


	private void bindPropertyToJson(Property<JsonNode> jsonProperty, Property<String> property) {
		String propertyName = property.getName();
		jsonProperty.subscribe(jsonValue -> {
			String textValue = jsonValue == null ? "" : jsonValue.findPath(propertyName).textValue();
			property.setValue(textValue);
		});
		property.addListener((_, _, propertyValue) -> {
			JsonNode jsonValue = jsonProperty.getValue();
			if (jsonValue != null && jsonValue.deepCopy() instanceof ObjectNode node) {
				node.set(propertyName, new TextNode(propertyValue));
				jsonProperty.setValue(node);
			} else {
				log.warn("Invalid JSON: {}", jsonProperty);
				throw new RuntimeJsonMappingException("Invalid JSON: " + jsonProperty);
			}
		});
	}

}
