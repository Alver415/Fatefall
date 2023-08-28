package com.alver.fatefall.utils;

import javafx.beans.WeakListener;
import javafx.collections.ListChangeListener;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.function.Function;

public class ListBinding<E, F> implements ListChangeListener<E>, WeakListener {
    private final WeakReference<List<F>> targetRef;
    private final Function<? super E, ? extends F> mappingFunction;

    public ListBinding(List<F> target, Function<? super E, ? extends F> mappingFunction) {
        this.targetRef = new WeakReference<>(target);
        this.mappingFunction = mappingFunction;
    }

    @Override
    public void onChanged(Change<? extends E> change) {
        final List<F> target = targetRef.get();
        if (target == null) {
            change.getList().removeListener(this);
        } else {
            while (change.next()) {
                int startIndex = change.getFrom();
                int endIndex = change.getTo();

                if (change.wasPermutated()) {
                    target.subList(startIndex, endIndex).clear();
                    target.addAll(startIndex, change.getList().subList(startIndex, endIndex)
                            .stream().map(mappingFunction).toList());
                } else {
                    if (change.wasRemoved()) {
                        target.subList(startIndex, startIndex + change.getRemovedSize()).clear();
                    }
                    if (change.wasAdded()) {
                        target.addAll(startIndex, change.getAddedSubList()
                                .stream().map(mappingFunction).toList());
                    }
                }
            }
        }
    }

    @Override
    public boolean wasGarbageCollected() {
        return targetRef.get() == null;
    }

    @Override
    public int hashCode() {
        final List<F> list = targetRef.get();
        return (list == null) ? 0 : list.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        final List<F> thisTarget = targetRef.get();
        if (thisTarget == null) {
            return false;
        }

        if (obj instanceof ListBinding<?, ?> other) {
            final List<?> otherTarget = other.targetRef.get();
            return thisTarget == otherTarget;
        }
        return false;
    }
}