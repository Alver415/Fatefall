package com.alver.fatefall.fx.core.view;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class LinkableProperty<T> implements Property<T> {

	private static final Logger log = LoggerFactory.getLogger(LinkableProperty.class);
	private final Property<T> property;

	public LinkableProperty(T initialValue) {
		this(null, null, initialValue);
	}

	public LinkableProperty(Object bean, String name) {
		this(bean, name, null);
	}

	public LinkableProperty(Object bean, String name, T initialValue) {
		this(new SimpleObjectProperty<>(bean, name, initialValue));
	}

	public LinkableProperty(Property<T> property) {
		this.property = property;
	}


	public void link(LinkableProperty<T> other) {
		this.linkedTo.set(other);
		other.linkedBy.add(this);
		try {
			bind(other);
		} catch (Exception e) {
			log.warn("Failed to link property '%s' to '%s'".formatted(this, other), e);
			unlink();
		}
	}

	public void unlink() {
		unbind();
		LinkableProperty<T> other = linkedTo.get();
		linkedTo.set(null);
		other.linkedBy.remove(this);
	}

	public void linkBidirectional(LinkableProperty<T> other) {
		bidirectionalLinks.add(other);
		bindBidirectional(other);
	}

	public void unlinkBidirectional(LinkableProperty<T> other) {
		unbindBidirectional(other);
		bidirectionalLinks.remove(other);
	}

	private final ReadOnlyObjectWrapper<LinkableProperty<T>> linkedTo = new ReadOnlyObjectWrapper<>(this, "linkedTo");

	public ReadOnlyObjectProperty<LinkableProperty<T>> linkedToProperty() {
		return this.linkedTo.getReadOnlyProperty();
	}

	public LinkableProperty<T> getLinkedTo() {
		return this.linkedToProperty().get();
	}


	private final ReadOnlyObjectWrapper<LinkableProperty<T>> lastLinkedTo =
			new ReadOnlyObjectWrapper<>(this, "lastLinkedTo") {
				{
					bind(linkedTo.flatMap(LinkableProperty::lastLinkedToProperty)
							.orElse(LinkableProperty.this));
				}
			};

	public ReadOnlyObjectProperty<LinkableProperty<T>> lastLinkedToProperty() {
		return this.lastLinkedTo.getReadOnlyProperty();
	}

	public LinkableProperty<T> getLastLinkedTo() {
		return this.lastLinkedToProperty().get();
	}


	private final ReadOnlyListWrapper<LinkableProperty<T>> dependencies =
			new ReadOnlyListWrapper<>(this, "dependencies") {
				{
					ObservableList<LinkableProperty<T>> list = FXCollections.observableArrayList();
					linkedTo.flatMap(LinkableProperty::dependenciesProperty).addListener((_, o, n) -> {
						list.clear();
						list.add(LinkableProperty.this);
						if (n != null) {
							list.addAll(n);
							n.addListener((ListChangeListener<? super LinkableProperty<T>>) c -> {
								list.clear();
								list.add(LinkableProperty.this);
								list.addAll(c.getList());
							});
						}
					});
					list.add(LinkableProperty.this);
					bind(new SimpleListProperty<>(list));

//					ObservableList<LinkableProperty<T>> baseCase = FXCollections.observableArrayList();
//					baseCase.add(LinkableProperty.this);
//
//					bind(linkedTo.map(linked -> {
//						ObservableList<LinkableProperty<T>> l = FXCollections.observableArrayList();
//						linked.dependenciesProperty().addListener((InvalidationListener) invalidation -> {
//							l.clear();
//							l.add(LinkableProperty.this);
//							l.addAll(linked.dependenciesProperty().get());
//						});
//						return l;
//					}).orElse(baseCase));
				}
			};

	public ReadOnlyListProperty<LinkableProperty<T>> dependenciesProperty() {
		return this.dependencies.getReadOnlyProperty();
	}

	public ObservableList<LinkableProperty<T>> getDependencies() {
		return this.dependenciesProperty().get();
	}

	public ObservableList<LinkableProperty<T>> getDependencyList() {
		if (getLinkedTo() == null) {
			ObservableList<LinkableProperty<T>> list = FXCollections.observableArrayList();
			list.add(this);
			return list;
		} else {
			ObservableList<LinkableProperty<T>> list = getLinkedTo().getDependencyList();
			list.add(this);
			return list;
		}
	}


	private final ReadOnlyBooleanWrapper isLinkedTo = new ReadOnlyBooleanWrapper(this, "isLinkedTo") {
		{
			bind(linkedTo.map(Objects::nonNull).orElse(false));
		}
	};

	public ReadOnlyBooleanProperty isLinkedToProperty() {
		return this.isLinkedTo.getReadOnlyProperty();
	}

	public boolean isLinkedTo() {
		return this.isLinkedToProperty().get();
	}

	private final ReadOnlyListWrapper<LinkableProperty<T>> linkedBy = new ReadOnlyListWrapper<>(
			this, "linkedBy", FXCollections.observableArrayList());

	public ReadOnlyListProperty<LinkableProperty<T>> linkedByProperty() {
		return this.linkedBy.getReadOnlyProperty();
	}

	public ObservableList<LinkableProperty<T>> getLinkedBy() {
		return this.bidirectionalLinksProperty().get();
	}


	private final ReadOnlyListWrapper<LinkableProperty<T>> bidirectionalLinks = new ReadOnlyListWrapper<>(
			this, "bidirectionalLinks", FXCollections.observableArrayList());

	public ReadOnlyListProperty<LinkableProperty<T>> bidirectionalLinksProperty() {
		return this.bidirectionalLinks.getReadOnlyProperty();
	}

	public ObservableList<LinkableProperty<T>> getBidirectionalLinks() {
		return this.bidirectionalLinksProperty().get();
	}


	//region Delegated Methods
	@Override
	public void bind(ObservableValue<? extends T> observable) {
		property.bind(observable);
	}

	@Override
	public void unbind() {
		property.unbind();
	}

	@Override
	public boolean isBound() {
		return property.isBound();
	}

	@Override
	public void bindBidirectional(Property<T> other) {
		property.bindBidirectional(other);
	}

	@Override
	public void unbindBidirectional(Property<T> other) {
		property.unbindBidirectional(other);
	}

	@Override
	public Object getBean() {
		return property.getBean();
	}

	@Override
	public String getName() {
		return property.getName();
	}

	@Override
	public void addListener(ChangeListener<? super T> listener) {
		property.addListener(listener);
	}

	@Override
	public void removeListener(ChangeListener<? super T> listener) {
		property.removeListener(listener);
	}

	@Override
	public T getValue() {
		return property.getValue();
	}

	@Override
	public void addListener(InvalidationListener listener) {
		property.addListener(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		property.removeListener(listener);
	}

	@Override
	public void setValue(T value) {
		property.setValue(value);
	}
	//endregion Delegated Methods

	static <T> LinkableProperty<T> wrap(Property<T> property) {
		return new LinkableProperty<>(property);
	}
}
