package com.alver.fatefall.repository;

import com.alver.fatefall.database.row.CardRow;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public abstract class BaseRepository {

	protected final DatabaseClient databaseClient;

	public BaseRepository(DatabaseClient databaseClient){
		this.databaseClient = databaseClient;
	}

	protected CardRow exactlyOne(List<CardRow> query) {
		if (query.size() == 0){
			throw new RuntimeException("Not Found.");
		} else if (query.size() == 1){
			return query.get(0);
		} else {
			throw new RuntimeException("Found Multiple.");
		}
	}
}
