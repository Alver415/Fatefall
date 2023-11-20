package com.alver.fatefall.fx.app.editor.components.directory;

import javafx.util.StringConverter;

import java.io.File;

public class StringDirectoryConverter extends StringConverter<File> {

    @Override
    public String toString(File file) {
        return file == null ? null : file.toString();
    }

    @Override
    public File fromString(String string) {
        return string == null ? null : new File(string);
    }
}
