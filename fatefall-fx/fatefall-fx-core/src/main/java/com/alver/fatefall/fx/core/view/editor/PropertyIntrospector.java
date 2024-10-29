package com.alver.fatefall.fx.core.view.editor;

import javafx.beans.property.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

public class PropertyIntrospector {

	private static final Logger log = LoggerFactory.getLogger(PropertyIntrospector.class);

	public List<PropertyInfo> getPropertyInfo(Class<?> target) {
		List<PropertyInfo> propertyInfoList = new ArrayList<>();

		Map<String, Method> setMethods = new HashMap<>();
		Map<String, Method> getMethods = new HashMap<>();
		Map<String, Method> propertyMethods = new HashMap<>();

		for (Method method : target.getMethods()) {
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
			if (startsWithGet && hasReturnType) {
				String rootName = methodName.substring("get".length()).toLowerCase();
				getMethods.put(rootName, method);
			}
		}

		for (String name : propertyMethods.keySet()) {
			Method property = propertyMethods.get(name);
			Method setter = setMethods.get(name);
			Method getter = getMethods.get(name);

			Optional<EditorInfo> propertyDetails = Optional.ofNullable(property.getAnnotation(EditorInfo.class));
			if (propertyDetails.map(EditorInfo::ignore).orElse(false)) continue;

			String displayName = propertyDetails.map(EditorInfo::displayName).orElse(null);
			String category = propertyDetails.map(EditorInfo::category).orElse(null);
			int order = propertyDetails.map(EditorInfo::order).orElse(EditorInfo.DEFAULT_ORDER);

			if (setter != null && getter != null &&
					!getter.getReturnType().isAssignableFrom(setter.getParameterTypes()[0])) {
				throw new RuntimeException();
			}
			Class<?> returnType = setter != null ? setter.getParameterTypes()[0] :
					getter != null ? getter.getReturnType() : null;


			propertyInfoList.add(new PropertyInfo(name, displayName, category, order, property, setter, getter, returnType));
		}

		return propertyInfoList;
	}

}
