package com.alver.fatefall.app.fx.view.entity.card.face;

import com.alver.fatefall.app.fx.model.Source;
import com.alver.fatefall.app.fx.model.property.DelegatingMap;
import com.alver.fatefall.app.fx.model.property.TreeProperty;
import javafx.beans.property.Property;
import javafx.util.Pair;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class TreePropertyBuilder {
    public static TreeProperty<?> buildAndBind(Collection<Pair<Source, TreeProperty<?>>> pairs) {
        Set<String> keys = pairs.stream()
                .map(Pair::getValue)
                .map(DelegatingMap::keySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        TreeProperty<Object> treeProperty = new TreeProperty<>();
        pairs.forEach(pair -> {
            treeProperty.set(pair.getKey(), pair.getValue().getValue());
            treeProperty.getProperty(pair.getKey()).ifPresent(prop -> prop.bindBidirectional((Property<Object>) pair.getValue()));
        });

        for (String key : keys){
            Collection<Pair<Source, TreeProperty<?>>> collect = pairs.stream()
                    .filter(pair -> pair.getValue().getChildrenMap().containsKey(key))
                    .map(pair -> {
                        Pair<Source, TreeProperty<?>> newPair = new Pair<>(pair.getKey(), pair.getValue().getChild(key));
                        return newPair;
                    })
                    .collect(Collectors.toSet());
            TreeProperty<?> merged = buildAndBind(collect);
            treeProperty.getChildrenMap().put(key, merged);
        }
        return treeProperty;
    }
}
