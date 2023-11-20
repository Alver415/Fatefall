package com.alver.fatefall.fx.core.interfaces;

import javafx.scene.Node;

public interface AppController {

	void addView(AppView appView);
	default void addView(String name, Node node){
		addView(AppView.of(name, node));
	}
	default void addView(String name, String title, Node node){
		addView(AppView.of(name, title, node));
	}

}
