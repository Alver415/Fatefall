/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.scryfall.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scryfall.api.PersistedObject;

import javax.persistence.*;

/**
 * The ImageUri model.
 */
@Entity
public class ImageUri extends PersistedObject {

    /**
     * The small property.
     */
    @JsonProperty(value = "small")
    private String small;

    /**
     * The normal property.
     */
    @JsonProperty(value = "normal")
    private String normal;

    /**
     * The large property.
     */
    @JsonProperty(value = "large")
    private String large;

    /**
     * The png property.
     */
    @JsonProperty(value = "png")
    private String png;

    /**
     * The artCrop property.
     */
    @JsonProperty(value = "art_crop")
    private String artCrop;

    /**
     * The borderCrop property.
     */
    @JsonProperty(value = "border_crop")
    private String borderCrop;

    /**
     * Get the small value.
     *
     * @return the small value
     */
    public String small() {
        return this.small;
    }

    /**
     * Set the small value.
     *
     * @param small the small value to set
     * @return the ImageUri object itself.
     */
    public ImageUri withSmall(String small) {
        this.small = small;
        return this;
    }

    /**
     * Get the normal value.
     *
     * @return the normal value
     */
    public String normal() {
        return this.normal;
    }

    /**
     * Set the normal value.
     *
     * @param normal the normal value to set
     * @return the ImageUri object itself.
     */
    public ImageUri withNormal(String normal) {
        this.normal = normal;
        return this;
    }

    /**
     * Get the large value.
     *
     * @return the large value
     */
    public String large() {
        return this.large;
    }

    /**
     * Set the large value.
     *
     * @param large the large value to set
     * @return the ImageUri object itself.
     */
    public ImageUri withLarge(String large) {
        this.large = large;
        return this;
    }

    /**
     * Get the png value.
     *
     * @return the png value
     */
    public String png() {
        return this.png;
    }

    /**
     * Set the png value.
     *
     * @param png the png value to set
     * @return the ImageUri object itself.
     */
    public ImageUri withPng(String png) {
        this.png = png;
        return this;
    }

    /**
     * Get the artCrop value.
     *
     * @return the artCrop value
     */
    public String artCrop() {
        return this.artCrop;
    }

    /**
     * Set the artCrop value.
     *
     * @param artCrop the artCrop value to set
     * @return the ImageUri object itself.
     */
    public ImageUri withArtCrop(String artCrop) {
        this.artCrop = artCrop;
        return this;
    }

    /**
     * Get the borderCrop value.
     *
     * @return the borderCrop value
     */
    public String borderCrop() {
        return this.borderCrop;
    }

    /**
     * Set the borderCrop value.
     *
     * @param borderCrop the borderCrop value to set
     * @return the ImageUri object itself.
     */
    public ImageUri withBorderCrop(String borderCrop) {
        this.borderCrop = borderCrop;
        return this;
    }

}
