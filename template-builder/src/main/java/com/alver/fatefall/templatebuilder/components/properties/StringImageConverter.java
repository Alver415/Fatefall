package com.alver.fatefall.templatebuilder.components.properties;

import com.alver.fatefall.templatebuilder.app.ImageUtil;
import com.alver.fatefall.templatebuilder.app.TemplateBuilder;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
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
        try {
            Path basePath = Path.of(TemplateBuilder.fxml.toURI()).getParent();
            Path resolved = basePath.resolve(Path.of(string));
            return new Image(new FileInputStream(resolved.toFile()));
        } catch (FileNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
//            e.printStackTrace();
//            return IMAGE_NOT_FOUND;
        }
    }
}
