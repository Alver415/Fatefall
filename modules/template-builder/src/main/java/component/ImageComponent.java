package component;

import component.tool.ComponentToolPopOver;
import javafx.beans.DefaultProperty;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.Bindingz;
import utils.DragListener;

@DefaultProperty("image")
public class ImageComponent extends ImageView implements Component {

    private final ComponentToolPopOver componentToolPopOver;

    public ImageComponent() {
        super();
        getStyleClass().addAll("component", "image-component");
        Bindingz.bindBidirectional(translateZProperty(), viewOrderProperty(), -1d);

        parentProperty().addListener((observable, oldValue, newValue) -> {
            fitWidthProperty().unbind();
            fitHeightProperty().unbind();
            ReadOnlyObjectProperty<Bounds> bounds = newValue.layoutBoundsProperty();
            bounds.addListener((observable1, oldValue1, newValue1) -> {
                fitWidthProperty().bind(Bindings.subtract(newValue1.getWidth(), rightProperty()).subtract(leftProperty()));
                fitHeightProperty().bind(Bindings.subtract(newValue1.getHeight(), bottomProperty()).subtract(topProperty()));
            });
        });
        AnchorProperty.addAnchorChangeListener(this);

        addEventFilter(MouseEvent.ANY, new DragListener(this));
        componentToolPopOver = new ComponentToolPopOver(this);

    }

    protected DoubleProperty top = new AnchorProperty(this, "top", AnchorProperty.UNDEFINED);

    public DoubleProperty topProperty() {
        return top;
    }

    public Double getTop() {
        return top.get();
    }

    public void setTop(Double top) {
        this.top.set(top);
    }

    protected DoubleProperty right = new AnchorProperty(this, "right", AnchorProperty.UNDEFINED);

    public DoubleProperty rightProperty() {
        return right;
    }

    public Double getRight() {
        return right.get();
    }

    public void setRight(Double right) {
        this.right.set(right);
    }

    protected DoubleProperty bottom = new AnchorProperty(this, "bottom", AnchorProperty.UNDEFINED);

    public DoubleProperty bottomProperty() {
        return bottom;
    }

    public Double getBottom() {
        return bottom.get();
    }

    public void setBottom(Double bottom) {
        this.bottom.set(bottom);
    }

    protected DoubleProperty left = new AnchorProperty(this, "left", AnchorProperty.UNDEFINED);

    public DoubleProperty leftProperty() {
        return left;
    }

    public Double getLeft() {
        return left.get();
    }

    public void setLeft(Double left) {
        this.left.set(left);
    }

}
