package com.alver.fatefall.plugin;

import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.alver.fatefall.data.entity.Workspace;
import javafx.scene.Node;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.pf4j.spring.SpringPluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class FatefallPlugin extends SpringPlugin {

	private static final Logger log = LoggerFactory.getLogger(FatefallPlugin.class);

	public FatefallPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	@Override
	public void start() {
		log.info("Starting Plugin: " + getWrapper().getPluginId());
        ((AnnotationConfigApplicationContext) getApplicationContext()).refresh();
        super.start();
	}

	@Override
	public void stop() {
		log.info("Stopping Plugin:  " + getWrapper().getPluginId());
		super.stop();
	}

	protected AnnotationConfigApplicationContext createApplicationContext() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		if (wrapper.getPluginManager() instanceof SpringPluginManager springPluginManager) {
			applicationContext.setParent(springPluginManager.getApplicationContext());
		}
		applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
		return applicationContext;
	}

	public void createToolTab(String name, Node content) {
		PluginManager pluginManager = getWrapper().getPluginManager();
		if (pluginManager instanceof FatefallPluginManager fatefallPluginManager) {
			fatefallPluginManager.createToolTab(name, content);
		}
	}

	public void createWorkspace(WorkspaceFX workspace) {
		PluginManager pluginManager = getWrapper().getPluginManager();
		if (pluginManager instanceof FatefallPluginManager fatefallPluginManager) {
			fatefallPluginManager.createWorkspace(workspace);
		}
	}

}
