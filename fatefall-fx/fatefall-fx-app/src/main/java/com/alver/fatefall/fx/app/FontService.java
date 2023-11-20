package com.alver.fatefall.fx.app;

import javafx.scene.text.Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

@Component
public class FontService {

    protected final List<Font> fonts = loadFonts(Path.of("fonts"));

    protected List<Font> loadFonts(Path basePath) {
        if (!basePath.toFile().exists()) {
            return List.of();
        }
        PathMatcher fontExtensionMatcher = FileSystems.getDefault().getPathMatcher("glob:**.ttf");
        try (Stream<Path> walk = Files.walk(basePath)) {
            return walk.filter(fontExtensionMatcher::matches)
                    .map(this::loadFont)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Font loadFont(Path path) {
        try {
            return Font.loadFont(path.toUri().toURL().toString(), 12);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion
}
