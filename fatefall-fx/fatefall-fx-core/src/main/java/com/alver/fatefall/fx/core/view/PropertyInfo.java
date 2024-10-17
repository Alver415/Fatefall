package com.alver.fatefall.fx.core.view;

import java.lang.reflect.Method;

public record PropertyInfo(
		String name,
		String displayName,
		String category,
		int order,
		Method property,
		Method setter,
		Method getter,
		Class<?> type) {

	@Override
	public String displayName(){
		return displayName != null ? displayName :
				name != null ? name :
				"???";
	}
}
