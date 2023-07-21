package com.alver.fatefall.app.services;

import com.alver.fatefall.app.fx.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.data.entity.Card;
import javafx.scene.control.MenuItem;

import java.util.List;

public interface ComponentFactory {
    WorkspaceView<?> buildWorkspaceView();

    List<MenuItem> buildCardViewContextMenuItems(Card card);

}