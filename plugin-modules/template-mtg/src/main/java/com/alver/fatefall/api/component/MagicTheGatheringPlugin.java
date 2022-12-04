package com.alver.fatefall.plugin.interfaces;

public class MagicTheGatheringPlugin extends DefaultPlugin {

    public CardView buildCardView() {
        return new MtgCardView();
    }
}
