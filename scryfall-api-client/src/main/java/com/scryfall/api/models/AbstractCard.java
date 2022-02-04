package com.scryfall.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scryfall.api.PersistedObject;
import com.sun.istack.Nullable;
import org.immutables.value.Value;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The Card model.
 */
@Value.Immutable
@Value.Style(
        typeAbstract = "Abstract*",
//        typeImmutable = "*",
        privateNoargConstructor = true)
@JsonSerialize(as = ImmutableCard.class)
@JsonDeserialize(builder = ImmutableCard.Builder.class)
public abstract class AbstractCard extends PersistedObject {

    /**
     * The id property.
     */
    @JsonProperty(value = "id")
    @Nullable
    public abstract UUID id();

    /**
     * The name property.
     */
    @JsonProperty(value = "name")
    @Nullable
    public abstract String name();

    /**
     * The oracleId property.
     */
    @JsonProperty(value = "oracle_id")
    @Nullable
    public abstract UUID oracleId();

    /**
     * The multiverseIds property.
     */
    @JsonProperty(value = "multiverse_ids")
    @ElementCollection
    @Nullable
    public abstract List<Integer> multiverseIds();

    /**
     * The mtgoId property.
     */
    @JsonProperty(value = "mtgo_id")
    @Nullable
    public abstract Integer mtgoId();

    /**
     * The arenaId property.
     */
    @JsonProperty(value = "arena_id")
    @Nullable
    public abstract Integer arenaId();

    /**
     * The mtgoFoilId property.
     */
    @JsonProperty(value = "mtgo_foil_id")
    @Nullable
    public abstract Integer mtgoFoilId();

    /**
     * The uri property.
     */
    @JsonProperty(value = "uri")
    @Nullable
    public abstract String uri();

    /**
     * The scryfallUri property.
     */
    @JsonProperty(value = "scryfall_uri")
    @Nullable
    public abstract String scryfallUri();

    /**
     * The printsSearchUri property.
     */
    @JsonProperty(value = "prints_search_uri")
    @Nullable
    public abstract String printsSearchUri();

    /**
     * The rulingsUri property.
     */
    @JsonProperty(value = "rulings_uri")
    @Nullable
    public abstract String rulingsUri();

    /**
     * Possible values include: 'normal', 'split', 'flip', 'transform', 'meld',
     * 'leveler', 'saga', 'planar', 'scheme', 'vanguard', 'token',
     * 'double_faced_token', 'emblem', 'augment', 'host'.
     */
    @JsonProperty(value = "layout")
    @Enumerated(EnumType.STRING)
    @Nullable
    public abstract Layouts layout();

    /**
     * The cmc property.
     */
    @JsonProperty(value = "cmc")
    @Nullable
    public abstract Double cmc();

    /**
     * The typeLine property.
     */
    @JsonProperty(value = "type_line")
    @Nullable
    public abstract String typeLine();

    /**
     * The oracleText property.
     */
    @JsonProperty(value = "oracle_text")
    @Nullable
    public abstract String oracleText();

    /**
     * The manaCost property.
     */
    @JsonProperty(value = "mana_cost")
    @Nullable
    public abstract String manaCost();

    /**
     * The power property.
     */
    @JsonProperty(value = "power")
    @Nullable
    public abstract String power();

    /**
     * The toughness property.
     */
    @JsonProperty(value = "toughness")
    @Nullable
    public abstract String toughness();

    /**
     * The loyalty property.
     */
    @JsonProperty(value = "loyalty")
    @Nullable
    public abstract String loyalty();

    /**
     * The lifeModifier property.
     */
    @JsonProperty(value = "life_modifier")
    @Nullable
    public abstract String lifeModifier();

    /**
     * The handModifier property.
     */
    @JsonProperty(value = "hand_modifier")
    @Nullable
    public abstract String handModifier();

    /**
     * The colors property.
     */
    @JsonProperty(value = "colors")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Nullable
    public abstract List<Colors> colors();

    /**
     * The colorIndicator property.
     */
    @JsonProperty(value = "color_indicator")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Nullable
    public abstract List<Colors> colorIndicator();

    /**
     * The colorIdentity property.
     */
    @JsonProperty(value = "color_identity")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Nullable
    public abstract List<Colors> colorIdentity();

    /**
     * The allParts property.
     */
    @JsonProperty(value = "all_parts")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Nullable
    public abstract List<RelatedCards> allParts();

    /**
     * The cardFaces property.
     */
    @JsonProperty(value = "card_faces")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Nullable
    public abstract List<CardFace> cardFaces();

    /**
     * The legalities property.
     */
    @JsonProperty(value = "legalities")
    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    public abstract Legality legalities();

    /**
     * The reserved property.
     */
    @JsonProperty(value = "reserved")
    @Nullable
    public abstract Boolean reserved();

    /**
     * The edhrecRank property.
     */
    @JsonProperty(value = "edhrec_rank")
    @Nullable
    public abstract Integer edhrecRank();

    /**
     * The set property.
     */
    @JsonProperty(value = "set")
    @Nullable
    public abstract String setCode();

    /**
     * The setName property.
     */
    @JsonProperty(value = "set_name")
    @Nullable
    public abstract String setName();

    /**
     * The collectorNumber property.
     */
    @JsonProperty(value = "collector_number")
    @Nullable
    public abstract String collectorNumber();

    /**
     * The setSearchUri property.
     */
    @JsonProperty(value = "set_search_uri")
    @Nullable
    public abstract String setSearchUri();

    /**
     * The scryfallSetUri property.
     */
    @JsonProperty(value = "scryfall_set_uri")
    @Nullable
    public abstract String scryfallSetUri();

    /**
     * The imageUris property.
     */
    @JsonProperty(value = "image_uris")
    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    public abstract ImageUri imageUris();

    /**
     * The highresImage property.
     */
    @JsonProperty(value = "highres_image")
    @Nullable
    public abstract Boolean highresImage();

    /**
     * The reprint property.
     */
    @JsonProperty(value = "reprint")
    @Nullable
    public abstract Boolean reprint();

    /**
     * The digital property.
     */
    @JsonProperty(value = "digital")
    @Nullable
    public abstract Boolean digital();

    /**
     * Possible values include: 'common', 'uncommon', 'rare', 'mythic'.
     */
    @JsonProperty(value = "rarity")
    @Enumerated(EnumType.STRING)
    @Nullable
    public abstract Rarity rarity();

    /**
     * The flavorText property.
     */
    @JsonProperty(value = "flavor_text")
    @Nullable
    public abstract String flavorText();

    /**
     * The artist property.
     */
    @JsonProperty(value = "artist")
    @Nullable
    public abstract String artist();

    /**
     * The illustrationId property.
     */
    @JsonProperty(value = "illustration_id")
    @Nullable
    public abstract UUID illustrationId();

    /**
     * The frame property.
     */
    @JsonProperty(value = "frame")
    @Nullable
    public abstract String frame();

    /**
     * The fullArt property.
     */
    @JsonProperty(value = "full_art")
    @Nullable
    public abstract Boolean fullArt();

    /**
     * The watermark property.
     */
    @JsonProperty(value = "watermark")
    @Nullable
    public abstract String watermark();

    /**
     * Possible values include: 'black', 'borderless', 'gold', 'silver',
     * 'white'.
     */
    @JsonProperty(value = "border_color")
    @Enumerated(EnumType.STRING)
    @Nullable
    public abstract BorderColors borderColor();

    /**
     * The storySpotlightNumber property.
     */
    @JsonProperty(value = "story_spotlight_number")
    @Nullable
    public abstract Integer storySpotlightNumber();

    /**
     * The storySpotlightUri property.
     */
    @JsonProperty(value = "story_spotlight_uri")
    @Nullable
    public abstract String storySpotlightUri();

    /**
     * The timeshifted property.
     */
    @JsonProperty(value = "timeshifted")
    @Nullable
    public abstract Boolean timeshifted();

    /**
     * The colorshifted property.
     */
    @JsonProperty(value = "colorshifted")
    @Nullable
    public abstract Boolean colorshifted();

    /**
     * The futureshifted property.
     */
    @JsonProperty(value = "futureshifted")
    @Nullable
    public abstract Boolean futureshifted();

    /**
     * The purchaseUris property.
     */
    @JsonProperty(value = "purchase_uris")
    @ElementCollection
    @CollectionTable(name = "purchase_uris",
            joinColumns = {@JoinColumn(name = "card_pk", referencedColumnName = "pk")})
    @MapKeyColumn(name = "key")
    @Column(name = "uri")
    @Nullable
    public abstract Map<String, String> purchaseUris();

    /**
     * The relatedUris property.
     */
    @JsonProperty(value = "related_uris")
    @ElementCollection
    @CollectionTable(name = "related_uris",
            joinColumns = {@JoinColumn(name = "card_pk", referencedColumnName = "pk")})
    @MapKeyColumn(name = "key")
    @Column(name = "uri")
    @Nullable
    public abstract Map<String, String> relatedUris();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
