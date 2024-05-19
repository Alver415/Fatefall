package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.app.editor.components.image.ImageSelectionEditor;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanProperty;
import org.controlsfx.property.editor.DefaultPropertyEditorFactory;
import org.controlsfx.property.editor.PropertyEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.alver.jfxtra.util.JFXUtils.run;
import static com.alver.jfxtra.util.JFXUtils.runFX;
import static org.controlsfx.property.BeanProperty.CATEGORY_LABEL_KEY;

public class CachedItemsPropertySheet extends PropertySheet {

    private static final Logger log = LoggerFactory.getLogger(CachedItemsPropertySheet.class);

    // region Properties
    private final ObjectProperty<Node> selected = new SimpleObjectProperty<>(this, "selected");

    public ObjectProperty<Node> selectedProperty() {
        return selected;
    }

    public Node getSelected() {
        return selectedProperty().get();
    }

    public void setSelected(Node node) {
        selectedProperty().set(node);
    }

    // endregion Properties

    private final LoadingCache<Node, ObservableList<Item>> cache;

    public CachedItemsPropertySheet() {
        setPropertyEditorFactory(new EditorFactory());
        cache = CacheBuilder.newBuilder().build(new SimpleCacheLoader<>(this::buildProperties));
        selectedProperty().subscribe(key -> run(() -> {
            ObservableList<Item> items = EMPTY_LIST;
            if (key != null) {
                try {
                    items = cache.get(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ObservableList<Item> finalItems = items;
            runFX(() -> getItems().setAll(finalItems));
        }));
    }

    private static final ObservableList<Item> EMPTY_LIST = FXCollections.observableList(List.of());

    private static final Map<Class<?>, String> categoryMap = Map.of(
            Node.class, "Node"
    );

    private ObservableList<Item> buildProperties(Object bean) {
        ObservableList<Item> list = FXCollections.observableArrayList();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            for (PropertyDescriptor p : beanInfo.getPropertyDescriptors()) {
                Method writeMethod = p.getWriteMethod();
                Method readMethod = p.getReadMethod();
                if (writeMethod != null && readMethod != null) {
                    p.setValue(CATEGORY_LABEL_KEY, writeMethod.getDeclaringClass().toString());
                } else {
                    p.setValue(CATEGORY_LABEL_KEY, "Not Editable");
                }
                list.add(new BeanProperty(bean, p));
            }
        } catch (IntrospectionException e) {
            log.error(e.getMessage(), e);
        }

        return list;
    }

    private static class EditorFactory extends DefaultPropertyEditorFactory {

        @Override
        public PropertyEditor<?> call(Item item) {
            Class<?> type = item.getType();

            if (type == Image.class) {
                return new ImageSelectionEditor(item);
            }

            return super.call(item);
        }
    }

    private static class SimpleCacheLoader<K, V> extends CacheLoader<K, V> {

        private final Function<K, V> function;

        private SimpleCacheLoader(Function<K, V> function) {
            this.function = function;
        }

        @Override
        public V load(K key) {
            return function.apply(key);
        }
    }
}
