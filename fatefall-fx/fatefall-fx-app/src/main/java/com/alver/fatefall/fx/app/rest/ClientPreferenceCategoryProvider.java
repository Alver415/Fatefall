package com.alver.fatefall.fx.app.rest;

import com.alver.fatefall.fx.app.FatefallProperties;
import com.alver.fatefall.fx.core.interfaces.AppPreferenceCategoryProvider;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ClientPreferenceCategoryProvider implements AppPreferenceCategoryProvider {

	private final FatefallProperties properties;

	public ClientPreferenceCategoryProvider(FatefallProperties properties) {
		this.properties = properties;
	}

	@Override
	public Category getCategory() {
		return Category.of("Connection",
				Group.of("API",
						Setting.of("Scheme", properties.schemeProperty()),
						Setting.of("Host", properties.hostProperty()),
						Setting.of("Port", properties.portProperty())));
	}
}
