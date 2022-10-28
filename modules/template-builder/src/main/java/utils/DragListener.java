package utils;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class DragListener implements EventHandler<MouseEvent> {
    private volatile double screenX = 0;
    private volatile double screenY = 0;
    private volatile double myX = 0;
    private volatile double myY = 0;

    private final Node node;
    public DragListener(Node node){
        this.node = node;
    }

    @Override
    public void handle(MouseEvent e) {
        if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            screenX = e.getScreenX();
            screenY = e.getScreenY();
            myX = node.getTranslateX();
            myY = node.getTranslateY();
        }
        if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            double deltaX = e.getScreenX() - screenX;
            double deltaY = e.getScreenY() - screenY;
            node.setTranslateX(myX + deltaX);
            node.setTranslateY(myY + deltaY);
        }
    }

}
