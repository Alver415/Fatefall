package com.alver.fatefall.app.fx.model;

import javafx.scene.paint.Color;

public interface Source {

    Source DEFAULT = new Impl("DEFAULT", "Default", Color.GRAY, 0);

    Source FACE = new Impl("FACE", "Face", Color.BLUE, 1);
    Source CARD = new Impl("CARD", "Card", Color.RED, 2);
    Source TEMPLATE = new Impl("TEMPLATE", "Template", Color.GREEN, 3);

    String getId();

    String getName();

    Color getColor();

    int getPriority();

    record Impl(String id, String name, Color color, int priority) implements Source {

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Color getColor() {
            return color;
        }

        @Override
        public int getPriority() {
            return priority;
        }
    }

}