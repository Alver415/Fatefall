package components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageBlendComponent extends BaseComponent{


    public ImageBlendComponent(){
        super();
        leftImage.addListener((observable, oldValue, newValue) -> {
            setImageView(newValue, rightImage.get());
        });
        rightImage.addListener((observable, oldValue, newValue) -> {
            setImageView(leftImage.get(), newValue);
        });
    }

    public void setImageView(String left, String right){
        if (left == null || right == null){
            return;
        }
        try{
            ImageView imageView = linearGradient(new Image(left), new Image(right));
            getChildren().clear();
            getChildren().add(imageView);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ImageView linearGradient(Image first, Image second) {
        if (first.getWidth() != second.getWidth() || first.getHeight() != second.getHeight()) {
            throw new RuntimeException("Invalid dimensions");
        }

        WritableImage result = new WritableImage((int) first.getWidth(), (int) first.getHeight());
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                double ratio = x / result.getWidth();
                Color a = first.getPixelReader().getColor(x, y);
                Color b = second.getPixelReader().getColor(x, y);
                Color c = new Color(
                        weighted(a.getRed(), b.getRed(), ratio),
                        weighted(a.getGreen(), b.getGreen(), ratio),
                        weighted(a.getBlue(), b.getBlue(), ratio),
                        weighted(a.getOpacity(), b.getOpacity(), ratio)
                );
                result.getPixelWriter().setColor(x, y, c);
            }
        }
        return new ImageView(result);
    }

    private static double weighted(double a, double b, double factor) {
        return (factor * a) + ((1 - factor) * b);
    }

    public StringProperty leftImage = new SimpleStringProperty(this, "leftImage", null);
    public StringProperty leftImageProperty(){
        return leftImage;
    }
    public String getLeftImage(){
        return leftImage.get();
    }
    public void setLeftImage(String leftImage){
        this.leftImage.set(leftImage);
    }

    public StringProperty rightImage = new SimpleStringProperty(this, "rightImage", null);
    public StringProperty rightImageProperty(){
        return rightImage;

    }
    public String getRightImage(){
        return rightImage.get();
    }
    public void setRightImage(String rightImage){
        this.rightImage.set(rightImage);
    }
}
