package com.other.fatefall.components;

import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

import static com.other.fatefall.components.Block.AnchorProperty.Anchor.*;

public class Block<T> extends AnchorPane {

    //region Properties

    protected ObjectProperty<T> value = new SimpleObjectProperty<>(this, "value", null);

    public ObjectProperty<T> valueProperty() {
        return value;
    }

    public T getValue() {
        return value.getValue();
    }

    public void setValue(T value) {
        this.value.set(value);
    }

    protected StringProperty displayName = new SimpleStringProperty(this, "displayName", null) {
        @Override
        public String get() {
            if (super.get() == null) {
                set(getId());
            }
            return super.get();
        }
    };

    public StringProperty displayNameProperty() {
        return displayName;
    }

    public String getDisplayName() {
        return displayName.get();
    }

    public void setDisplayName(String displayName) {
        this.displayName.set(displayName);
    }

    public Anchors getAnchors() {
        return new Anchors(
                getTop(),
                getRight(),
                getBottom(),
                getLeft());
    }

    public void setAnchors(Anchors anchors) {
        setTop(anchors.top);
        setRight(anchors.right);
        setBottom(anchors.bottom);
        setLeft(anchors.left);
    }

    protected DoubleProperty top = new AnchorProperty(this, TOP, null);
    protected DoubleProperty right = new AnchorProperty(this, RIGHT, null);
    protected DoubleProperty bottom = new AnchorProperty(this, BOTTOM, null);
    protected DoubleProperty left = new AnchorProperty(this, LEFT, null);

    public DoubleProperty topProperty() {
        return top;
    }

    public DoubleProperty rightProperty() {
        return right;
    }

    public DoubleProperty bottomProperty() {
        return bottom;
    }

    public DoubleProperty leftProperty() {
        return left;
    }

    public Double getTop() {
        return topProperty().get();
    }

    public void setTop(Double top) {
        topProperty().set(top);
    }

    public Double getRight() {
        return rightProperty().get();
    }

    public void setRight(Double right) {
        rightProperty().set(right);
    }

    public Double getBottom() {
        return bottomProperty().get();
    }

    public void setBottom(Double bottom) {
        bottomProperty().set(bottom);
    }

    public Double getLeft() {
        return leftProperty().get();
    }

    public void setLeft(Double left) {
        leftProperty().set(left);
    }

    public static class Anchors {
        protected Double top;
        protected Double right;
        protected Double bottom;
        protected Double left;

        public Anchors() {
            setAll(null);
        }
        public Anchors(Double all) {
            setAll(all);
        }

        public Anchors(Double vertical, Double horizontal) {
            setTop(vertical);
            setRight(horizontal);
            setBottom(vertical);
            setLeft(horizontal);
        }

        public Anchors(Double top, Double right, Double bottom, Double left) {
            setTop(top);
            setRight(right);
            setBottom(bottom);
            setLeft(left);
        }
        public Double getTop() {
            return top;
        }

        public void setTop(Double top) {
            this.top = top;
        }

        public Double getRight() {
            return right;
        }

        public void setRight(Double right) {
            this.right = right;
        }

        public Double getBottom() {
            return bottom;
        }

        public void setBottom(Double bottom) {
            this.bottom = bottom;
        }

        public Double getLeft() {
            return left;
        }

        public void setLeft(Double left) {
            this.left = left;
        }

        public void setAll(Double value) {
            setTop(value);
            setRight(value);
            setBottom(value);
            setLeft(value);
        }

        public static Anchors valueOf(String string) {
            String[] split = string.split("[ ,]+");
            int length = split.length;
            if (length == 0) {
                return new Anchors();
            } else if (length == 1) {
                return new Anchors(
                        Double.parseDouble(split[0]));
            } else if (length == 2) {
                return new Anchors(
                        Double.parseDouble(split[0]),
                        Double.parseDouble(split[1]));
            } else if (length == 4) {
                return new Anchors(
                        Double.parseDouble(split[0]),
                        Double.parseDouble(split[1]),
                        Double.parseDouble(split[2]),
                        Double.parseDouble(split[3]));
            } else {
                throw new IllegalArgumentException("Incorrect number of anchors");
            }
        }
    }


    public class AnchorProperty extends SimpleDoubleProperty {
        public enum Anchor {
            TOP, RIGHT, BOTTOM, LEFT;
        }

        protected final Anchor anchor;

        public AnchorProperty(Object bean, Anchor anchor, Double initialValue) {
            this(bean, anchor, initialValue == null ? Region.USE_COMPUTED_SIZE : initialValue);
        }

        public AnchorProperty(Object bean, Anchor anchor, double initialValue) {
            super(bean, anchor.name(), initialValue);
            this.anchor = anchor;
        }


        @Override
        public void set(double value) {
            super.set(value);
            Double anchorValue = value == Region.USE_COMPUTED_SIZE ? null : value;
            switch (anchor) {
                case TOP -> AnchorPane.setTopAnchor((Node) getBean(), anchorValue);
                case RIGHT -> AnchorPane.setRightAnchor((Node) getBean(), anchorValue);
                case BOTTOM -> AnchorPane.setBottomAnchor((Node) getBean(), anchorValue);
                case LEFT -> AnchorPane.setLeftAnchor((Node) getBean(), anchorValue);
            }
        }

        @Override
        public double get() {
            if (true) return super.get();
            return switch (anchor) {
                case TOP -> AnchorPane.getTopAnchor((Node) getBean());
                case RIGHT -> AnchorPane.getRightAnchor((Node) getBean());
                case BOTTOM -> AnchorPane.getBottomAnchor((Node) getBean());
                case LEFT -> AnchorPane.getLeftAnchor((Node) getBean());
            };
        }
    }


    //endregion

    //region Font Loading
    private static final List<Font> FONTS = loadFonts(Path.of("fonts"));

    private static List<Font> loadFonts(Path basePath) {
        if (!basePath.toFile().exists()) {
            return List.of();
        }
        PathMatcher fontExtensionMatcher = FileSystems.getDefault().getPathMatcher("glob:**.ttf");
        try (Stream<Path> walk = Files.walk(basePath)) {
            return walk.filter(fontExtensionMatcher::matches)
                    .map(Block::loadFont)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Font loadFont(Path path) {
        try {
            return Font.loadFont(path.toUri().toURL().toString(), 12);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion
}
