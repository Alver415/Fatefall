package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.fatefall.fx.app.editor.components.image.ImageSelectionEditor;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
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
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.controlsfx.property.BeanProperty.CATEGORY_LABEL_KEY;

public class CachedItemsPropertySheet extends PropertySheet {

	private static final Logger log = LoggerFactory.getLogger(CachedItemsPropertySheet.class);

	private final LoadingCache<Node, ObservableList<Item>> cache;

	public CachedItemsPropertySheet() {
		this.setPropertyEditorFactory(new EditorFactory());
		cache = CacheBuilder.newBuilder().build(new CacheLoader<>() {
			@Override
			public ObservableList<Item> load(Node key) {
				return buildProperties(key);
			}
		});
	}

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
		} catch (
				IntrospectionException e) {
			log.error(e.getMessage(), e);
		}

		return list;
	}

	public void selectNode(Node key) {
		try {
			ObservableList<Item> list = cache.get(key);
			getItems().setAll(list);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
	public static class EditorFactory extends DefaultPropertyEditorFactory {

		@Override
		public PropertyEditor<?> call(Item item) {
			Class<?> type = item.getType();

			if (type == Image.class) {
				return new ImageSelectionEditor(item);
			}

			return super.call(item);
		}
	}
}
