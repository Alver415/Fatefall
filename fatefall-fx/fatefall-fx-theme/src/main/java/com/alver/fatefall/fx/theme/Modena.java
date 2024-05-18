package com.alver.fatefall.fx.theme;

import atlantafx.base.theme.Theme;

public class Modena implements Theme {

	@Override
	public String getName() {
		return "Modena";
	}

	@Override
	public String getUserAgentStylesheet() {
		return "com/sun/javafx/scene/control/skin/modena/modena.css";
	}

	@Override
	public String getUserAgentStylesheetBSS() {
		return "com/sun/javafx/scene/control/skin/modena/modena.bss";
	}

	@Override
	public boolean isDarkMode() {
		return false;
	}
}
