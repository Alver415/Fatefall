package com.alver.fatefall.converter;

import com.alver.fatefall.app.fx.entity.WorkspaceFX;
import com.alver.fatefall.data.entity.Workspace;
import com.alver.fatefall.data.entity.WorkspaceRow;
import com.alver.fatefall.service.EntityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("bundled")
public class WorkspaceConverter extends EntityConverter<Workspace<?>, WorkspaceFX, WorkspaceRow> {

    private final CardConverter cardConverter;

    public WorkspaceConverter(
            EntityService<Workspace<?>, WorkspaceRow> service,
            CardConverter cardConverter) {
        super(service);
        this.cardConverter = cardConverter;
    }

    @Override
    public WorkspaceFX convert(WorkspaceRow workspaceRow) {
        WorkspaceFX workspaceFX = new WorkspaceFX(workspaceRow.getId());
        workspaceFX.setData(workspaceRow.getData());
        workspaceFX.setName(workspaceRow.getName());
        workspaceFX.addCards(workspaceRow.getCards().stream().map(cardConverter::convert).toList());
        return workspaceFX;
    }
}
