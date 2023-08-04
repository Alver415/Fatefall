package com.alver.fatefall.converter;

import com.alver.fatefall.app.fx.entity.CardFaceFX;
import com.alver.fatefall.data.entity.CardFace;
import com.alver.fatefall.data.entity.CardFaceRow;
import com.alver.fatefall.service.EntityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("bundled")
public class CardFaceConverter extends EntityConverter<CardFace, CardFaceFX, CardFaceRow> {

    public CardFaceConverter(EntityService<CardFace, CardFaceRow> service) {
        super(service);
    }

    @Override
    public CardFaceFX convert(CardFaceRow cardFaceRow) {
        CardFaceFX cardFaceFx = new CardFaceFX(cardFaceRow.getId());
        cardFaceFx.setData(cardFaceRow.getData());
        cardFaceFx.setName(cardFaceRow.getName());
        return cardFaceFx;
    }
}
