package com.alver.fatefall.templatebuilder.app;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageUtil {

    public static Image getTransparencyGrid(int width, int height) {
        Color light = Color.rgb(0, 0, 0, 0.1);
        Color lighter = Color.rgb(0, 0, 0, 0.2);
        return getTransparencyGrid(width, height, light, lighter);
    }
    public static Image getTransparencyGrid(int width, int height, Color first, Color second) {
        return getTransparencyGrid(width, height, colorToInt(first), colorToInt(second));
    }

    public static Image getTransparencyGrid(int width, int height, int argbFirst, int argbSecond) {
        WritableImage image = new WritableImage(width, height);
        int[] pixels = new int[width * height];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                boolean x = (((col * 2) / width) % 2) == 0;
                boolean y = (((row * 2) / height) % 2) == 0;

                int index = col + row * width;
                pixels[index] = x ^ y ? argbFirst : argbSecond;
            }
        }

        image.getPixelWriter().setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pixels, 0, width);
        return image;
    }

    public static int colorToInt(Color c) {
        int a = (int) Math.round(c.getOpacity() * 255);
        int r = (int) Math.round(c.getRed() * 255);
        int g = (int) Math.round(c.getGreen() * 255);
        int b = (int) Math.round(c.getBlue() * 255);
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static Color intToColor(int value) {
        int a = (value >>> 24) & 0xFF;
        int r = (value >>> 16) & 0xFF;
        int g = (value >>> 8) & 0xFF;
        int b = value & 0xFF;
        return Color.rgb(r, g, b, a / 255.0);
    }
}
