package com.alver.fatefall.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DatabaseClient {

	private final DataSource dataSource;

	@Autowired
	public DatabaseClient(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> query(String sql) {
		return query(sql, ResultSetTransformer.DEFAULT_TRANSFORMER);
	}

	public <T> List<T> query(String sql, ResultSetTransformer<T> transformer) {
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			List<T> response = new ArrayList<>();
			while (resultSet.next()) {
				response.add(transformer.apply(resultSet));
			}
			return response;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void execute(String sql) {
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public interface ResultSetTransformer<T> {

		T apply(ResultSet resultSet) throws SQLException;

		ResultSetTransformer<Map<String, Object>> DEFAULT_TRANSFORMER = (resultSet -> {
			ResultSetMetaData metaData = resultSet.getMetaData();
			Map<String, Object> row = new HashMap<>(metaData.getColumnCount());
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				String columnName = metaData.getColumnName(i);
				Object value = resultSet.getObject(i);
				row.put(columnName, value);
			}
			return row;
		});
	}
}
