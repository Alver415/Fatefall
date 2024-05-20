package com.alver.fatefall.template.mtg;

public enum Color {
    WHITE(ManaPip.W),
    BLUE(ManaPip.U),
    BLACK(ManaPip.B),
    RED(ManaPip.R),
    GREEN(ManaPip.G);

    private final ManaPip manaPip;

    Color(ManaPip manaPip) {
        this.manaPip = manaPip;
    }

    public ManaPip getManaPip() {
        return manaPip;
    }
}
