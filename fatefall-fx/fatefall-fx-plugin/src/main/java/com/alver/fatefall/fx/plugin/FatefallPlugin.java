package com.alver.fatefall.fx.plugin;

import com.alver.fatefall.fx.core.interfaces.AppView;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.pf4j.spring.SpringPluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class FatefallPlugin extends SpringPlugin {
	private static final Logger log = LoggerFactory.getLogger(FatefallPlugin.class);

	public FatefallPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	@Override
	public void start() {
		try {
			((AnnotationConfigApplicationContext) getApplicationContext()).refresh();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void stop() {
		super.stop(); // to close applicationContext
	}

	protected AnnotationConfigApplicationContext createApplicationContext() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		if (wrapper.getPluginManager() instanceof SpringPluginManager springPluginManager) {
			applicationContext.setParent(springPluginManager.getApplicationContext());
		}
		applicationContext.setClassLoader(wrapper.getPluginClassLoader());
		return applicationContext;
	}

	public void addView(AppView appView) {
		PluginManager pluginManager = wrapper.getPluginManager();
		if (pluginManager instanceof FatefallPluginManager fatefallPluginManager) {
			fatefallPluginManager.addView(appView);
		} else {
			log.warn("pluginManager is not instance of FatefallPluginManager: {}", pluginManager.getClass());
		}
	}

	public void createWorkspace(WorkspaceFX workspace) {
		PluginManager pluginManager = getWrapper().getPluginManager();
		if (pluginManager instanceof FatefallPluginManager fatefallPluginManager) {
			fatefallPluginManager.createWorkspace(workspace);
		}
	}

}
