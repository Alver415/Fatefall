package com.alver.fatefall.plugin;

import com.alver.fatefall.app.fx.components.mainstage.MainStage;
import com.alver.fatefall.plugin.implementations.DefaultPlugin;
import com.alver.fatefall.plugin.interfaces.CardCollectionView;
import com.alver.fatefall.plugin.interfaces.CardView;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PluginManager {

    public static void show() {
        reload();
        ChoiceDialog<Plugin> pluginChoiceDialog = new ChoiceDialog<>(active, plugins.values());
        Optional<Plugin> plugin = pluginChoiceDialog.showAndWait();
        plugin.ifPresent(PluginManager::setActive);
    }

    public static Map<String, Plugin> plugins;
    public static Plugin active;

    public static void setActive(Plugin plugin) {
        active = plugin;
    }

    static {
        reload();
    }

    public static void reload() {
        plugins = loadPlugins();
        active = plugins.get("Default");
    }

    private static Map<String, Plugin> loadPlugins() {
        try (Stream<Path> paths = Files.walk(Path.of("plugins"))) {
            Stream<Plugin> plugins = paths
                    .filter(p -> p.getFileName().toString().endsWith(".jar"))
                    .map(PluginManager::toURL)
                    .map(url -> new URLClassLoader(new URL[]{url}))
                    .map(url -> ServiceLoader.load(Plugin.class, url))
                    .flatMap(ServiceLoader::stream)
                    .map(ServiceLoader.Provider::get);
            plugins = Stream.concat(Stream.of(new DefaultPlugin()), plugins);
            return plugins.collect(Collectors.toMap(
                            Plugin::getName,
                            Function.identity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static CardView buildCardView() {
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

    public static CardCollectionView buildCardCollectionView() {
        return active.getComponentFactory().buildCardCollectionView();
    }

    private static URL toURL(Path path) {
        try {
            return path.toFile().toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * TEMPORARY: NEEDS TO BE REFACTORED
     */

    public static TabPane getTabPane() {
        return mainStage.getTabPane();
    }

    static MainStage mainStage;

    public static void setMainStage(MainStage mainStage) {
        PluginManager.mainStage = mainStage;
    }
}
