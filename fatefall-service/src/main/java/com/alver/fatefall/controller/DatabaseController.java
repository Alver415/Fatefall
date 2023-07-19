package com.alver.fatefall.controller;

import com.alver.fatefall.repository.DatabaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/database")
public class DatabaseController {

	private final DatabaseClient databaseClient;

	@Autowired
	public DatabaseController(DatabaseClient databaseClient) {
		this.databaseClient = databaseClient;
	}

	@GetMapping("/query")
	public List<Map<String, Object>> query(
			@RequestBody String sql) {
		return databaseClient.query(sql);
	}
	@GetMapping("/execute")
	public void execute(
			@RequestBody String sql) {
		databaseClient.execute(sql);
	}
}
