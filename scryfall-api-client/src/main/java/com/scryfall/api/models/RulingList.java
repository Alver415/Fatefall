/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.scryfall.api.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The RulingList model.
 */
public class RulingList {
    /**
     * The data property.
     */
    @JsonProperty(value = "data")
    private List<Ruling> data;

    /**
     * Get the data value.
     *
     * @return the data value
     */
    public List<Ruling> data() {
        return this.data;
    }

    /**
     * Set the data value.
     *
     * @param data the data value to set
     * @return the RulingList object itself.
     */
    public RulingList withData(List<Ruling> data) {
        this.data = data;
        return this;
    }

}
