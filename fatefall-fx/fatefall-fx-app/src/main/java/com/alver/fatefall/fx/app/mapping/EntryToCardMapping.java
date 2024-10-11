package com.alver.fatefall.fx.app.mapping;

import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fsfx.FileSystemEntry;
import com.alver.fsfx.util.ByteBufferToStringConverter;
import com.alver.fsfx.util.Converter;
import com.alver.fsfx.util.StringToJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import javafx.beans.property.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class EntryToCardMapping {

	private static final Logger log = LoggerFactory.getLogger(EntryToCardMapping.class);

	private static final ByteBufferToStringConverter BYTES_TO_STRING = new ByteBufferToStringConverter();
	private static final StringToJsonConverter STRING_TO_JSON  = new StringToJsonConverter();
	private static final Converter<ByteBuffer, JsonNode> BYTES_TO_JSON = BYTES_TO_STRING.compound(STRING_TO_JSON);

	private static final List<Object> list = new ArrayList<>();

	public static CardFX map(FileSystemEntry entry){
		CardFX card = new CardFX();
		ObjectProperty<JsonNode> json = new SimpleObjectProperty<>(card, "json");
		BYTES_TO_JSON.inverted().bindBidirectional(json, entry.contentProperty());

		bindPropertyToJson(json, card.nameProperty());


		//TODO: Temp hack to ensure these intermediary properties aren't garbage collected.
		list.add(json);
		return card;
	}


	private static void bindPropertyToJson(Property<JsonNode> jsonProperty, Property<String> property) {
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
