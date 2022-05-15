package com.alver.fatefall.api.server.local;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.CardCollectionApi;
import com.alver.fatefall.api.CardImageApi;
import com.alver.fatefall.api.FatefallApi;
import org.springframework.beans.factory.annotation.Autowired;

public class LocalFatefallApi implements FatefallApi {

    @Autowired
    private CardApi cardApi;
    @Autowired
    private CardImageApi cardImageApi;
    @Autowired
    private CardCollectionApi cardCollectionApi;

    @Override
    public CardApi getCardApi() {
        return cardApi;
    }

    @Override
    public CardImageApi getCardImageApi() {
        return cardImageApi;
    }

    @Override
    public CardCollectionApi getCardCollectionApi() {
        return cardCollectionApi;
    }
}
