
package com.alver.fatefall.templatebuilder.components.editor.path;

import javafx.util.StringConverter;

import java.nio.file.Path;

public class StringPathConverter extends StringConverter<Path> {

    @Override
    public String toString(Path path) {
        return path == null ? null : path.toString();
    }

    @Override
    public Path fromString(String string) {
        return string == null ? null : Path.of(string);
    }
}
