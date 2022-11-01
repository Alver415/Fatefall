package com.alver.fatefall.templatebuilder.components.utils;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LinearImageInterpolator extends ImageInterpolator {

    public enum Type {
        VERTICAL, HORIZONTAL;
    }

    protected Type type;

    public LinearImageInterpolator() {
        this(Type.VERTICAL);
    }

    public LinearImageInterpolator(Type type) {
        this.type = type;
    }

    @Override
    public Image interpolate(Image firstImage, Image secondImage) {
        if (firstImage == null || secondImage == null) {
            throw new NullPointerException();
        }
        if (firstImage.getWidth() != secondImage.getWidth() ||
                firstImage.getHeight() != secondImage.getHeight()) {
            throw new IllegalArgumentException("Dimensions do not match: (%f,%f), (%f,%f)".formatted(
                    firstImage.getWidth(), firstImage.getHeight(),
                    secondImage.getWidth(), secondImage.getHeight()));
        }

        WritableImage interpolated = new WritableImage((int) firstImage.getWidth(), (int) firstImage.getHeight());
        for (int y = 0; y < interpolated.getHeight(); y++) {
            for (int x = 0; x < interpolated.getWidth(); x++) {
                Color color = interpolatePixel(firstImage, secondImage, y, x);
                interpolated.getPixelWriter().setColor(x, y, color);
            }
        }
        return interpolated;
    }

    protected Color interpolatePixel(Image firstImage, Image secondImage, int y, int x) {
        double fraction = switch (type) {
            case VERTICAL -> x / firstImage.getWidth();
            case HORIZONTAL -> y / firstImage.getHeight();
        };

        Color first = firstImage.getPixelReader().getColor(x, y);
        Color second = secondImage.getPixelReader().getColor(x, y);

        return new Color(
                interpolateNumber(first.getRed(), second.getRed(), fraction),
                interpolateNumber(first.getGreen(), second.getGreen(), fraction),
                interpolateNumber(first.getBlue(), second.getBlue(), fraction),
                interpolateNumber(first.getOpacity(), second.getOpacity(), fraction)
        );
    }

    private double interpolateNumber(double first, double second, double fraction) {
        return (fraction * first) + ((1 - fraction) * second);
    }

}
