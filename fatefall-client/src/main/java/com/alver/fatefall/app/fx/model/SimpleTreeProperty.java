package com.alver.fatefall.app.fx.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SimpleTreeProperty<T> extends SimpleObjectProperty<T> {

    private final StringProperty id;
    private final ObjectProperty<SimpleTreeProperty<?>> parent;
    private final MapProperty<String, SimpleTreeProperty<?>> childrenMap;
    private final MapProperty<Source, Property<T>> propertyMap;

    public SimpleTreeProperty() {
        this("");
    }
    public SimpleTreeProperty(String id) {
        Objects.requireNonNull(id);
        this.id = new SimpleStringProperty(id);
        this.parent = new SimpleObjectProperty<>();
        this.childrenMap = new SimpleMapProperty<>(FXCollections.observableHashMap());
        this.propertyMap = new SimpleMapProperty<>(FXCollections.observableHashMap());

        this.propertyMap.addListener((MapChangeListener<? super Source, ? super Property<T>>) change ->
                change.getMap().entrySet().stream()
                        .peek(entry -> this.unbindBidirectional(entry.getValue()))
                        .min(Comparator.comparingInt(entry -> entry.getKey().getPriority()))
                        .ifPresent(entry -> this.bindBidirectional(entry.getValue())));
    }

    private static String[] splitPath(String path) {
        return path.split("\\.");
    }

    public static <T> SimpleTreeProperty<T> build(String id) {
        return build(id, List.of());
    }

    public static <T> SimpleTreeProperty<T> build(
            String id,
            List<Pair<Source, Property>> pairs) {
        SimpleTreeProperty<T> node = new SimpleTreeProperty<>(id);
        for (Pair<Source, Property> pair : pairs) {
            Source source = pair.getKey();
            Property data = pair.getValue();

            node.merge(build(source, "data", data));
        }
        return node;
    }

    private static <T> SimpleTreeProperty<T> build(
            Source source,
            String id,
            Property property) {
        SimpleTreeProperty<T> node = new SimpleTreeProperty<>(id);

        if (property instanceof MapProperty map) {
            Consumer<Map.Entry<String, Property>> createAndMergeChildNode = entry -> {
                SimpleTreeProperty<?> childNode = build(
                        source,
                        entry.getKey(),
                        entry.getValue());
                node.mergeChild(childNode);
            };
            map.entrySet().forEach(createAndMergeChildNode);
        } else if (property instanceof ListProperty list) {
            AtomicInteger index = new AtomicInteger();
            Consumer<Property> createAndMergeChildNode = element -> {
                SimpleTreeProperty<?> childNode = build(
                        source,
                        "[%d]".formatted(index.getAndIncrement()),
                        element);
                node.mergeChild(childNode);
            };
            list.forEach(createAndMergeChildNode);
        } else if (property instanceof SetProperty set) {
            AtomicInteger index = new AtomicInteger();
            Consumer<Property> createAndMergeChildNode = element -> {
                SimpleTreeProperty<?> childNode = build(
                        source,
                        "[%d]".formatted(index.getAndIncrement()),
                        element);
                node.mergeChild(childNode);
            };
            set.forEach(createAndMergeChildNode);
        } else {
            node.propertyMap.put(source, property);
        }
        return node;
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public MapProperty<Source, Property<T>> propertyMapProperty() {
        return propertyMap;
    }

    public Optional<Source> getHighestPrioritySource() {
        return propertyMap.keySet().stream()
                .min(Comparator.comparingInt(Source::getPriority));
    }

    public ObjectProperty<SimpleTreeProperty<?>> parentProperty() {
        return parent;
    }

    public SimpleTreeProperty<?> getParent() {
        return parent.get();
    }

    public void setParent(SimpleTreeProperty<?> parent) {
        this.parent.set(parent);
    }

    public MapProperty<String, SimpleTreeProperty<?>> childrenMapProperty() {
        return childrenMap;
    }

    public ObservableMap<String, SimpleTreeProperty<?>> getChildrenMap() {
        return childrenMap.get();
    }

    public void setChildrenMap(ObservableMap<String, SimpleTreeProperty<?>> childrenMap) {
        this.childrenMap.set(childrenMap);
    }

    public void merge(SimpleTreeProperty<?> other) {
        if (!Objects.equals(this.getId(), other.getId())) {
            throw new AssertionError("id must be the same to merge: %s != %s".formatted(this.getId(), other.getId()));
        }
        mergeValues(other);
        mergeChildren(other);
    }

    private void mergeValues(SimpleTreeProperty<?> other) {
        for (Map.Entry<Source, ?> entry : other.propertyMap.entrySet()) {
            mergeValue(other, (Map.Entry<Source, Property<T>>) entry);
        }
    }

    private void mergeChildren(SimpleTreeProperty<?> other) {
        for (SimpleTreeProperty<?> otherChild : other.childrenMap.values()) {
            mergeChild(otherChild);
        }
    }

    private void mergeValue(SimpleTreeProperty<?> other, Map.Entry<Source, Property<T>> entry) {
        Source source = entry.getKey();
        if (propertyMap.containsKey(source) &&
                !Objects.equals(this.propertyMap.get(source), other.propertyMap.get(source))) {
            throw new AssertionError("cannot merge different values with matching sources: key=%s, this=%s, other%s"
                    .formatted(source, this.propertyMap.get(source), other.propertyMap.get(source)));
        } else {
            propertyMap.put(source, entry.getValue());
        }
    }

    public void mergeChild(SimpleTreeProperty<?> otherChild) {
        String id = otherChild.getId();
        if (this.childrenMap.containsKey(id)) {
            SimpleTreeProperty<?> thisChild = this.childrenMap.get(id);
            thisChild.merge(otherChild);
        } else {
            otherChild.parent.set(this);
            this.childrenMap.put(id, otherChild);
        }
    }

    public SimpleTreeProperty<?> getChild(String key) {
        return childrenMap.get(key);
    }

    public SimpleTreeProperty<T> find(String path) {
        return this.find(splitPath(path));
    }

    public SimpleTreeProperty<T> find(String... path) {
        return this.find(Arrays.asList(path));
    }

    public SimpleTreeProperty<T> find(List<String> path) {
        SimpleTreeProperty<?> child = getChild(path.get(0));
        if (child == null){
            return null;
        }
        List<String> subPath = path.subList(1, path.size());
        if (subPath.isEmpty()){
            return (SimpleTreeProperty<T>) child;
        } else {
            return (SimpleTreeProperty<T>) child.find(subPath);
        }
    }

    public SimpleTreeProperty<T> findOrCreate(String path) {
        return this.findOrCreate(splitPath(path));
    }

    public SimpleTreeProperty<T> findOrCreate(String... path) {
        return this.findOrCreate(Arrays.asList(path));
    }

    public SimpleTreeProperty<T> findOrCreate(List<String> path) {
        SimpleTreeProperty<?> child = getChild(path.get(0));
        if (child == null) {
            child = new SimpleTreeProperty<>(path.get(0));
        }
        List<String> subPath = path.subList(1, path.size());
        if (subPath.isEmpty()){
            return (SimpleTreeProperty<T>) child;
        } else {
            return (SimpleTreeProperty<T>) child.findOrCreate(subPath);
        }
    }

    public Optional<Property<T>> getProperty(Source source) {
        return Optional.ofNullable(this.propertyMap.get(source));
    }

    public Optional<T> get(Source source) {
        return getProperty(source).map(Property::getValue);
    }

    public Optional<T> getValue(Source source) {
        return get(source);
    }

    public void set(Source source, T value) {
        getProperty(source).ifPresentOrElse(
                property -> property.setValue(value),
                () -> this.propertyMap.put(source, new SimpleObjectProperty<>(value)));
    }

    public void setValue(Source source, T value) {
        this.set(source, value);
    }

    @Override
    public Object getBean() {
        return null;
    }

    @Override
    public String getName() {
        return id.getName();
    }
}
