package com.alver.fatefall.fx.core.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

public class PropertyIntrospector {

	private static final Logger log = LoggerFactory.getLogger(PropertyIntrospector.class);


	public PropertyIntrospector() {
		this(new EditorFactory());
	}

	public PropertyIntrospector(EditorFactory editorFactory) {
		setEditorFactory(editorFactory);
	}

	private final ObjectProperty<EditorFactory> editorFactory = new SimpleObjectProperty<>(this, "editorFactory");

	public ObjectProperty<EditorFactory> editorFactoryProperty() {
		return this.editorFactory;
	}

	public EditorFactory getEditorFactory() {
		return this.editorFactoryProperty().get();
	}

	public void setEditorFactory(EditorFactory value) {
		this.editorFactoryProperty().set(value);
	}

	public static List<PropertyInfo> getPropertyInfo(Class<?> target) {
		List<PropertyInfo> propertyInfoList = new ArrayList<>();

		Map<String, Method> setMethods = new HashMap<>();
		Map<String, Method> getMethods = new HashMap<>();
		Map<String, Method> propertyMethods = new HashMap<>();

		for (Method method : target.getDeclaredMethods()) {
			String methodName = method.getName();
			boolean endsWithProperty = methodName.endsWith("Property");
			boolean assignableFromProperty = Property.class.isAssignableFrom(method.getReturnType());
			if (endsWithProperty && assignableFromProperty) {
				String rootName = methodName
						.substring(0, methodName.length() - "Property".length())
						.toLowerCase();
				propertyMethods.put(rootName, method);
			}
			boolean startsWithSet = methodName.startsWith("set");
			boolean singleParameter = method.getParameterCount() == 1;
			if (startsWithSet && singleParameter) {
				String rootName = methodName.substring("set".length()).toLowerCase();
				setMethods.put(rootName, method);
			}
			boolean startsWithGet = methodName.startsWith("get");
			boolean hasReturnType = method.getReturnType() != Void.class;
			if (startsWithGet) {
				String rootName = methodName.substring("get".length()).toLowerCase();
				getMethods.put(rootName, method);
			}
		}

		for (String name : propertyMethods.keySet()) {
			Method property = propertyMethods.get(name);
			Method setter = setMethods.get(name);
			Method getter = getMethods.get(name);

			Optional<EditorInfo> propertyDetails = Optional.ofNullable(property.getAnnotation(EditorInfo.class));
			if (propertyDetails.map(EditorInfo::ignore).orElse(false)){
				continue;
			}
			String displayName = propertyDetails.map(EditorInfo::displayName).orElse(null);
			String category = propertyDetails.map(EditorInfo::category).orElse(null);



			if (setter != null && getter != null &&
					!getter.getReturnType().isAssignableFrom(setter.getParameterTypes()[0])) {
				throw new RuntimeException();
			}
			Class<?> returnType = setter != null ? setter.getParameterTypes()[0] :
					getter != null ? getter.getReturnType() : null;


			propertyInfoList.add(new PropertyInfo(name, displayName, category, property, setter, getter, returnType));
		}

		return propertyInfoList;
	}

	public <T> List<? extends Editor<?>> buildEditors(T target) {
		return Stream.ofNullable(target)
				.map(Object::getClass)
				.map(PropertyIntrospector::getPropertyInfo)
				.flatMap(Collection::stream)
				.map(propertyInfo -> {
					Method propertyMethod = propertyInfo.property();
					try {
						Property<?> property = (Property<?>) propertyMethod.invoke(target);
						return getEditorFactory().buildEditor(propertyInfo, property);
					} catch (Exception e) {
						log.error(e.getMessage(), e);
						return getEditorFactory().buildEditor(propertyInfo, null);
					}
				})
				.toList();
	}

	private static boolean isPropertyMethod(Method method) {
		boolean endsWithProperty = method.getName().endsWith("Property");
		boolean assignableFromProperty = Property.class.isAssignableFrom(method.getReturnType());
		return endsWithProperty && assignableFromProperty;
	}

	private static <T> Property<?> invoke(T target, Method method) {
		try {
			return (Property<?>) method.invoke(target);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}
