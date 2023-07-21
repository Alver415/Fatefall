package com.alver.fatefall.api;

import java.util.List;
import java.util.Map;

public interface DatabaseApi {

	List<Map<String, Object>> query(String sql);

	void execute(String sql);
}
