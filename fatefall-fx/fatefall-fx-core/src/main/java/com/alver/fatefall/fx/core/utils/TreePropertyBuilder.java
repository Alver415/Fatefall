package com.alver.fatefall.fx.core.utils;

import com.alver.fatefall.fx.core.model.Source;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreePropertyBuilder {

	public static TreeProperty<Object> buildAndBind(Map<Source, TreeProperty<Object>> sources) {
		Map<Source, TreeProperty<Object>> filtered = sources.entrySet().stream()
				.filter(p -> Objects.nonNull(p.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		TreeProperty<Object> treeProperty = new TreeProperty<>();
		filtered.entrySet().stream()
				.filter(e ->  !(e.getValue().getValue() == null || e.getValue().getValue() instanceof TreeProperty))
				.forEach(e -> treeProperty.putProperty(e.getKey(), e.getValue()));

		filtered.values().stream()
				.map(Map::keySet)
				.flatMap(Collection::stream)
				.forEach(key -> treeProperty.getChildrenMap().put(key, buildAndBind(filtered.entrySet().stream()
						.filter(pair -> pair.getValue().getChildrenMap().containsKey(key))
						.collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().getChild(key))))));

		return treeProperty;
	}
}
