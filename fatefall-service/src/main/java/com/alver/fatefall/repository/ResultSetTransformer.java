package com.alver.fatefall.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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