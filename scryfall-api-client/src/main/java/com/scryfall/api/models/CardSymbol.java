/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.scryfall.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CardSymbol model.
 */
public class CardSymbol {
    /**
     * The symbol property.
     */
    @JsonProperty(value = "symbol")
    private String symbol;

    /**
     * The looseVariant property.
     */
    @JsonProperty(value = "loose_variant")
    private String looseVariant;

    /**
     * The english property.
     */
    @JsonProperty(value = "english")
    private String english;

    /**
     * The transposable property.
     */
    @JsonProperty(value = "transposable")
    private Boolean transposable;

    /**
     * The representsMana property.
     */
    @JsonProperty(value = "represents_mana")
    private Boolean representsMana;

    /**
     * The cmc property.
     */
    @JsonProperty(value = "cmc")
    private Double cmc;

    /**
     * The appearsInManaCosts property.
     */
    @JsonProperty(value = "appears_in_mana_costs")
    private Boolean appearsInManaCosts;

    /**
     * The funny property.
     */
    @JsonProperty(value = "funny")
    private Boolean funny;

    /**
     * Possible values include: 'W', 'U', 'B', 'R', 'G'.
     */
    @JsonProperty(value = "colors")
    private Colors colors;

    /**
     * Get the symbol value.
     *
     * @return the symbol value
     */
    public String symbol() {
        return this.symbol;
    }

    /**
     * Set the symbol value.
     *
     * @param symbol the symbol value to set
     * @return the CardSymbol object itself.
     */
    public CardSymbol withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Get the looseVariant value.
     *
     * @return the looseVariant value
     */
    public String looseVariant() {
        return this.looseVariant;
    }

    /**
     * Set the looseVariant value.
     *
     * @param looseVariant the looseVariant value to set
     * @return the CardSymbol object itself.
     */
    public CardSymbol withLooseVariant(String looseVariant) {
        this.looseVariant = looseVariant;
        return this;
    }

    /**
     * Get the english value.
     *
     * @return the english value
     */
    public String english() {
        return this.english;
    }

    /**
     * Set the english value.
     *
     * @param english the english value to set
     * @return the CardSymbol object itself.
     */
    public CardSymbol withEnglish(String english) {
        this.english = english;
        return this;
    }

    /**
     * Get the transposable value.
     *
     * @return the transposable value
     */
    public Boolean transposable() {
        return this.transposable;
    }

    /**
     * Set the transposable value.
     *
     * @param transposable the transposable value to set
     * @return the CardSymbol object itself.
     */
    public CardSymbol withTransposable(Boolean transposable) {
        this.transposable = transposable;
        return this;
    }

    /**
     * Get the representsMana value.
     *
     * @return the representsMana value
     */
    public Boolean representsMana() {
        return this.representsMana;
    }

    /**
     * Set the representsMana value.
     *
     * @param representsMana the representsMana value to set
     * @return the CardSymbol object itself.
     */
    public CardSymbol withRepresentsMana(Boolean representsMana) {
        this.representsMana = representsMana;
        return this;
    }

    /**
     * Get the cmc value.
     *
     * @return the cmc value
     */
    public Double cmc() {
        return this.cmc;
    }

    /**
     * Set the cmc value.
     *
     * @param cmc the cmc value to set
     * @return the CardSymbol object itself.
     */
    public CardSymbol withCmc(Double cmc) {
        this.cmc = cmc;
        return this;
    }

    /**
     * Get the appearsInManaCosts value.
     *
     * @return the appearsInManaCosts value
     */
    public Boolean appearsInManaCosts() {
        return this.appearsInManaCosts;
    }

    /**
     * Set the appearsInManaCosts value.
     *
     * @param appearsInManaCosts the appearsInManaCosts value to set
     * @return the CardSymbol object itself.
     */
    public CardSymbol withAppearsInManaCosts(Boolean appearsInManaCosts) {
        this.appearsInManaCosts = appearsInManaCosts;
        return this;
    }

    /**
     * Get the funny value.
     *
     * @return the funny value
     */
    public Boolean funny() {
        return this.funny;
    }

    /**
     * Set the funny value.
     *
     * @param funny the funny value to set
     * @return the CardSymbol object itself.
     */
    public CardSymbol withFunny(Boolean funny) {
        this.funny = funny;
        return this;
    }

    /**
     * Get possible values include: 'W', 'U', 'B', 'R', 'G'.
     *
     * @return the colors value
     */
    public Colors colors() {
        return this.colors;
    }

    /**
     * Set possible values include: 'W', 'U', 'B', 'R', 'G'.
     *
     * @param colors the colors value to set
     * @return the CardSymbol object itself.
     */
    public CardSymbol withColors(Colors colors) {
        this.colors = colors;
        return this;
    }

}
