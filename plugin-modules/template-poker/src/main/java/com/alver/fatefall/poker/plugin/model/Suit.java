package com.alver.fatefall.poker.plugin.model;

import javafx.scene.paint.Color;

public enum Suit {
    SPADE(Color.BLACK, "♠"),
    HEART(Color.RED, "♥"),
    CLUB(Color.BLACK, "♣"),
    DIAMOND(Color.RED, "♦");

    private final Color color;
    private final String symbol;

    Suit(Color color, String symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public Color getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }
    public static Suit fromSymbol(String symbol) {
        return switch (symbol){
            case "♠": yield SPADE;
            case "♥": yield HEART;
            case "♣": yield CLUB;
            case "♦": yield DIAMOND;
            default: yield null;
        };
    }
}
