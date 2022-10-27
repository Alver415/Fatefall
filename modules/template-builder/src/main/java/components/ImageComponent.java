package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageComponent extends BaseComponent {

    protected final ImageView imageView = new ImageView();

    public ImageComponent() {
        super();
        getChildren().add(imageView);
        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());

        value.addListener((observable, oldValue, newValue) -> {
            try {
                imageView.setImage(new Image(newValue));
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
