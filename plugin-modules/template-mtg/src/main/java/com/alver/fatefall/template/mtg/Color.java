package com.alver.fatefall.template.mtg;

public enum Color {
	WHITE(ManaPip.W),
	BLUE(ManaPip.U),
	BLACK(ManaPip.B),
	RED(ManaPip.R),
	GREEN(ManaPip.G);

	final ManaPip pip;
	Color(ManaPip pip) {
		this.pip = pip;
	}
}
