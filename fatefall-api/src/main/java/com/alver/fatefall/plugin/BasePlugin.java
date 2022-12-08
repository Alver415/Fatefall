package com.alver.fatefall.plugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class BasePlugin implements Plugin {
    private static final Logger log = LogManager.getLogger(BasePlugin.class);

    protected final String name;
    protected final String version;

    public BasePlugin() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("plugin.properties"));
        this.name = properties.getProperty("name");
        this.version = properties.getProperty("version");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void start() {
        log.info("Starting: %s - %s".formatted(name, version));
    }

    @Override
    public void stop() {
        log.info("Stopping: %s - %s".formatted(name, version));
    }

    @Override
    public List<Action> getActions() {
        return List.of();
    }

}
