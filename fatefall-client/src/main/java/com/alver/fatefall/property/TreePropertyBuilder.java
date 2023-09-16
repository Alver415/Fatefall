package com.alver.fatefall.property;

import com.alver.fatefall.app.fx.model.Source;
import javafx.beans.property.Property;
import javafx.util.Pair;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreePropertyBuilder {
    public static TreeProperty<?> buildAndBind(Collection<Pair<Source, TreeProperty<?>>> pairs) {
        pairs = pairs.stream()
                .filter(p -> Objects.nonNull(p.getValue()))
                .toList();
        Collection<String> keys = pairs.stream()
                .map(Pair::getValue)
                .map(DelegatingMap::keySet)
                .flatMap(Collection::stream)
                .toList();

        TreeProperty<Object> treeProperty = new TreeProperty<>();
        pairs.forEach(pair -> {
            treeProperty.set(pair.getKey(), pair.getValue().getValue());
            treeProperty.getProperty(pair.getKey())
                    .ifPresent(prop -> prop.bindBidirectional((Property<Object>) pair.getValue()));
        });

        //Recurse
        for (String key : keys){
            Collection<Pair<Source, TreeProperty<?>>> collect = pairs.stream()
                    .filter(pair -> pair.getValue().getChildrenMap().containsKey(key))
                    .map(pair -> new Pair<Source, TreeProperty<?>>(pair.getKey(), pair.getValue().getChild(key)))
                    .collect(Collectors.toSet());
            TreeProperty<?> merged = buildAndBind(collect);
            treeProperty.getChildrenMap().put(key, merged);
        }
        return treeProperty;
    }
}
