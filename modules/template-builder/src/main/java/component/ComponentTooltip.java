package component;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class ComponentTooltip extends Tooltip {

    public ComponentTooltip(Node node){
        Tooltip tooltip = new Tooltip();
        tooltip.textProperty().bind(idProperty());
        tooltip.setShowDelay(Duration.ZERO);
        Tooltip.install(node, tooltip);
    }

}
