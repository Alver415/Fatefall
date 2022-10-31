package component;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.File;

public class FileField extends CustomTextField {
    private FileChooser.ExtensionFilter filter;

    public FileField(){
        Button button = new Button("Select");
        button.setOnAction(a -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setSelectedExtensionFilter(filter);
            File file = fileChooser.showOpenDialog(getScene().getWindow());
            setText(file.toString());
        });
        setRight(button);

        textProperty().bindBidirectional(fileProperty(), new StringConverter<>() {
            @Override
            public String toString(File file) {
                return file == null ? null : file.getPath();
            }

            @Override
            public File fromString(String string) {
                return string == null ? null : new File(string);
            }
        });
    }

    protected ListProperty<String> extensions = new SimpleListProperty<>(this, "extensions", FXCollections.observableArrayList());
    public ListProperty<String> extensionsProperty(){
        return extensions;
    }
    public ObservableList<String> getExtensions(){
        return extensions.get();
    }
    public void setExtensions(ObservableList<String> extensions){
        this.extensions.set(extensions);
    }

    protected ObjectProperty<File> file = new SimpleObjectProperty<>(this, "file", null);
    public ObjectProperty<File> fileProperty(){
        return file;
    }
    public File getFile(){
        return file.get();
    }
    public void setFile(File file){
        this.file.set(file);
    }
}
