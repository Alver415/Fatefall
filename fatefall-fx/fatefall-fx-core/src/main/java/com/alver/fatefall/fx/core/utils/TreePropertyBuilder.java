package com.alver.fatefall.fx.core.utils;

import com.alver.fatefall.fx.core.model.Source;
import javafx.util.Pair;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreePropertyBuilder {

    public static TreeProperty<Object> buildAndBind(Collection<Pair<Source, TreeProperty<Object>>> sources) {
        sources = sources.stream()
                .filter(p -> Objects.nonNull(p.getValue()))
                .toList();
        Collection<String> keys = sources.stream()
                .map(Pair::getValue)
                .map(DelegatingMap::keySet)
                .flatMap(Collection::stream)
                .toList();

        TreeProperty<Object> treeProperty = new TreeProperty<>();
        sources.forEach(pair -> treeProperty.putProperty(pair.getKey(), pair.getValue()));

        //Recurse
        for (String key : keys){
            Collection<Pair<Source, TreeProperty<Object>>> collect = sources.stream()
                    .filter(pair -> pair.getValue().getChildrenMap().containsKey(key))
                    .map(pair -> new Pair<>(pair.getKey(), pair.getValue().getChild(key)))
                    .collect(Collectors.toSet());
            TreeProperty<Object> merged = buildAndBind(collect);
            treeProperty.getChildrenMap().put(key, merged);
        }
        return treeProperty;
    }

}
