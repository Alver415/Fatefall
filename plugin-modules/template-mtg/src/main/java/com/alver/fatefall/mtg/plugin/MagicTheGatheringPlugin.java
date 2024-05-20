package com.alver.fatefall.mtg.plugin;

import com.alver.fatefall.mtg.MagicTheGatheringConfiguration;
import com.alver.fatefall.fx.plugin.FatefallPlugin;
import org.pf4j.PluginWrapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MagicTheGatheringPlugin extends FatefallPlugin {

    public MagicTheGatheringPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }
    @Override
    protected AnnotationConfigApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = super.createApplicationContext();
        applicationContext.registerBean(MagicTheGatheringPlugin.class, () -> MagicTheGatheringPlugin.this);
        applicationContext.register(MagicTheGatheringConfiguration.class);

        return applicationContext;
    }

}
