package com.alver.fatefall.app;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Configuration
public class JacksonFXConfiguration{

	public static class ListPropertySerializer extends JsonSerializer<ListProperty> {
		@Override
		public void serialize(ListProperty listProperty, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeObject(listProperty.get());
		}
	}

	public static class ListPropertyDeserializer extends JsonDeserializer<ListProperty> {
		@Override
		public ListProperty deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
			List list = jsonParser.readValueAs(new TypeReference<List>() {
			});
			return new SimpleListProperty<>(FXCollections.observableArrayList(list));
		}
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule entityFXModule = new SimpleModule("EntityFXModule");
		entityFXModule.addSerializer(ListProperty.class, new ListPropertySerializer());
		entityFXModule.addDeserializer(ListProperty.class, new ListPropertyDeserializer());
		objectMapper.registerModule(entityFXModule);
		return objectMapper;
	}

	@Bean
	public ObjectWriter getObjectWriter(ObjectMapper objectMapper) {
		return objectMapper.writerWithDefaultPrettyPrinter();
	}

}
