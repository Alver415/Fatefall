package component;

import javafx.fxml.FXMLLoader;

import java.net.URL;
import java.util.Objects;

public interface FXMLLoadable {


    default void load(Class<?> level, URL fxml) {
        if (getClass().equals(level)){
            load(fxml);
        }
    }

    private void load(URL fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setController(this);
            loader.setRoot(this);
            loader.setClassLoader(this.getClass().getClassLoader());
            loader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static URL findFXML(Class<?> clazz){
        String clazzName = clazz.getSimpleName();
        String fxmlName = clazzName + ".fxml";
        URL fxml = clazz.getResource(fxmlName);
        return Objects.requireNonNull(fxml);
    }
}
