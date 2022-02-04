/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.scryfall.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for BorderColors.
 */
public enum BorderColors {
    /** Enum value black. */
    BLACK("black"),

    /** Enum value borderless. */
    BORDERLESS("borderless"),

    /** Enum value gold. */
    GOLD("gold"),

    /** Enum value silver. */
    SILVER("silver"),

    /** Enum value white. */
    WHITE("white");

    /** The actual serialized value for a BorderColors instance. */
    private String value;

    BorderColors(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a BorderColors instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed BorderColors object, or null if unable to parse.
     */
    @JsonCreator
    public static BorderColors fromString(String value) {
        BorderColors[] items = BorderColors.values();
        for (BorderColors item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
