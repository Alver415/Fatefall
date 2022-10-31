package utils;

import component.Component;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DragListener implements EventHandler<MouseEvent> {

    private enum Mode {
        TRANSLATE, SCALE, ROTATE;
    }

    private final Component component;
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

    public DragListener(Component component) {
        this.component = component;
    }

    @Override
    public void handle(MouseEvent e) {
        if (!e.getButton().equals(MouseButton.PRIMARY)){
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

        nodeX = component.getTranslateX();
        nodeY = component.getTranslateY();
        nodeTop = component.getTop();
        nodeBottom = component.getBottom();
        nodeLeft = component.getLeft();
        nodeRight = component.getRight();

        nodeRotate = component.getRotate();
    }

    private void update(MouseEvent e) {
        double deltaX = e.getSceneX() - startX;
        double deltaY = e.getSceneY() - startY;

        switch (mode) {
            case TRANSLATE -> {
                component.setTranslateX(nodeX + deltaX);
                component.setTranslateY(nodeY + deltaY);
            }
            case SCALE -> {
                component.setTop(nodeTop + deltaY);
                component.setBottom(nodeBottom + deltaY);
                component.setLeft(nodeLeft + deltaX);
                component.setRight(nodeRight + deltaX);
            }
            case ROTATE -> {
                component.setRotate(nodeRotate + deltaX);

            }
        }
    }


    private void end(MouseEvent e) {
        // Don't have to do anything here.
    }

}
