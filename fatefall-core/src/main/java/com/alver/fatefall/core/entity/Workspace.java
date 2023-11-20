package com.alver.fatefall.core.entity;

import java.util.List;

public interface Workspace extends Entity {

    List<? extends Card> getCards();

}
