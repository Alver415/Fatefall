package com.alver.fatefall.plugin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.jar.JarFile;
import java.util.stream.Stream;

public class PluginManager {

    protected final ObservableList<Plugin> plugins = FXCollections.observableArrayList();
    protected final ObservableMap<Plugin, Map<String, Action>> actionsMap = FXCollections.observableHashMap();

    public PluginManager() {
        reload();
    }

    public ObservableList<Plugin> getPlugins() {
        return plugins;
    }


    public void reload() {
        plugins.setAll(loadPlugins());
    }

    private List<Plugin> loadPlugins() {
        try (Stream<Path> paths = Files.walk(Path.of("plugins"))) {
            return paths
                    .filter(p -> p.getFileName().toString().endsWith(".jar"))
                    .map(this::pathToURL)
                    .map(PluginClassLoader::new)
                    .map(url -> ServiceLoader.load(Plugin.class, url))
                    .flatMap(ServiceLoader::stream)
                    .map(ServiceLoader.Provider::get)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private List<Plugin> findPlugins() {

        try (Stream<Path> paths = Files.walk(Path.of("plugins"))) {

            List<String> pluginNames = paths.filter(p -> p.getFileName().toString().endsWith(".jar"))
                    .map(this::pathToURL)
                    .map(PluginClassLoader::new)
                    .map(classLoader -> classLoader.getResourceAsStream("plugin.properties"))
                    .map(stream -> {
                        Properties properties = new Properties();
                        try {
                            properties.load(stream);
                            return properties.getProperty("name");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
            System.out.println(pluginNames);
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static JarFile pathToJarFile(Path path) {
        try {
            return new JarFile(path.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL pathToURL(Path path) {
        try {
            return path.toFile().toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
