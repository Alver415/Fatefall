package com.alver.fatefall.mtg.plugin.template;

import java.util.Set;

import static com.alver.fatefall.mtg.plugin.template.Color.*;

public enum ColorIdentity {
	// region Zero
	COLORLESS(),
	// endregion

	// region One
	MONO_WHITE(WHITE),
	MONO_BLUE(BLUE),
	MONO_BLACK(BLACK),
	MONO_RED(RED),
	MONO_GREEN(GREEN),
	// endregion

	// region Two
	AZORIUS(WHITE, BLUE),
	DIMIR(BLUE, BLACK),
	RAKDOS(BLACK, RED),
	GRUUL(RED, GREEN),
	SELESNYA(WHITE, GREEN),
	ORZHOV(WHITE, BLACK),
	IZZET(BLUE, RED),
	GOLGARI(BLACK, GREEN),
	BOROS(RED, WHITE),
	SIMIC(BLUE, GREEN),
	// endregion

	// region Three

	// region Shards
	BANT(),
	ESPER(),
	GRIXIS(),
	JUND(),
	NAYA(),
	// endregion

	// region Wedges
	ABZAN(),
	JESKAI(),
	SULTAI(),
	MARDU(),
	TEMUR(),
	// endregion

	// endregion

	// region Four
	CHAOS(BLUE, BLACK, RED, GREEN),
	AGGRESSION(WHITE, BLACK, RED, GREEN),
	ALTRUISM(WHITE, BLUE, RED, GREEN),
	GROWTH(WHITE, BLUE, BLACK, GREEN),
	ARTIFICE(WHITE, BLUE, BLACK, RED),
	// endregion

	// region Five
	WUBRG(WHITE, BLUE, BLACK, RED, GREEN);
	// endregion

	private final Set<Color> colors;

	ColorIdentity(Color... colors) {
		this.colors = Set.of(colors);
	}
	public Set<Color> getColors(){
		return colors;
	}

	public static ColorIdentity of(Color... colors){
		return of(Set.of(colors));
	}
	public static ColorIdentity of(Set<Color> colors){
		for (ColorIdentity identity : ColorIdentity.values()){
			if (identity.colors.equals(colors)){
				return identity;
			}
		}
		return null;
	}
}
