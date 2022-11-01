package com.alver.fatefall.templatebuilder.components.editor.image;

import com.alver.fatefall.templatebuilder.app.ImageUtil;
import com.alver.fatefall.templatebuilder.app.TemplateBuilder;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class StringImageConverter extends StringConverter<Image> {

    private static final Image IMAGE_NOT_FOUND = ImageUtil.getTransparencyGrid(32, 32,
            Color.ORANGE.deriveColor(0, 1, 1, 0.1),
            Color.RED.deriveColor(0, 1, 1, 0.1));

    @Override
    public String toString(Image image) {
        return image == null ? null : image.getUrl();
    }

    @Override
    public Image fromString(String string) {
        Image image;
        try {
            Path basePath = Path.of(TemplateBuilder.fxml.toURI()).getParent();
            Path resolved = basePath.resolve(Path.of(string));
            File file = resolved.toFile();
            if (file.exists() && file.isFile()) {
                image = new Image(file.toPath().toString());
                if (image.isError()){
                    image.getException().printStackTrace();
                } else {
                    return image;
                }
            }
        } catch (URISyntaxException | InvalidPathException ignored) {
            //Ignored
        }
        image = new Image(string);
        if (image.isError()){
            image.getException().printStackTrace();
            return null;
        } else {
            return image;
        }
    }
}
