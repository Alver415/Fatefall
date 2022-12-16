package com.other.fatefall.plugin;

import com.alver.fatefall.api.FatefallPlugin;
import com.other.fatefall.FxmlEditorConfiguration;
import org.pf4j.PluginWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class FxmlEditorPlugin extends FatefallPlugin {

    public FxmlEditorPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
        applicationContext.registerBean(FxmlEditorPlugin.class, () -> FxmlEditorPlugin.this);
        applicationContext.register(FxmlEditorConfiguration.class);
        applicationContext.refresh();

        return applicationContext;
    }

}
