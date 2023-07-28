package com.alver.fatefall.app;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Configuration
public class JacksonConfiguration extends SimpleModule {

	@Component
	public static class JavaFXJacksonModule extends SimpleModule {
		public JavaFXJacksonModule() {
			addSerializer(ListProperty.class, new ListPropertySerializer());
			addDeserializer(ListProperty.class, new ListPropertyDeserializer());
		}
	}

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
		objectMapper.registerModule(new JacksonConfiguration());
		return objectMapper;
	}

	@Bean
	public ObjectWriter getObjectWriter(ObjectMapper objectMapper) {
		return objectMapper.writerWithDefaultPrettyPrinter();
	}

}
