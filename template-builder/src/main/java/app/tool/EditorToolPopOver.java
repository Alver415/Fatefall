package app.tool;

import javafx.scene.Node;
import org.controlsfx.control.PopOver;

public class EditorToolPopOver extends PopOver {

    private static final EditorToolPopOver instance = new EditorToolPopOver();
    private EditorToolPopOver() {
        setHeaderAlwaysVisible(true);
        setAnimated(false);
    }

    public static void request(EditorToolPane editor, double x, double y){
        Node target = editor.getTarget();
        instance.titleProperty().bind(target.idProperty());
        instance.setContentNode(editor);
        instance.show(target.getScene().getWindow(), x, y);
    }


}
