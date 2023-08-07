package com.alver.fatefall.plugin;

import com.alver.fatefall.app.fx.view.entity.card.CardView;
import com.alver.fatefall.app.plugin.FatefallPlugin;
import org.pf4j.PluginWrapper;

public class MagicTheGatheringPlugin extends FatefallPlugin {

    public MagicTheGatheringPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }
    public CardView<?> buildCardView() {
        return null;
    }
}
