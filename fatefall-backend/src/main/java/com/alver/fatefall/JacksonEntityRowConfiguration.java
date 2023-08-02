package com.alver.fatefall;

import com.alver.fatefall.data.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonEntityRowConfiguration extends SimpleModule {

	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule fatefallModule = new SimpleModule("FatefallModule");
		fatefallModule.addAbstractTypeMapping(Workspace.class, WorkspaceRow.class);
		fatefallModule.addAbstractTypeMapping(Card.class, CardRow.class);
		fatefallModule.addAbstractTypeMapping(CardFace.class, CardFaceRow.class);
		objectMapper.registerModule(fatefallModule);
		return objectMapper;
	}

	@Bean
	public ObjectWriter getObjectWriter(ObjectMapper objectMapper) {
		return objectMapper.writerWithDefaultPrettyPrinter();
	}

}
