package com.alver.fatefall.app;

import org.pf4j.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class FatefallConfiguration {

    @Bean
    public PluginManager getPluginManager() {
        DefaultPluginManager pluginManager = new DefaultPluginManager(Path.of("plugins")) {
            @Override
            protected PluginLoader createPluginLoader() {
                return new CompoundPluginLoader()
                        .add(new DevelopmentPluginLoader(this) {
                            @Override
                            protected PluginClassLoader createPluginClassLoader(Path pluginPath, PluginDescriptor pluginDescriptor) {
                                return new PluginClassLoader(pluginManager, pluginDescriptor, getClass().getClassLoader(), ClassLoadingStrategy.ADP);
                            }
                        }, this::isDevelopment)
                        .add(new JarPluginLoader(this) {
                            @Override
                            public PluginClassLoader loadPlugin(Path pluginPath, PluginDescriptor pluginDescriptor) {
                                PluginClassLoader pluginClassLoader = new PluginClassLoader(pluginManager, pluginDescriptor, getClass().getClassLoader(), ClassLoadingStrategy.ADP);
                                pluginClassLoader.addFile(pluginPath.toFile());
                                return pluginClassLoader;
                            }
                        }, this::isNotDevelopment)
                        .add(new DefaultPluginLoader(this) {
                            @Override
                            protected PluginClassLoader createPluginClassLoader(Path pluginPath, PluginDescriptor pluginDescriptor) {
                                return new PluginClassLoader(pluginManager, pluginDescriptor, getClass().getClassLoader(), ClassLoadingStrategy.ADP);
                            }
                        }, this::isNotDevelopment);
            }
        };
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
        return pluginManager;
    }
}