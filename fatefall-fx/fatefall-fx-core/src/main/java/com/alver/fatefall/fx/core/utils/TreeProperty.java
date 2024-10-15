package com.alver.fatefall.fx.core.utils;

import com.alver.fatefall.fx.core.model.Source;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static com.sun.javafx.fxml.BeanAdapter.PROPERTY_SUFFIX;

@SuppressWarnings("unchecked")
public class TreeProperty<T> implements
		DelegatingProperty<T>,
		DelegatingMap<String, TreeProperty<Object>> {

	private final MapProperty<String, TreeProperty<Object>> childrenMap;

	private final Property<T> valueProperty;
	private final MapProperty<Source, Property<T>> propertyMap;
	private final ReadOnlyObjectWrapper<Source> source;

	public TreeProperty() {
		this(null, null, null);
	}
	public TreeProperty(Object bean, String name) {
		this(bean, name, null);
	}

	public TreeProperty(Object bean, String name, T initialValue) {
		this.childrenMap = new SimpleMapProperty<>(FXCollections.observableHashMap());
		this.valueProperty = new SimpleObjectProperty<>(bean, name, initialValue);
		this.propertyMap = new SimpleMapProperty<>(FXCollections.observableHashMap());
		this.propertyMap.addListener((MapChangeListener<? super Source, ? super Property<T>>) change ->
				change.getMap().entrySet().stream()
						.peek(entry -> this.unbindBidirectional(entry.getValue()))
						.min(Comparator.comparingInt(entry -> entry.getKey().ordinal()))
						.ifPresent(entry -> this.bindBidirectional(entry.getValue())));

		this.source = new ReadOnlyObjectWrapper<>();
		this.source.bind(Bindings.createObjectBinding(() ->
				propertyMap.keySet().stream()
						.min(Comparator.comparingInt(Source::ordinal))
						.orElse(null), propertyMap));
	}

	public T get() {
		return getValue();
	}

	public Optional<T> get(Source source) {
		return getProperty(source).map(Property::getValue);
	}

	public Optional<T> getValue(Source source) {
		return get(source);
	}

	public void set(T value) {
		this.setValue(value);
	}

	public void set(Source source, T value) {
		getProperty(source).ifPresentOrElse(
				property -> property.setValue(value),
				() -> this.propertyMap.put(source, new SimpleObjectProperty<>(value)));
	}

	public void setValue(Source source, T value) {
		this.set(source, value);
	}

	public MapProperty<Source, Property<T>> propertyMapProperty() {
		return propertyMap;
	}

	public ReadOnlyObjectProperty<Source> sourceProperty() {
		return source.getReadOnlyProperty();
	}

	public MapProperty<String, TreeProperty<Object>> childrenMapProperty() {
		return childrenMap;
	}

	public ObservableMap<String, TreeProperty<Object>> getChildrenMap() {
		return childrenMap.get();
	}

	public void setChildrenMap(ObservableMap<String, TreeProperty<Object>> childrenMap) {
		this.childrenMap.set(childrenMap);
	}

	public void merge(TreeProperty<Object> other) {
		mergeValues(other);
		mergeChildren(other);
	}

	private void mergeValues(TreeProperty<Object> other) {
		for (Map.Entry<Source, ?> entry : other.propertyMap.entrySet()) {
			mergeValue(other, (Map.Entry<Source, Property<T>>) entry);
		}
	}

	private void mergeChildren(TreeProperty<Object> other) {
		for (Entry<String, TreeProperty<Object>> entry : other.childrenMap.entrySet()) {
			mergeChild(entry.getKey(), entry.getValue());
		}
	}

	private void mergeValue(TreeProperty<Object> other, Map.Entry<Source, Property<T>> entry) {
		Source source = entry.getKey();
		if (propertyMap.containsKey(source) &&
				!Objects.equals(this.propertyMap.get(source), other.propertyMap.get(source))) {
			throw new AssertionError("cannot merge different values with matching sources: key=%s, this=%s, other%s"
					.formatted(source, this.propertyMap.get(source), other.propertyMap.get(source)));
		} else {
			propertyMap.put(source, entry.getValue());
		}
	}

	public void mergeChild(String id, TreeProperty<Object> otherChild) {
		if (this.childrenMap.containsKey(id)) {
			TreeProperty<Object> thisChild = this.childrenMap.get(id);
			thisChild.merge(otherChild);
		} else {
			this.childrenMap.put(id, otherChild);
		}
	}

	public TreeProperty<Object> getChild(String key) {
		return childrenMap.get(key);
	}

	public TreeProperty<Object> find(String path) {
		return this.find(splitPath(path));
	}

	public TreeProperty<Object> find(String... path) {
		return this.find(Arrays.asList(path));
	}

	public TreeProperty<Object> find(List<String> path) {
		TreeProperty<Object> child = getChild(path.get(0));
		List<String> subPath = path.subList(1, path.size());
		return subPath.isEmpty() ?
				child :
				(child == null ?
						null :
						child.find(subPath));
	}

	public TreeProperty<Object> findOrCreate(String path) {
		return this.findOrCreate(splitPath(path));
	}

	public TreeProperty<Object> findOrCreate(String... path) {
		return this.findOrCreate(Arrays.asList(path));
	}

	public TreeProperty<Object> findOrCreate(List<String> path) {
		TreeProperty<Object> child = getChild(path.get(0));
		if (child == null) {
			child = new TreeProperty<>();
		}
		List<String> subPath = path.subList(1, path.size());
		return subPath.isEmpty() ?
				child :
				child.findOrCreate(subPath);
	}

	public void putProperty(Source source, Property<T> property) {
		this.propertyMap.put(source, property);
	}

	public Optional<Property<T>> getProperty(Source source) {
		return Optional.ofNullable(this.propertyMap.get(source));
	}

	@Override
	public Property<T> getDelegateProperty() {
		return valueProperty;
	}

	@Override
	public Map<String, TreeProperty<Object>> getDelegateMap() {
		return childrenMap;
	}

	public static <T> TreeProperty<T> create(
			Source source,
			Property<Object> property) {
		TreeProperty<T> node = new TreeProperty<>();

		if (property instanceof MapProperty map) {
			Consumer<Map.Entry<String, Property<Object>>> createAndMergeChildNode = entry -> {
				TreeProperty<Object> childNode = create(
						source,
						entry.getValue());
				node.mergeChild(entry.getKey(), childNode);
			};
			map.entrySet().forEach(createAndMergeChildNode);
		} else if (property instanceof ListProperty list) {
			AtomicInteger index = new AtomicInteger();
			Consumer<Property<Object>> createAndMergeChildNode = element -> {
				String id = "[%d]".formatted(index.getAndIncrement());
				TreeProperty<Object> childNode = create(
						source,
						element);
				node.mergeChild(id, childNode);
			};
			list.forEach(createAndMergeChildNode);
		} else if (property instanceof SetProperty set) {
			AtomicInteger index = new AtomicInteger();
			Consumer<Property<Object>> createAndMergeChildNode = element -> {
				String id = "[%d]".formatted(index.getAndIncrement());
				TreeProperty<Object> childNode = create(
						source,
						element);
				node.mergeChild(id, childNode);
			};
			set.forEach(createAndMergeChildNode);
		} else {
			node.propertyMap.put(source, (Property<T>) property);
		}
		return node;
	}

	private static String[] splitPath(String path) {
		return path.split("\\.");
	}

	@Override
	public boolean containsKey(Object key) {
		boolean result = getDelegateMap().containsKey(key);
		if (!result) {
			// Hack: If we fail to find a property with the suffix, try again to see if we have the same property
			// name without the suffix. Needed to make BeanAdapter be able to find properties without the suffix.
			result = getDelegateMap().containsKey(keyWithoutPropertySuffix(String.valueOf(key)));
		}
		return result;
	}

	@Override
	public TreeProperty<Object> get(Object key) {
		TreeProperty<Object> result = getDelegateMap().get(key);
		if (result == null) {
			// Hack: If we fail to find a property with the suffix, try again to see if we have the same property
			// name without the suffix. Needed to make BeanAdapter be able to find properties without the suffix.
			result = getDelegateMap().get(keyWithoutPropertySuffix(String.valueOf(key)));
		}
		return result;
	}

	private static String keyWithoutPropertySuffix(String keyString) {
		if (!keyString.endsWith(PROPERTY_SUFFIX)) {
			return keyString;
		}
		return keyString.substring(0, keyString.length() - PROPERTY_SUFFIX.length());
	}
	@Override
	public String toString() {
		return String.valueOf(getValue());
	}
}
