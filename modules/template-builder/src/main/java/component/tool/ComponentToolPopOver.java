package component.tool;

import component.Component;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.PopOver;

public class ComponentToolPopOver extends PopOver {

    private final Component component;

    public ComponentToolPopOver(Component component) {
        this.component = component;
        Node node = (Node) component;
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                setContentNode(new ComponentToolPane(component));
                show(node);
            }
        });
        setHeaderAlwaysVisible(true);
        setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
        titleProperty().bindBidirectional(component.idProperty());
        setPrefSize(200, 600);
    }

}
