package com.alver.fatefall.property;

import com.alver.fatefall.app.fx.model.Source;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class TreeProperty<T> extends SimpleObjectProperty<T> implements DelegatingMap<String, TreeProperty<?>>{

    private final StringProperty id;
    private final ObjectProperty<TreeProperty<?>> parent;
    private final MapProperty<String, TreeProperty<?>> childrenMap;
    private final MapProperty<Source, Property<T>> propertyMap;

    public TreeProperty() {
        this("");
    }

    public TreeProperty(String id) {
        Objects.requireNonNull(id);
        this.id = new SimpleStringProperty(id);
        this.parent = new SimpleObjectProperty<>();
        this.childrenMap = new SimpleMapProperty<>(FXCollections.observableHashMap());
        this.propertyMap = new SimpleMapProperty<>(FXCollections.observableHashMap());

        this.propertyMap.addListener((MapChangeListener<? super Source, ? super Property<T>>) change ->
                change.getMap().entrySet().stream()
                        .peek(entry -> this.unbindBidirectional(entry.getValue()))
                        .min(Comparator.comparingInt(entry -> entry.getKey().ordinal()))
                        .ifPresent(entry -> this.bindBidirectional(entry.getValue())));
    }

    private static String[] splitPath(String path) {
        return path.split("\\.");
    }

    public static <T> TreeProperty<T> create(String id) {
        return create(id, List.of());
    }

    public static <T> TreeProperty<T> create(
            String id,
            List<Pair<Source, Property>> pairs) {
        TreeProperty<T> node = new TreeProperty<>(id);
        for (Pair<Source, Property> pair : pairs) {
            Source source = pair.getKey();
            Property data = pair.getValue();

            node.merge(create(source, "data", data));
        }
        return node;
    }

    private static <T> TreeProperty<T> create(
            Source source,
            String id,
            Property property) {
        TreeProperty<T> node = new TreeProperty<>(id);

        if (property instanceof MapProperty map) {
            Consumer<Map.Entry<String, Property>> createAndMergeChildNode = entry -> {
                TreeProperty<?> childNode = create(
                        source,
                        entry.getKey(),
                        entry.getValue());
                node.mergeChild(childNode);
            };
            map.entrySet().forEach(createAndMergeChildNode);
        } else if (property instanceof ListProperty list) {
            AtomicInteger index = new AtomicInteger();
            Consumer<Property> createAndMergeChildNode = element -> {
                TreeProperty<?> childNode = create(
                        source,
                        "[%d]".formatted(index.getAndIncrement()),
                        element);
                node.mergeChild(childNode);
            };
            list.forEach(createAndMergeChildNode);
        } else if (property instanceof SetProperty set) {
            AtomicInteger index = new AtomicInteger();
            Consumer<Property> createAndMergeChildNode = element -> {
                TreeProperty<?> childNode = create(
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
                .min(Comparator.comparingInt(Source::ordinal));
    }

    public ObjectProperty<TreeProperty<?>> parentProperty() {
        return parent;
    }

    public TreeProperty<?> getParent() {
        return parent.get();
    }

    public void setParent(TreeProperty<?> parent) {
        this.parent.set(parent);
    }

    public MapProperty<String, TreeProperty<?>> childrenMapProperty() {
        return childrenMap;
    }

    public ObservableMap<String, TreeProperty<?>> getChildrenMap() {
        return childrenMap.get();
    }

    public void setChildrenMap(ObservableMap<String, TreeProperty<?>> childrenMap) {
        this.childrenMap.set(childrenMap);
    }

    public void merge(TreeProperty<?> other) {
        if (!Objects.equals(this.getId(), other.getId())) {
            throw new AssertionError("id must be the same to merge: %s != %s".formatted(this.getId(), other.getId()));
        }
        mergeValues(other);
        mergeChildren(other);
    }

    private void mergeValues(TreeProperty<?> other) {
        for (Map.Entry<Source, ?> entry : other.propertyMap.entrySet()) {
            mergeValue(other, (Map.Entry<Source, Property<T>>) entry);
        }
    }

    private void mergeChildren(TreeProperty<?> other) {
        for (TreeProperty<?> otherChild : other.childrenMap.values()) {
            mergeChild(otherChild);
        }
    }

    private void mergeValue(TreeProperty<?> other, Map.Entry<Source, Property<T>> entry) {
        Source source = entry.getKey();
        if (propertyMap.containsKey(source) &&
                !Objects.equals(this.propertyMap.get(source), other.propertyMap.get(source))) {
            throw new AssertionError("cannot merge different values with matching sources: key=%s, this=%s, other%s"
                    .formatted(source, this.propertyMap.get(source), other.propertyMap.get(source)));
        } else {
            propertyMap.put(source, entry.getValue());
        }
    }

    public void mergeChild(TreeProperty<?> otherChild) {
        String id = otherChild.getId();
        if (this.childrenMap.containsKey(id)) {
            TreeProperty<?> thisChild = this.childrenMap.get(id);
            thisChild.merge(otherChild);
        } else {
            otherChild.parent.set(this);
            this.childrenMap.put(id, otherChild);
        }
    }

    public TreeProperty<?> getChild(String key) {
        return childrenMap.get(key);
    }

    public TreeProperty<?> find(String path) {
        return this.find(splitPath(path));
    }

    public TreeProperty<?> find(String... path) {
        return this.find(Arrays.asList(path));
    }

    public TreeProperty<?> find(List<String> path) {
        TreeProperty<?> child = getChild(path.get(0));
        List<String> subPath = path.subList(1, path.size());
        return subPath.isEmpty() ?
                child :
                (child == null ?
                        null :
                        child.find(subPath));
    }

    public TreeProperty<?> findOrCreate(String path) {
        return this.findOrCreate(splitPath(path));
    }

    public TreeProperty<?> findOrCreate(String... path) {
        return this.findOrCreate(Arrays.asList(path));
    }

    public TreeProperty<?> findOrCreate(List<String> path) {
        TreeProperty<?> child = getChild(path.get(0));
        if (child == null) {
            child = new TreeProperty<>(path.get(0));
        }
        List<String> subPath = path.subList(1, path.size());
        return subPath.isEmpty() ?
                child :
                child.findOrCreate(subPath);
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

    @Override
    public Map<String, TreeProperty<?>> getDelegateMap() {
        return childrenMap;
    }
}
