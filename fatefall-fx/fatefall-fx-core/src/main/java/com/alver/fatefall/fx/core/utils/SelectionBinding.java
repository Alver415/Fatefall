package com.alver.fatefall.fx.core.utils;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.SelectionModel;
import javafx.util.Subscription;

import java.util.Objects;
import java.util.function.Consumer;

public class SelectionBinding {

	public static <T> Subscription bindBidirectional(
			ObservableValue<T> aObservable,
			Consumer<T> aSetter,
			ObservableValue<T> bObservable,
			Consumer<T> bSetter) {
		Subscription s1 = aObservable.subscribe((oldValue, newValue) -> {
			if (!Objects.equals(oldValue, newValue)) bSetter.accept(newValue);
		});
		Subscription s2 = bObservable.subscribe((oldValue, newValue) -> {
			if (!Objects.equals(oldValue, newValue)) aSetter.accept(newValue);
		});
		return Subscription.combine(s1, s2);
	}

	public static <T extends SelectionModel<T>> Subscription bindBidirectional(T a, T b) {
		Subscription s1 = a.selectedItemProperty().subscribe((oldValue, newValue) -> {
			if (!Objects.equals(oldValue, newValue)) b.select(newValue);
		});
		Subscription s2 = b.selectedItemProperty().subscribe((oldValue, newValue) -> {
			if (!Objects.equals(oldValue, newValue)) a.select(newValue);
		});
		return Subscription.combine(s1, s2);
	}

	public static <T> Subscription bindBidirectional(SelectionModel<T> a, Property<T> b) {
		Subscription s1 = a.selectedItemProperty().subscribe((oldValue, newValue) -> {
			if (!Objects.equals(oldValue, newValue)) b.setValue(newValue);
		});
		Subscription s2 = b.subscribe((oldValue, newValue) -> {
			if (!Objects.equals(oldValue, newValue)) a.select(newValue);
		});
		return Subscription.combine(s1, s2);
	}
}
