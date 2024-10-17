package com.alver.fatefall.poker.plugin.template;

public enum Rank {
	ACE(1, "A"),
	TWO(2, "2"),
	THREE(3, "3"),
	FOUR(4, "4"),
	FIVE(5, "5"),
	SIX(6, "6"),
	SEVEN(7, "7"),
	EIGHT(8, "8"),
	NINE(9, "9"),
	TEN(10, "10"),
	JACK(11, "J"),
	QUEEN(12, "Q"),
	KING(13, "K");

	private final int value;
	private final String symbol;

	Rank(int value, String symbol) {
		this.value = value;
		this.symbol = symbol;
	}

	public int getValue() {
		return value;
	}

	public String getSymbol() {
		return symbol;
	}
	public static Rank fromSymbol(String symbol) {
		return switch (symbol){
			case "A": yield ACE;
			case "2": yield TWO;
			case "3": yield THREE;
			case "4": yield FOUR;
			case "5": yield FIVE;
			case "6": yield SIX;
			case "7": yield SEVEN;
			case "8": yield EIGHT;
			case "9": yield NINE;
			case "10": yield TEN;
			case "J": yield JACK;
			case "Q": yield QUEEN;
			case "K": yield KING;
			default: yield null;
		};
	}
}
