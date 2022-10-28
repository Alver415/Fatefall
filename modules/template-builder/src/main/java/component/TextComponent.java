package component;

import component.tool.ComponentToolPopOver;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utils.DragListener;

public class TextComponent extends TextArea implements Component {

    private static final Font DEFAULT_FONT = Font.font("Beleren Bold", 16);

    private final ComponentToolPopOver componentToolPopOver;

    public TextComponent() {
        super();
        getStyleClass().add("component");
        getStylesheets().add("app/component.css");
        setViewOrder(-1); //Bump back so it is drawn on top.

        setWrapText(true);
        setFont(DEFAULT_FONT);
        promptTextProperty().bindBidirectional(idProperty());
        prefWidthProperty().bind(widthProperty());
        prefHeightProperty().bind(heightProperty());

        addEventFilter(MouseEvent.ANY, new DragListener(this));
        componentToolPopOver = new ComponentToolPopOver(this);
    }

    public final void setFontName(String name) {
        setFont(Font.font(name, getFontSize()));
    }

    public final String getFontName() {
        return getFont().getName();
    }

    public final void setFontSize(double size) {
        setFont(Font.font(getFontName(), size));
    }

    public final double getFontSize() {
        return getFont().getSize();
    }

    public final void setFontColor(Color color) {
        setStyle(" -fx-text-fill: #" + color.toString().substring(2));
    }

    public final Color getFontColor() {
        return Color.BLACK;
    }


    protected DoubleProperty top = new AnchorProperty(this, "top", AnchorProperty.UNDEFINED);

    public DoubleProperty topProperty() {
        return top;
    }

    protected DoubleProperty right = new AnchorProperty(this, "right", AnchorProperty.UNDEFINED);

    public DoubleProperty rightProperty() {
        return right;
    }

    protected DoubleProperty bottom = new AnchorProperty(this, "bottom", AnchorProperty.UNDEFINED);

    public DoubleProperty bottomProperty() {
        return bottom;
    }

    protected DoubleProperty left = new AnchorProperty(this, "left", AnchorProperty.UNDEFINED);

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


}
