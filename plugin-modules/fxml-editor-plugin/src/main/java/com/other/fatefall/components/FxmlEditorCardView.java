package com.other.fatefall.components;

import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.app.fx.components.FXMLAutoLoad;
import com.alver.fatefall.app.plugin.implementations.cardview.CardFace;
import com.alver.fatefall.app.plugin.implementations.cardview.DefaultCardView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@FXMLAutoLoad(location = "com/alver/fatefall/app/plugin/implementations/cardview/DefaultCardView.fxml")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FxmlEditorCardView extends DefaultCardView implements EventHandler<MouseEvent> {

    public FxmlEditorCardView() {
        super();
        addEventFilter(MouseEvent.ANY, this);
    }

    @Override
    protected void setupCardFaces(Card card) {
        TextBlock frontImageBlock = new TextBlock("Front");
        TextBlock backImageBlock = new TextBlock("Back");

        CardFace frontFace = new CardFace(frontImageBlock);
        CardFace backFace = new CardFace(backImageBlock);

        cardPane.setFront(frontFace);
        cardPane.setBack(backFace);
    }

    private enum Mode {
        TRANSLATE, SCALE, ROTATE;
    }

    private volatile Block<?> block;

    private volatile Mode mode = Mode.TRANSLATE;

    private volatile double startX = 0;
    private volatile double startY = 0;

    private volatile double nodeX = 0;
    private volatile double nodeY = 0;

    private volatile double nodeTop = 0;
    private volatile double nodeBottom = 0;
    private volatile double nodeLeft = 0;
    private volatile double nodeRight = 0;

    private volatile double nodeRotate = 0;

    @Override
    public void handle(MouseEvent e) {
        Optional<Block<?>> block = findAncestorBlock(e);
        if (block.isPresent()) {
            this.block = block.get();
        } else {
            return;
        }
        if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED) && e.getButton().equals(MouseButton.SECONDARY)) {
            TextBlock newTextBlock = new TextBlock();
            newTextBlock.setText("New TextBlock");
            newTextBlock.setTranslateX(e.getX());
            newTextBlock.setTranslateY(e.getY());
            getCardPane().getFront().getChildren().add(newTextBlock);
        }
        if (!e.getButton().equals(MouseButton.PRIMARY)) {
            return;
        }
        if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            begin(e);
        } else if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            Mode currMode = getMode(e);
            if (currMode != mode) {
                mode = currMode;
                begin(e);
            } else {
                update(e);
            }
        } else if (e.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            end(e);
        }
    }

    private Optional<Block<?>> findAncestorBlock(MouseEvent e) {
        Node node = e.getPickResult().getIntersectedNode();
        while (node != null && node != this) {
            if (node instanceof Block<?> found) {
                while (node != null) {
                    node = node.getParent();
                    if (node == this) {
                        return Optional.of(found);
                    }
                }
                return Optional.empty();
            }
            node = node.getParent();
        }
        return Optional.empty();
    }

    private static Mode getMode(MouseEvent e) {
        if (e.isControlDown()) {
            return Mode.SCALE;
        } else if (e.isAltDown()) {
            return Mode.ROTATE;
        } else {
            return Mode.TRANSLATE;
        }
    }

    private void begin(MouseEvent e) {
        startX = e.getSceneX();
        startY = e.getSceneY();

        nodeX = block.getTranslateX();
        nodeY = block.getTranslateY();
        nodeTop = block.getTop();
        nodeBottom = block.getBottom();
        nodeLeft = block.getLeft();
        nodeRight = block.getRight();

        nodeRotate = block.getRotate();
    }

    private void update(MouseEvent e) {
        double deltaX = e.getSceneX() - startX;
        double deltaY = e.getSceneY() - startY;

        switch (mode) {
            case TRANSLATE -> {
                block.setTranslateX(nodeX + deltaX);
                block.setTranslateY(nodeY + deltaY);
            }
            case SCALE -> {
                block.setTop(nodeTop + deltaY);
                block.setBottom(nodeBottom + deltaY);
                block.setLeft(nodeLeft + deltaX);
                block.setRight(nodeRight + deltaX);
            }
            case ROTATE -> {
                block.setRotate(nodeRotate + deltaX);
            }
        }
    }

    private void end(MouseEvent e) {
        // Don't have to do anything here.
    }

}