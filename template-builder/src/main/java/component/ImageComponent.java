package component;

import javafx.beans.DefaultProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

@DefaultProperty("image")
public class ImageComponent extends Component {

    private static final URL FXML = FXMLLoadable.findFXML(ImageComponent.class);

    @FXML
    protected ImageView imageView;
    protected ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image", null);

    public ImageComponent() {
        load(ImageComponent.class, FXML);
    }

    @FXML
    protected void initialize() {
        super.initialize();
        imageProperty().bindBidirectional(imageView.imageProperty());

        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }

    public Image getImage() {
        return imageProperty().get();
    }

    public void setImage(Image image) {
        imageProperty().set(image);
    }

    public DoubleProperty fitWidthProperty() {
        return imageView.fitWidthProperty();
    }

    public double getFitWidth() {
        return fitWidthProperty().get();
    }

    public void setFitWidth(double value) {
        fitWidthProperty().set(value);
    }

    public DoubleProperty fitHeightProperty() {
        return imageView.fitWidthProperty();
    }

    public double getFitHeight() {
        return fitHeightProperty().get();
    }

    public void setFitHeight(double value) {
        fitHeightProperty().set(value);
    }


    public BooleanProperty preserveRatioProperty() {
        return imageView.preserveRatioProperty();
    }

    public boolean getPreserveRatio() {
        return preserveRatioProperty().get();
    }

    public void setPreserveRatio(boolean value) {
        preserveRatioProperty().set(value);
    }

}
