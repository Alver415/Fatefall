package com.alver.fatefall.api.base;

import com.alver.fatefall.api.PersistedObject;
import com.alver.fatefall.api.base.implementation.ImmutableCard;
import com.alver.fatefall.api.models.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.Nullable;
import org.immutables.value.Value;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Value.Immutable
@Value.Modifiable
@StandardStyle
@JsonSerialize(as = ImmutableCard.class)
@JsonDeserialize(builder = ImmutableCard.Builder.class)
public abstract class Card extends PersistedObject {

    @Nullable
    public abstract UUID getId();

    @Nullable
    public abstract String getName();

    @Nullable
    public abstract UUID getOracleId();

    @Nullable
    public abstract List<Integer> getMultiverseIds();

    @Nullable
    public abstract Integer getMtgoId();

    @Nullable
    public abstract Integer getArenaId();

    @Nullable
    public abstract Integer getMtgoFoilId();

    @Nullable
    public abstract String getUri();

    @Nullable
    public abstract String getScryfallUri();

    @Nullable
    public abstract String getPrintsSearchUri();

    @Nullable
    public abstract String getRulingsUri();

    @Nullable
    public abstract Layouts getLayout();

    @Nullable
    public abstract Double getCmc();

    @Nullable
    public abstract String getTypeLine();

    @Nullable
    public abstract String getOracleText();

    @Nullable
    public abstract String getManaCost();

    @Nullable
    public abstract String getPower();

    @Nullable
    public abstract String getToughness();

    @Nullable
    public abstract String getLoyalty();

    @Nullable
    public abstract String getLifeModifier();

    @Nullable
    public abstract String getHandModifier();

    @Nullable
    public abstract List<Colors> getColors();

    @Nullable
    public abstract List<Colors> getColorIndicator();

    @Nullable
    public abstract List<Colors> getColorIdentity();

    @Nullable
    public abstract List<RelatedCards> getAllParts();

    @Nullable
    @JsonProperty("card_faces")
    public abstract List<CardFace> getCardFaces();

    @Nullable
    public abstract Legality getLegalities();

    @Nullable
    public abstract Boolean getReserved();

    @Nullable
    public abstract Integer getEdhrecRank();

    @Nullable
    public abstract String getSetCode();

    @Nullable
    public abstract String getSetName();

    @Nullable
    public abstract String getCollectorNumber();

    @Nullable
    public abstract String getSetSearchUri();

    @Nullable
    public abstract String getScryfallSetUri();

    @Nullable
    @JsonProperty("image_uris")
    public abstract ImageUri getImageUris();

    @Nullable
    public abstract Boolean getHighresImage();

    @Nullable
    public abstract Boolean getReprint();

    @Nullable
    public abstract Boolean getDigital();

    @Nullable
    public abstract Rarity getRarity();

    @Nullable
    public abstract String getFlavorText();

    @Nullable
    public abstract String getArtist();

    @Nullable
    public abstract UUID getIllustrationId();

    @Nullable
    public abstract String getFrame();

    @Nullable
    public abstract Boolean getFullArt();

    @Nullable
    public abstract String getWatermark();

    @Nullable
    public abstract BorderColors getBorderColor();

    @Nullable
    public abstract Integer getStorySpotlightNumber();

    @Nullable
    public abstract String getStorySpotlightUri();

    @Nullable
    public abstract Boolean getTimeshifted();

    @Nullable
    public abstract Boolean getColorshifted();

    @Nullable
    public abstract Boolean getFutureshifted();

    @Nullable
    public abstract Map<String, String> getPurchaseUris();

    @Nullable
    public abstract Map<String, String> getRelatedUris();

}
