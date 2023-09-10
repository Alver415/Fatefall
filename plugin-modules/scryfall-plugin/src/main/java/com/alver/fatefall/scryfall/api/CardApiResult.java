package com.alver.fatefall.scryfall.api;

import com.alver.fatefall.app.fx.model.entity.CardFX;

import java.util.List;

public record CardApiResult(String object, Integer total_cards, boolean has_more, List<CardFX> data) {
}
