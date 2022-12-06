package com.alver.fatefall.app.plugin;

import com.alver.fatefall.app.fx.components.FXMLLoadable;
import com.alver.fatefall.app.plugin.implementations.DefaultPlugin;
import com.alver.fatefall.app.plugin.interfaces.CardCollectionView;
import com.alver.fatefall.app.plugin.interfaces.CardView;
import jakarta.annotation.PostConstruct;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PluginManager extends BorderPane implements FXMLLoadable {

    @Autowired
    protected DefaultPlugin defaultPlugin;

    public PluginManager(){
        loadFxml();
    }
    @PostConstruct
    public void postConstruct(){
        active = defaultPlugin;
    }

    public Map<String, Plugin> plugins;
    public Plugin active;

    public void setActive(Plugin plugin) {
        active = plugin;
    }

    public void reload() {
        plugins = loadPlugins();
        active = plugins.get("Default");
    }

    private Map<String, Plugin> loadPlugins() {
        try (Stream<Path> paths = Files.walk(Path.of("plugins"))) {
            Stream<Plugin> plugins = paths
                    .filter(p -> p.getFileName().toString().endsWith(".jar"))
                    .map(this::toURL)
                    .map(PluginClassLoader::new)
                    .map(url -> ServiceLoader.load(Plugin.class, url))
                    .flatMap(ServiceLoader::stream)
                    .map(ServiceLoader.Provider::get);
            plugins = Stream.concat(Stream.of(defaultPlugin), plugins);
            return plugins.collect(Collectors.toMap(
                    Plugin::getName,
                    Function.identity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CardView buildCardView() {
        CardView cardView = active.getComponentFactory().buildCardView();

        Node cardViewNode = cardView.getFxViewNode();
        cardViewNode.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                ContextMenu contextMenu = new ContextMenu();
                for (Plugin plugin : plugins.values()) {
                    if (!contextMenu.getItems().isEmpty()) {
                        contextMenu.getItems().add(new SeparatorMenuItem());
                    }
                    Collection<MenuItem> menuItems = plugin.getContextMenuFactory().buildCardViewMenuItems(cardView.getCard());
                    contextMenu.getItems().addAll(menuItems);
                }
                contextMenu.show(cardViewNode, event.getScreenX(), event.getScreenY());
            }
        });

        return cardView;
    }

    public CardCollectionView buildCardCollectionView() {
        return active.getComponentFactory().buildCardCollectionView();
    }

    private URL toURL(Path path) {
        try {
            return path.toFile().toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
