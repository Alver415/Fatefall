package com.alver.fatefall.fx.app.view.entity.card;

import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import org.reflections.Reflections;

import java.util.Comparator;
import java.util.List;

public class SelectNodeClassDialog extends ChoiceDialog<Class<? extends Node>> {

	public SelectNodeClassDialog() {
		super(null, getNodeClasses());
	}
	public static List<Class<? extends Node>> getNodeClasses() {
		return new Reflections("javafx.scene").getSubTypesOf(Node.class).stream()
				.filter(clz -> !clz.isAnonymousClass())
				.filter(SelectNodeClassDialog::hasDefaultConstructor)
				.sorted(Comparator.comparing(Class::getName))
				.toList();
	}
	private static boolean hasDefaultConstructor(Class<? extends Node> clz) {
		try {
			return clz.getConstructor() != null;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

}
