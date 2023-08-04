package com.alver.fatefall.converter;

import com.alver.fatefall.app.fx.entity.CardFX;
import com.alver.fatefall.data.entity.Card;
import com.alver.fatefall.data.entity.CardRow;
import com.alver.fatefall.service.EntityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("bundled")
public class CardConverter extends EntityConverter<Card<?>, CardFX, CardRow> {

    private final CardFaceConverter cardFaceConverter;

    public CardConverter(
            EntityService<Card<?>, CardRow> service,
            CardFaceConverter cardFaceConverter) {
        super(service);
        this.cardFaceConverter = cardFaceConverter;
    }

    @Override
    public CardFX convert(CardRow cardRow) {
        CardFX cardFx=new CardFX(cardRow.getId());
        cardFx.setData(cardRow.getData());
        cardFx.setName(cardRow.getName());
        cardFx.backProperty().set(cardFaceConverter.convert(cardRow.getBack()));
        cardFx.frontProperty().set(cardFaceConverter.convert(cardRow.getFront()));
        return cardFx;
    }
}
