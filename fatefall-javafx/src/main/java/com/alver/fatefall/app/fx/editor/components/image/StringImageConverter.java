package com.alver.fatefall.app.fx.editor.components.image;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.util.StringConverter;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class StringImageConverter extends StringConverter<Image> {

	@Override
	public String toString(Image image) {
		return image == null ? null : image.getUrl();
	}

	@Override
	public Image fromString(String string) {
		Image image;
		try {
			File file = getBasePath().resolve(Path.of(string)).toFile();
			if (file.exists() && file.isFile()) {
				image = new Image(file.toPath().toString(), true);
				image.exceptionProperty().addListener(((observable, oldValue, newValue) -> {
					newValue.printStackTrace();
				}));
				return image;
			}
		} catch (InvalidPathException ignored) {
			//Ignored
		}
		try {
			image = new Image(string, true);
			image.exceptionProperty().addListener(((observable, oldValue, newValue) -> {
				newValue.printStackTrace();
			}));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected ObjectProperty<Path> basePath = new SimpleObjectProperty<>(this, "basePath", Path.of(""));
	public ObjectProperty<Path> basePathProperty() {
		return basePath;
	}
	public Path getBasePath() {
		return basePath.get();
	}
	public void setBasePath(Path basePath) {
		this.basePath.set(basePath);
	}
}
