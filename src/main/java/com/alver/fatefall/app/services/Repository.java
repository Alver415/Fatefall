package com.alver.fatefall.app.services;

import com.alver.fatefall.api.models.CardCollection;
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
		File dataDirectory = new File("data");
		if (!dataDirectory.exists() && !dataDirectory.mkdirs()) {
            throw new RuntimeException("Failed to create data directory: " + dataDirectory.getAbsolutePath());
        }
		for (File file : Objects.requireNonNull(dataDirectory.listFiles())) {
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
			Files.writeString(Path.of("data/" + cardCollection.getId()), json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
