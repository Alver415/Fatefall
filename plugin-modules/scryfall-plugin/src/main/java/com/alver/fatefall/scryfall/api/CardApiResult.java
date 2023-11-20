package com.alver.fatefall.scryfall.api;

import com.alver.fatefall.fx.core.model.CardFX;

import java.util.List;

public record CardApiResult(String object, Integer total_cards, boolean has_more, List<CardFX> data) {
}
