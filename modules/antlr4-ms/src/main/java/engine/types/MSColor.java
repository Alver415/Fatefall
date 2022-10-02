package engine.types;

public class MSColor extends MSValue {

    private static final int MAX = 255;
    private static final int MIN = 0;

    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    public MSColor(int red, int green, int blue) {
        this(red, green, blue, MAX);
    }

    public MSColor(int red, int green, int blue, int alpha) {
        this.red = clamp(red);
        this.green = clamp(green);
        this.blue = clamp(blue);
        this.alpha = clamp(alpha);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

    private static int clamp(int n){
        return Math.min(Math.max(n, MIN), MAX);
    }

}
