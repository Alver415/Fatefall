package com.alver.fatefall.fx.theme;

import atlantafx.base.theme.Theme;

public class Caspian implements Theme {

	@Override
	public String getName() {
		return "Caspian";
	}

	@Override
	public String getUserAgentStylesheet() {
		return "com/sun/javafx/scene/control/skin/caspian/caspian.css";
	}

	@Override
	public String getUserAgentStylesheetBSS() {
		return "com/sun/javafx/scene/control/skin/caspian/caspian.bss";
	}

	@Override
	public boolean isDarkMode() {
		return false;
	}
}
