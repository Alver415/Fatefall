package com.alver.fatefall.app.services;

import com.alver.fatefall.api.Card;
import com.alver.fatefall.api.CardCollection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Repository {

	private final ObjectMapper mapper = new ObjectMapper();

	public List<CardCollection> getCardCollections() {
		List<CardCollection> list = new ArrayList<>();
		for (File file : Objects.requireNonNull(new File("repo").listFiles())) {
			try {
				list.add(mapper.readValue(file, CardCollection.class));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	public void save(CardCollection cardCollection) {
		try {
			String json = mapper.writeValueAsString(cardCollection);
			Files.writeString(Path.of("repo/" + cardCollection.getId()), json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
