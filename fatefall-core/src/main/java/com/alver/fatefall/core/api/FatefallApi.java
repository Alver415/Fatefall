package com.alver.fatefall.core.api;

import com.alver.fatefall.core.entity.Card;

public interface FatefallApi {

	CardsApi getWorkspacesApi();
	CardsApi getCardsApi();
	CardsApi getCardFacesApi();
	CardsApi getTemplatesApi();
}
