package com.alver.fatefall.api.base.implementation;

import com.alver.fatefall.api.base.Card;
import com.alver.fatefall.api.models.BorderColors;
import com.alver.fatefall.api.models.CardFace;
import com.alver.fatefall.api.models.Colors;
import com.alver.fatefall.api.models.ImageUri;
import com.alver.fatefall.api.models.Layouts;
import com.alver.fatefall.api.models.Legality;
import com.alver.fatefall.api.models.Rarity;
import com.alver.fatefall.api.models.RelatedCards;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link com.alver.fatefall.api.base.Card}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableCard.builder()}.
 */
@Generated(from = "com.alver.fatefall.api.base.Card", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutableCard extends Card {
  private final @Nullable UUID id;
  private final @Nullable String name;
  private final @Nullable UUID oracleId;
  private final @Nullable List<Integer> multiverseIds;
  private final @Nullable Integer mtgoId;
  private final @Nullable Integer arenaId;
  private final @Nullable Integer mtgoFoilId;
  private final @Nullable String uri;
  private final @Nullable String scryfallUri;
  private final @Nullable String printsSearchUri;
  private final @Nullable String rulingsUri;
  private final @Nullable Layouts layout;
  private final @Nullable Double cmc;
  private final @Nullable String typeLine;
  private final @Nullable String oracleText;
  private final @Nullable String manaCost;
  private final @Nullable String power;
  private final @Nullable String toughness;
  private final @Nullable String loyalty;
  private final @Nullable String lifeModifier;
  private final @Nullable String handModifier;
  private final @Nullable List<Colors> colors;
  private final @Nullable List<Colors> colorIndicator;
  private final @Nullable List<Colors> colorIdentity;
  private final @Nullable List<RelatedCards> allParts;
  private final @Nullable List<CardFace> cardFaces;
  private final @Nullable Legality legalities;
  private final @Nullable Boolean reserved;
  private final @Nullable Integer edhrecRank;
  private final @Nullable String setCode;
  private final @Nullable String setName;
  private final @Nullable String collectorNumber;
  private final @Nullable String setSearchUri;
  private final @Nullable String scryfallSetUri;
  private final @Nullable ImageUri imageUris;
  private final @Nullable Boolean highresImage;
  private final @Nullable Boolean reprint;
  private final @Nullable Boolean digital;
  private final @Nullable Rarity rarity;
  private final @Nullable String flavorText;
  private final @Nullable String artist;
  private final @Nullable UUID illustrationId;
  private final @Nullable String frame;
  private final @Nullable Boolean fullArt;
  private final @Nullable String watermark;
  private final @Nullable BorderColors borderColor;
  private final @Nullable Integer storySpotlightNumber;
  private final @Nullable String storySpotlightUri;
  private final @Nullable Boolean timeshifted;
  private final @Nullable Boolean colorshifted;
  private final @Nullable Boolean futureshifted;
  private final @Nullable Map<String, String> purchaseUris;
  private final @Nullable Map<String, String> relatedUris;

  private ImmutableCard() {
    this.id = null;
    this.name = null;
    this.oracleId = null;
    this.multiverseIds = null;
    this.mtgoId = null;
    this.arenaId = null;
    this.mtgoFoilId = null;
    this.uri = null;
    this.scryfallUri = null;
    this.printsSearchUri = null;
    this.rulingsUri = null;
    this.layout = null;
    this.cmc = null;
    this.typeLine = null;
    this.oracleText = null;
    this.manaCost = null;
    this.power = null;
    this.toughness = null;
    this.loyalty = null;
    this.lifeModifier = null;
    this.handModifier = null;
    this.colors = null;
    this.colorIndicator = null;
    this.colorIdentity = null;
    this.allParts = null;
    this.cardFaces = null;
    this.legalities = null;
    this.reserved = null;
    this.edhrecRank = null;
    this.setCode = null;
    this.setName = null;
    this.collectorNumber = null;
    this.setSearchUri = null;
    this.scryfallSetUri = null;
    this.imageUris = null;
    this.highresImage = null;
    this.reprint = null;
    this.digital = null;
    this.rarity = null;
    this.flavorText = null;
    this.artist = null;
    this.illustrationId = null;
    this.frame = null;
    this.fullArt = null;
    this.watermark = null;
    this.borderColor = null;
    this.storySpotlightNumber = null;
    this.storySpotlightUri = null;
    this.timeshifted = null;
    this.colorshifted = null;
    this.futureshifted = null;
    this.purchaseUris = null;
    this.relatedUris = null;
  }

  private ImmutableCard(
      @Nullable UUID id,
      @Nullable String name,
      @Nullable UUID oracleId,
      @Nullable List<Integer> multiverseIds,
      @Nullable Integer mtgoId,
      @Nullable Integer arenaId,
      @Nullable Integer mtgoFoilId,
      @Nullable String uri,
      @Nullable String scryfallUri,
      @Nullable String printsSearchUri,
      @Nullable String rulingsUri,
      @Nullable Layouts layout,
      @Nullable Double cmc,
      @Nullable String typeLine,
      @Nullable String oracleText,
      @Nullable String manaCost,
      @Nullable String power,
      @Nullable String toughness,
      @Nullable String loyalty,
      @Nullable String lifeModifier,
      @Nullable String handModifier,
      @Nullable List<Colors> colors,
      @Nullable List<Colors> colorIndicator,
      @Nullable List<Colors> colorIdentity,
      @Nullable List<RelatedCards> allParts,
      @Nullable List<CardFace> cardFaces,
      @Nullable Legality legalities,
      @Nullable Boolean reserved,
      @Nullable Integer edhrecRank,
      @Nullable String setCode,
      @Nullable String setName,
      @Nullable String collectorNumber,
      @Nullable String setSearchUri,
      @Nullable String scryfallSetUri,
      @Nullable ImageUri imageUris,
      @Nullable Boolean highresImage,
      @Nullable Boolean reprint,
      @Nullable Boolean digital,
      @Nullable Rarity rarity,
      @Nullable String flavorText,
      @Nullable String artist,
      @Nullable UUID illustrationId,
      @Nullable String frame,
      @Nullable Boolean fullArt,
      @Nullable String watermark,
      @Nullable BorderColors borderColor,
      @Nullable Integer storySpotlightNumber,
      @Nullable String storySpotlightUri,
      @Nullable Boolean timeshifted,
      @Nullable Boolean colorshifted,
      @Nullable Boolean futureshifted,
      @Nullable Map<String, String> purchaseUris,
      @Nullable Map<String, String> relatedUris) {
    this.id = id;
    this.name = name;
    this.oracleId = oracleId;
    this.multiverseIds = multiverseIds;
    this.mtgoId = mtgoId;
    this.arenaId = arenaId;
    this.mtgoFoilId = mtgoFoilId;
    this.uri = uri;
    this.scryfallUri = scryfallUri;
    this.printsSearchUri = printsSearchUri;
    this.rulingsUri = rulingsUri;
    this.layout = layout;
    this.cmc = cmc;
    this.typeLine = typeLine;
    this.oracleText = oracleText;
    this.manaCost = manaCost;
    this.power = power;
    this.toughness = toughness;
    this.loyalty = loyalty;
    this.lifeModifier = lifeModifier;
    this.handModifier = handModifier;
    this.colors = colors;
    this.colorIndicator = colorIndicator;
    this.colorIdentity = colorIdentity;
    this.allParts = allParts;
    this.cardFaces = cardFaces;
    this.legalities = legalities;
    this.reserved = reserved;
    this.edhrecRank = edhrecRank;
    this.setCode = setCode;
    this.setName = setName;
    this.collectorNumber = collectorNumber;
    this.setSearchUri = setSearchUri;
    this.scryfallSetUri = scryfallSetUri;
    this.imageUris = imageUris;
    this.highresImage = highresImage;
    this.reprint = reprint;
    this.digital = digital;
    this.rarity = rarity;
    this.flavorText = flavorText;
    this.artist = artist;
    this.illustrationId = illustrationId;
    this.frame = frame;
    this.fullArt = fullArt;
    this.watermark = watermark;
    this.borderColor = borderColor;
    this.storySpotlightNumber = storySpotlightNumber;
    this.storySpotlightUri = storySpotlightUri;
    this.timeshifted = timeshifted;
    this.colorshifted = colorshifted;
    this.futureshifted = futureshifted;
    this.purchaseUris = purchaseUris;
    this.relatedUris = relatedUris;
  }

  /**
   * @return The value of the {@code id} attribute
   */
  @JsonProperty("id")
  @Override
  public @Nullable UUID getId() {
    return id;
  }

  /**
   * @return The value of the {@code name} attribute
   */
  @JsonProperty("name")
  @Override
  public @Nullable String getName() {
    return name;
  }

  /**
   * @return The value of the {@code oracleId} attribute
   */
  @JsonProperty("oracleId")
  @Override
  public @Nullable UUID getOracleId() {
    return oracleId;
  }

  /**
   * @return The value of the {@code multiverseIds} attribute
   */
  @JsonProperty("multiverseIds")
  @Override
  public @Nullable List<Integer> getMultiverseIds() {
    return multiverseIds;
  }

  /**
   * @return The value of the {@code mtgoId} attribute
   */
  @JsonProperty("mtgoId")
  @Override
  public @Nullable Integer getMtgoId() {
    return mtgoId;
  }

  /**
   * @return The value of the {@code arenaId} attribute
   */
  @JsonProperty("arenaId")
  @Override
  public @Nullable Integer getArenaId() {
    return arenaId;
  }

  /**
   * @return The value of the {@code mtgoFoilId} attribute
   */
  @JsonProperty("mtgoFoilId")
  @Override
  public @Nullable Integer getMtgoFoilId() {
    return mtgoFoilId;
  }

  /**
   * @return The value of the {@code uri} attribute
   */
  @JsonProperty("uri")
  @Override
  public @Nullable String getUri() {
    return uri;
  }

  /**
   * @return The value of the {@code scryfallUri} attribute
   */
  @JsonProperty("scryfallUri")
  @Override
  public @Nullable String getScryfallUri() {
    return scryfallUri;
  }

  /**
   * @return The value of the {@code printsSearchUri} attribute
   */
  @JsonProperty("printsSearchUri")
  @Override
  public @Nullable String getPrintsSearchUri() {
    return printsSearchUri;
  }

  /**
   * @return The value of the {@code rulingsUri} attribute
   */
  @JsonProperty("rulingsUri")
  @Override
  public @Nullable String getRulingsUri() {
    return rulingsUri;
  }

  /**
   * @return The value of the {@code layout} attribute
   */
  @JsonProperty("layout")
  @Override
  public @Nullable Layouts getLayout() {
    return layout;
  }

  /**
   * @return The value of the {@code cmc} attribute
   */
  @JsonProperty("cmc")
  @Override
  public @Nullable Double getCmc() {
    return cmc;
  }

  /**
   * @return The value of the {@code typeLine} attribute
   */
  @JsonProperty("typeLine")
  @Override
  public @Nullable String getTypeLine() {
    return typeLine;
  }

  /**
   * @return The value of the {@code oracleText} attribute
   */
  @JsonProperty("oracleText")
  @Override
  public @Nullable String getOracleText() {
    return oracleText;
  }

  /**
   * @return The value of the {@code manaCost} attribute
   */
  @JsonProperty("manaCost")
  @Override
  public @Nullable String getManaCost() {
    return manaCost;
  }

  /**
   * @return The value of the {@code power} attribute
   */
  @JsonProperty("power")
  @Override
  public @Nullable String getPower() {
    return power;
  }

  /**
   * @return The value of the {@code toughness} attribute
   */
  @JsonProperty("toughness")
  @Override
  public @Nullable String getToughness() {
    return toughness;
  }

  /**
   * @return The value of the {@code loyalty} attribute
   */
  @JsonProperty("loyalty")
  @Override
  public @Nullable String getLoyalty() {
    return loyalty;
  }

  /**
   * @return The value of the {@code lifeModifier} attribute
   */
  @JsonProperty("lifeModifier")
  @Override
  public @Nullable String getLifeModifier() {
    return lifeModifier;
  }

  /**
   * @return The value of the {@code handModifier} attribute
   */
  @JsonProperty("handModifier")
  @Override
  public @Nullable String getHandModifier() {
    return handModifier;
  }

  /**
   * @return The value of the {@code colors} attribute
   */
  @JsonProperty("colors")
  @Override
  public @Nullable List<Colors> getColors() {
    return colors;
  }

  /**
   * @return The value of the {@code colorIndicator} attribute
   */
  @JsonProperty("colorIndicator")
  @Override
  public @Nullable List<Colors> getColorIndicator() {
    return colorIndicator;
  }

  /**
   * @return The value of the {@code colorIdentity} attribute
   */
  @JsonProperty("colorIdentity")
  @Override
  public @Nullable List<Colors> getColorIdentity() {
    return colorIdentity;
  }

  /**
   * @return The value of the {@code allParts} attribute
   */
  @JsonProperty("allParts")
  @Override
  public @Nullable List<RelatedCards> getAllParts() {
    return allParts;
  }

  /**
   * @return The value of the {@code cardFaces} attribute
   */
  @JsonProperty("card_faces")
  @Override
  public @Nullable List<CardFace> getCardFaces() {
    return cardFaces;
  }

  /**
   * @return The value of the {@code legalities} attribute
   */
  @JsonProperty("legalities")
  @Override
  public @Nullable Legality getLegalities() {
    return legalities;
  }

  /**
   * @return The value of the {@code reserved} attribute
   */
  @JsonProperty("reserved")
  @Override
  public @Nullable Boolean getReserved() {
    return reserved;
  }

  /**
   * @return The value of the {@code edhrecRank} attribute
   */
  @JsonProperty("edhrecRank")
  @Override
  public @Nullable Integer getEdhrecRank() {
    return edhrecRank;
  }

  /**
   * @return The value of the {@code setCode} attribute
   */
  @JsonProperty("setCode")
  @Override
  public @Nullable String getSetCode() {
    return setCode;
  }

  /**
   * @return The value of the {@code setName} attribute
   */
  @JsonProperty("setName")
  @Override
  public @Nullable String getSetName() {
    return setName;
  }

  /**
   * @return The value of the {@code collectorNumber} attribute
   */
  @JsonProperty("collectorNumber")
  @Override
  public @Nullable String getCollectorNumber() {
    return collectorNumber;
  }

  /**
   * @return The value of the {@code setSearchUri} attribute
   */
  @JsonProperty("setSearchUri")
  @Override
  public @Nullable String getSetSearchUri() {
    return setSearchUri;
  }

  /**
   * @return The value of the {@code scryfallSetUri} attribute
   */
  @JsonProperty("scryfallSetUri")
  @Override
  public @Nullable String getScryfallSetUri() {
    return scryfallSetUri;
  }

  /**
   * @return The value of the {@code imageUris} attribute
   */
  @JsonProperty("image_uris")
  @Override
  public @Nullable ImageUri getImageUris() {
    return imageUris;
  }

  /**
   * @return The value of the {@code highresImage} attribute
   */
  @JsonProperty("highresImage")
  @Override
  public @Nullable Boolean getHighresImage() {
    return highresImage;
  }

  /**
   * @return The value of the {@code reprint} attribute
   */
  @JsonProperty("reprint")
  @Override
  public @Nullable Boolean getReprint() {
    return reprint;
  }

  /**
   * @return The value of the {@code digital} attribute
   */
  @JsonProperty("digital")
  @Override
  public @Nullable Boolean getDigital() {
    return digital;
  }

  /**
   * @return The value of the {@code rarity} attribute
   */
  @JsonProperty("rarity")
  @Override
  public @Nullable Rarity getRarity() {
    return rarity;
  }

  /**
   * @return The value of the {@code flavorText} attribute
   */
  @JsonProperty("flavorText")
  @Override
  public @Nullable String getFlavorText() {
    return flavorText;
  }

  /**
   * @return The value of the {@code artist} attribute
   */
  @JsonProperty("artist")
  @Override
  public @Nullable String getArtist() {
    return artist;
  }

  /**
   * @return The value of the {@code illustrationId} attribute
   */
  @JsonProperty("illustrationId")
  @Override
  public @Nullable UUID getIllustrationId() {
    return illustrationId;
  }

  /**
   * @return The value of the {@code frame} attribute
   */
  @JsonProperty("frame")
  @Override
  public @Nullable String getFrame() {
    return frame;
  }

  /**
   * @return The value of the {@code fullArt} attribute
   */
  @JsonProperty("fullArt")
  @Override
  public @Nullable Boolean getFullArt() {
    return fullArt;
  }

  /**
   * @return The value of the {@code watermark} attribute
   */
  @JsonProperty("watermark")
  @Override
  public @Nullable String getWatermark() {
    return watermark;
  }

  /**
   * @return The value of the {@code borderColor} attribute
   */
  @JsonProperty("borderColor")
  @Override
  public @Nullable BorderColors getBorderColor() {
    return borderColor;
  }

  /**
   * @return The value of the {@code storySpotlightNumber} attribute
   */
  @JsonProperty("storySpotlightNumber")
  @Override
  public @Nullable Integer getStorySpotlightNumber() {
    return storySpotlightNumber;
  }

  /**
   * @return The value of the {@code storySpotlightUri} attribute
   */
  @JsonProperty("storySpotlightUri")
  @Override
  public @Nullable String getStorySpotlightUri() {
    return storySpotlightUri;
  }

  /**
   * @return The value of the {@code timeshifted} attribute
   */
  @JsonProperty("timeshifted")
  @Override
  public @Nullable Boolean getTimeshifted() {
    return timeshifted;
  }

  /**
   * @return The value of the {@code colorshifted} attribute
   */
  @JsonProperty("colorshifted")
  @Override
  public @Nullable Boolean getColorshifted() {
    return colorshifted;
  }

  /**
   * @return The value of the {@code futureshifted} attribute
   */
  @JsonProperty("futureshifted")
  @Override
  public @Nullable Boolean getFutureshifted() {
    return futureshifted;
  }

  /**
   * @return The value of the {@code purchaseUris} attribute
   */
  @JsonProperty("purchaseUris")
  @Override
  public @Nullable Map<String, String> getPurchaseUris() {
    return purchaseUris;
  }

  /**
   * @return The value of the {@code relatedUris} attribute
   */
  @JsonProperty("relatedUris")
  @Override
  public @Nullable Map<String, String> getRelatedUris() {
    return relatedUris;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getId() id} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for id (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withId(@Nullable UUID value) {
    if (this.id == value) return this;
    return new ImmutableCard(
        value,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getName() name} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for name (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withName(@Nullable String value) {
    if (Objects.equals(this.name, value)) return this;
    return new ImmutableCard(
        this.id,
        value,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getOracleId() oracleId} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for oracleId (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withOracleId(@Nullable UUID value) {
    if (this.oracleId == value) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        value,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withMultiverseIds(@Nullable int... elements) {
    if (elements == null) {
      return new ImmutableCard(
          this.id,
          this.name,
          this.oracleId,
          null,
          this.mtgoId,
          this.arenaId,
          this.mtgoFoilId,
          this.uri,
          this.scryfallUri,
          this.printsSearchUri,
          this.rulingsUri,
          this.layout,
          this.cmc,
          this.typeLine,
          this.oracleText,
          this.manaCost,
          this.power,
          this.toughness,
          this.loyalty,
          this.lifeModifier,
          this.handModifier,
          this.colors,
          this.colorIndicator,
          this.colorIdentity,
          this.allParts,
          this.cardFaces,
          this.legalities,
          this.reserved,
          this.edhrecRank,
          this.setCode,
          this.setName,
          this.collectorNumber,
          this.setSearchUri,
          this.scryfallSetUri,
          this.imageUris,
          this.highresImage,
          this.reprint,
          this.digital,
          this.rarity,
          this.flavorText,
          this.artist,
          this.illustrationId,
          this.frame,
          this.fullArt,
          this.watermark,
          this.borderColor,
          this.storySpotlightNumber,
          this.storySpotlightUri,
          this.timeshifted,
          this.colorshifted,
          this.futureshifted,
          this.purchaseUris,
          this.relatedUris);
    }
    ArrayList<Integer> wrappedList = new ArrayList<>(elements.length);
    for (int element : elements) {
      wrappedList.add(element);
    }
    List<Integer> newValue = createUnmodifiableList(false, wrappedList);
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        newValue,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of multiverseIds elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withMultiverseIds(@Nullable Iterable<Integer> elements) {
    if (this.multiverseIds == elements) return this;
    @Nullable List<Integer> newValue = elements == null ? null : createUnmodifiableList(false, createSafeList(elements, true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        newValue,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getMtgoId() mtgoId} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for mtgoId (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withMtgoId(@Nullable Integer value) {
    if (Objects.equals(this.mtgoId, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        value,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getArenaId() arenaId} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for arenaId (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withArenaId(@Nullable Integer value) {
    if (Objects.equals(this.arenaId, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        value,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getMtgoFoilId() mtgoFoilId} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for mtgoFoilId (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withMtgoFoilId(@Nullable Integer value) {
    if (Objects.equals(this.mtgoFoilId, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        value,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getUri() uri} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for uri (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withUri(@Nullable String value) {
    if (Objects.equals(this.uri, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        value,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getScryfallUri() scryfallUri} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for scryfallUri (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withScryfallUri(@Nullable String value) {
    if (Objects.equals(this.scryfallUri, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        value,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getPrintsSearchUri() printsSearchUri} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for printsSearchUri (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withPrintsSearchUri(@Nullable String value) {
    if (Objects.equals(this.printsSearchUri, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        value,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getRulingsUri() rulingsUri} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for rulingsUri (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withRulingsUri(@Nullable String value) {
    if (Objects.equals(this.rulingsUri, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        value,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getLayout() layout} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for layout (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withLayout(@Nullable Layouts value) {
    if (this.layout == value) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        value,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getCmc() cmc} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for cmc (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withCmc(@Nullable Double value) {
    if (Objects.equals(this.cmc, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        value,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getTypeLine() typeLine} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for typeLine (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withTypeLine(@Nullable String value) {
    if (Objects.equals(this.typeLine, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        value,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getOracleText() oracleText} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for oracleText (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withOracleText(@Nullable String value) {
    if (Objects.equals(this.oracleText, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        value,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getManaCost() manaCost} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for manaCost (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withManaCost(@Nullable String value) {
    if (Objects.equals(this.manaCost, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        value,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getPower() power} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for power (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withPower(@Nullable String value) {
    if (Objects.equals(this.power, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        value,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getToughness() toughness} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for toughness (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withToughness(@Nullable String value) {
    if (Objects.equals(this.toughness, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        value,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getLoyalty() loyalty} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for loyalty (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withLoyalty(@Nullable String value) {
    if (Objects.equals(this.loyalty, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        value,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getLifeModifier() lifeModifier} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for lifeModifier (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withLifeModifier(@Nullable String value) {
    if (Objects.equals(this.lifeModifier, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        value,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getHandModifier() handModifier} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for handModifier (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withHandModifier(@Nullable String value) {
    if (Objects.equals(this.handModifier, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        value,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getColors() colors}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withColors(@Nullable Colors... elements) {
    if (elements == null) {
      return new ImmutableCard(
          this.id,
          this.name,
          this.oracleId,
          this.multiverseIds,
          this.mtgoId,
          this.arenaId,
          this.mtgoFoilId,
          this.uri,
          this.scryfallUri,
          this.printsSearchUri,
          this.rulingsUri,
          this.layout,
          this.cmc,
          this.typeLine,
          this.oracleText,
          this.manaCost,
          this.power,
          this.toughness,
          this.loyalty,
          this.lifeModifier,
          this.handModifier,
          null,
          this.colorIndicator,
          this.colorIdentity,
          this.allParts,
          this.cardFaces,
          this.legalities,
          this.reserved,
          this.edhrecRank,
          this.setCode,
          this.setName,
          this.collectorNumber,
          this.setSearchUri,
          this.scryfallSetUri,
          this.imageUris,
          this.highresImage,
          this.reprint,
          this.digital,
          this.rarity,
          this.flavorText,
          this.artist,
          this.illustrationId,
          this.frame,
          this.fullArt,
          this.watermark,
          this.borderColor,
          this.storySpotlightNumber,
          this.storySpotlightUri,
          this.timeshifted,
          this.colorshifted,
          this.futureshifted,
          this.purchaseUris,
          this.relatedUris);
    }
    @Nullable List<Colors> newValue = Arrays.asList(elements) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(elements), true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        newValue,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getColors() colors}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of colors elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withColors(@Nullable Iterable<? extends Colors> elements) {
    if (this.colors == elements) return this;
    @Nullable List<Colors> newValue = elements == null ? null : createUnmodifiableList(false, createSafeList(elements, true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        newValue,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withColorIndicator(@Nullable Colors... elements) {
    if (elements == null) {
      return new ImmutableCard(
          this.id,
          this.name,
          this.oracleId,
          this.multiverseIds,
          this.mtgoId,
          this.arenaId,
          this.mtgoFoilId,
          this.uri,
          this.scryfallUri,
          this.printsSearchUri,
          this.rulingsUri,
          this.layout,
          this.cmc,
          this.typeLine,
          this.oracleText,
          this.manaCost,
          this.power,
          this.toughness,
          this.loyalty,
          this.lifeModifier,
          this.handModifier,
          this.colors,
          null,
          this.colorIdentity,
          this.allParts,
          this.cardFaces,
          this.legalities,
          this.reserved,
          this.edhrecRank,
          this.setCode,
          this.setName,
          this.collectorNumber,
          this.setSearchUri,
          this.scryfallSetUri,
          this.imageUris,
          this.highresImage,
          this.reprint,
          this.digital,
          this.rarity,
          this.flavorText,
          this.artist,
          this.illustrationId,
          this.frame,
          this.fullArt,
          this.watermark,
          this.borderColor,
          this.storySpotlightNumber,
          this.storySpotlightUri,
          this.timeshifted,
          this.colorshifted,
          this.futureshifted,
          this.purchaseUris,
          this.relatedUris);
    }
    @Nullable List<Colors> newValue = Arrays.asList(elements) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(elements), true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        newValue,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of colorIndicator elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withColorIndicator(@Nullable Iterable<? extends Colors> elements) {
    if (this.colorIndicator == elements) return this;
    @Nullable List<Colors> newValue = elements == null ? null : createUnmodifiableList(false, createSafeList(elements, true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        newValue,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withColorIdentity(@Nullable Colors... elements) {
    if (elements == null) {
      return new ImmutableCard(
          this.id,
          this.name,
          this.oracleId,
          this.multiverseIds,
          this.mtgoId,
          this.arenaId,
          this.mtgoFoilId,
          this.uri,
          this.scryfallUri,
          this.printsSearchUri,
          this.rulingsUri,
          this.layout,
          this.cmc,
          this.typeLine,
          this.oracleText,
          this.manaCost,
          this.power,
          this.toughness,
          this.loyalty,
          this.lifeModifier,
          this.handModifier,
          this.colors,
          this.colorIndicator,
          null,
          this.allParts,
          this.cardFaces,
          this.legalities,
          this.reserved,
          this.edhrecRank,
          this.setCode,
          this.setName,
          this.collectorNumber,
          this.setSearchUri,
          this.scryfallSetUri,
          this.imageUris,
          this.highresImage,
          this.reprint,
          this.digital,
          this.rarity,
          this.flavorText,
          this.artist,
          this.illustrationId,
          this.frame,
          this.fullArt,
          this.watermark,
          this.borderColor,
          this.storySpotlightNumber,
          this.storySpotlightUri,
          this.timeshifted,
          this.colorshifted,
          this.futureshifted,
          this.purchaseUris,
          this.relatedUris);
    }
    @Nullable List<Colors> newValue = Arrays.asList(elements) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(elements), true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        newValue,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of colorIdentity elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withColorIdentity(@Nullable Iterable<? extends Colors> elements) {
    if (this.colorIdentity == elements) return this;
    @Nullable List<Colors> newValue = elements == null ? null : createUnmodifiableList(false, createSafeList(elements, true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        newValue,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getAllParts() allParts}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withAllParts(@Nullable RelatedCards... elements) {
    if (elements == null) {
      return new ImmutableCard(
          this.id,
          this.name,
          this.oracleId,
          this.multiverseIds,
          this.mtgoId,
          this.arenaId,
          this.mtgoFoilId,
          this.uri,
          this.scryfallUri,
          this.printsSearchUri,
          this.rulingsUri,
          this.layout,
          this.cmc,
          this.typeLine,
          this.oracleText,
          this.manaCost,
          this.power,
          this.toughness,
          this.loyalty,
          this.lifeModifier,
          this.handModifier,
          this.colors,
          this.colorIndicator,
          this.colorIdentity,
          null,
          this.cardFaces,
          this.legalities,
          this.reserved,
          this.edhrecRank,
          this.setCode,
          this.setName,
          this.collectorNumber,
          this.setSearchUri,
          this.scryfallSetUri,
          this.imageUris,
          this.highresImage,
          this.reprint,
          this.digital,
          this.rarity,
          this.flavorText,
          this.artist,
          this.illustrationId,
          this.frame,
          this.fullArt,
          this.watermark,
          this.borderColor,
          this.storySpotlightNumber,
          this.storySpotlightUri,
          this.timeshifted,
          this.colorshifted,
          this.futureshifted,
          this.purchaseUris,
          this.relatedUris);
    }
    @Nullable List<RelatedCards> newValue = Arrays.asList(elements) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(elements), true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        newValue,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getAllParts() allParts}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of allParts elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withAllParts(@Nullable Iterable<? extends RelatedCards> elements) {
    if (this.allParts == elements) return this;
    @Nullable List<RelatedCards> newValue = elements == null ? null : createUnmodifiableList(false, createSafeList(elements, true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        newValue,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withCardFaces(@Nullable CardFace... elements) {
    if (elements == null) {
      return new ImmutableCard(
          this.id,
          this.name,
          this.oracleId,
          this.multiverseIds,
          this.mtgoId,
          this.arenaId,
          this.mtgoFoilId,
          this.uri,
          this.scryfallUri,
          this.printsSearchUri,
          this.rulingsUri,
          this.layout,
          this.cmc,
          this.typeLine,
          this.oracleText,
          this.manaCost,
          this.power,
          this.toughness,
          this.loyalty,
          this.lifeModifier,
          this.handModifier,
          this.colors,
          this.colorIndicator,
          this.colorIdentity,
          this.allParts,
          null,
          this.legalities,
          this.reserved,
          this.edhrecRank,
          this.setCode,
          this.setName,
          this.collectorNumber,
          this.setSearchUri,
          this.scryfallSetUri,
          this.imageUris,
          this.highresImage,
          this.reprint,
          this.digital,
          this.rarity,
          this.flavorText,
          this.artist,
          this.illustrationId,
          this.frame,
          this.fullArt,
          this.watermark,
          this.borderColor,
          this.storySpotlightNumber,
          this.storySpotlightUri,
          this.timeshifted,
          this.colorshifted,
          this.futureshifted,
          this.purchaseUris,
          this.relatedUris);
    }
    @Nullable List<CardFace> newValue = Arrays.asList(elements) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(elements), true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        newValue,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of cardFaces elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withCardFaces(@Nullable Iterable<? extends CardFace> elements) {
    if (this.cardFaces == elements) return this;
    @Nullable List<CardFace> newValue = elements == null ? null : createUnmodifiableList(false, createSafeList(elements, true, false));
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        newValue,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getLegalities() legalities} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for legalities (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withLegalities(@Nullable Legality value) {
    if (this.legalities == value) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        value,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getReserved() reserved} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for reserved (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withReserved(@Nullable Boolean value) {
    if (Objects.equals(this.reserved, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        value,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getEdhrecRank() edhrecRank} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for edhrecRank (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withEdhrecRank(@Nullable Integer value) {
    if (Objects.equals(this.edhrecRank, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        value,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getSetCode() setCode} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for setCode (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withSetCode(@Nullable String value) {
    if (Objects.equals(this.setCode, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        value,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getSetName() setName} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for setName (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withSetName(@Nullable String value) {
    if (Objects.equals(this.setName, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        value,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getCollectorNumber() collectorNumber} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for collectorNumber (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withCollectorNumber(@Nullable String value) {
    if (Objects.equals(this.collectorNumber, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        value,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getSetSearchUri() setSearchUri} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for setSearchUri (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withSetSearchUri(@Nullable String value) {
    if (Objects.equals(this.setSearchUri, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        value,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getScryfallSetUri() scryfallSetUri} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for scryfallSetUri (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withScryfallSetUri(@Nullable String value) {
    if (Objects.equals(this.scryfallSetUri, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        value,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getImageUris() imageUris} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for imageUris (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withImageUris(@Nullable ImageUri value) {
    if (this.imageUris == value) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        value,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getHighresImage() highresImage} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for highresImage (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withHighresImage(@Nullable Boolean value) {
    if (Objects.equals(this.highresImage, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        value,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getReprint() reprint} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for reprint (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withReprint(@Nullable Boolean value) {
    if (Objects.equals(this.reprint, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        value,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getDigital() digital} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for digital (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withDigital(@Nullable Boolean value) {
    if (Objects.equals(this.digital, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        value,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getRarity() rarity} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for rarity (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withRarity(@Nullable Rarity value) {
    if (this.rarity == value) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        value,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getFlavorText() flavorText} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for flavorText (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withFlavorText(@Nullable String value) {
    if (Objects.equals(this.flavorText, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        value,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getArtist() artist} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for artist (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withArtist(@Nullable String value) {
    if (Objects.equals(this.artist, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        value,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getIllustrationId() illustrationId} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for illustrationId (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withIllustrationId(@Nullable UUID value) {
    if (this.illustrationId == value) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        value,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getFrame() frame} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for frame (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withFrame(@Nullable String value) {
    if (Objects.equals(this.frame, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        value,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getFullArt() fullArt} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for fullArt (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withFullArt(@Nullable Boolean value) {
    if (Objects.equals(this.fullArt, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        value,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getWatermark() watermark} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for watermark (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withWatermark(@Nullable String value) {
    if (Objects.equals(this.watermark, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        value,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getBorderColor() borderColor} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for borderColor (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withBorderColor(@Nullable BorderColors value) {
    if (this.borderColor == value) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        value,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getStorySpotlightNumber() storySpotlightNumber} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for storySpotlightNumber (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withStorySpotlightNumber(@Nullable Integer value) {
    if (Objects.equals(this.storySpotlightNumber, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        value,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getStorySpotlightUri() storySpotlightUri} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for storySpotlightUri (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withStorySpotlightUri(@Nullable String value) {
    if (Objects.equals(this.storySpotlightUri, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        value,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getTimeshifted() timeshifted} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for timeshifted (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withTimeshifted(@Nullable Boolean value) {
    if (Objects.equals(this.timeshifted, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        value,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getColorshifted() colorshifted} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for colorshifted (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withColorshifted(@Nullable Boolean value) {
    if (Objects.equals(this.colorshifted, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        value,
        this.futureshifted,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link com.alver.fatefall.api.base.Card#getFutureshifted() futureshifted} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for futureshifted (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableCard withFutureshifted(@Nullable Boolean value) {
    if (Objects.equals(this.futureshifted, value)) return this;
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        value,
        this.purchaseUris,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by replacing the {@link com.alver.fatefall.api.base.Card#getPurchaseUris() purchaseUris} map with the specified map.
   * Nulls are not permitted as keys or values.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param entries The entries to be added to the purchaseUris map
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withPurchaseUris(@Nullable Map<String, ? extends String> entries) {
    if (this.purchaseUris == entries) return this;
    @Nullable Map<String, String> newValue = entries == null ? null : createUnmodifiableMap(true, false, entries);
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        newValue,
        this.relatedUris);
  }

  /**
   * Copy the current immutable object by replacing the {@link com.alver.fatefall.api.base.Card#getRelatedUris() relatedUris} map with the specified map.
   * Nulls are not permitted as keys or values.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param entries The entries to be added to the relatedUris map
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withRelatedUris(@Nullable Map<String, ? extends String> entries) {
    if (this.relatedUris == entries) return this;
    @Nullable Map<String, String> newValue = entries == null ? null : createUnmodifiableMap(true, false, entries);
    return new ImmutableCard(
        this.id,
        this.name,
        this.oracleId,
        this.multiverseIds,
        this.mtgoId,
        this.arenaId,
        this.mtgoFoilId,
        this.uri,
        this.scryfallUri,
        this.printsSearchUri,
        this.rulingsUri,
        this.layout,
        this.cmc,
        this.typeLine,
        this.oracleText,
        this.manaCost,
        this.power,
        this.toughness,
        this.loyalty,
        this.lifeModifier,
        this.handModifier,
        this.colors,
        this.colorIndicator,
        this.colorIdentity,
        this.allParts,
        this.cardFaces,
        this.legalities,
        this.reserved,
        this.edhrecRank,
        this.setCode,
        this.setName,
        this.collectorNumber,
        this.setSearchUri,
        this.scryfallSetUri,
        this.imageUris,
        this.highresImage,
        this.reprint,
        this.digital,
        this.rarity,
        this.flavorText,
        this.artist,
        this.illustrationId,
        this.frame,
        this.fullArt,
        this.watermark,
        this.borderColor,
        this.storySpotlightNumber,
        this.storySpotlightUri,
        this.timeshifted,
        this.colorshifted,
        this.futureshifted,
        this.purchaseUris,
        newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableCard} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableCard
        && equalTo(0, (ImmutableCard) another);
  }

  private boolean equalTo(int synthetic, ImmutableCard another) {
    return Objects.equals(id, another.id)
        && Objects.equals(name, another.name)
        && Objects.equals(oracleId, another.oracleId)
        && Objects.equals(multiverseIds, another.multiverseIds)
        && Objects.equals(mtgoId, another.mtgoId)
        && Objects.equals(arenaId, another.arenaId)
        && Objects.equals(mtgoFoilId, another.mtgoFoilId)
        && Objects.equals(uri, another.uri)
        && Objects.equals(scryfallUri, another.scryfallUri)
        && Objects.equals(printsSearchUri, another.printsSearchUri)
        && Objects.equals(rulingsUri, another.rulingsUri)
        && Objects.equals(layout, another.layout)
        && Objects.equals(cmc, another.cmc)
        && Objects.equals(typeLine, another.typeLine)
        && Objects.equals(oracleText, another.oracleText)
        && Objects.equals(manaCost, another.manaCost)
        && Objects.equals(power, another.power)
        && Objects.equals(toughness, another.toughness)
        && Objects.equals(loyalty, another.loyalty)
        && Objects.equals(lifeModifier, another.lifeModifier)
        && Objects.equals(handModifier, another.handModifier)
        && Objects.equals(colors, another.colors)
        && Objects.equals(colorIndicator, another.colorIndicator)
        && Objects.equals(colorIdentity, another.colorIdentity)
        && Objects.equals(allParts, another.allParts)
        && Objects.equals(cardFaces, another.cardFaces)
        && Objects.equals(legalities, another.legalities)
        && Objects.equals(reserved, another.reserved)
        && Objects.equals(edhrecRank, another.edhrecRank)
        && Objects.equals(setCode, another.setCode)
        && Objects.equals(setName, another.setName)
        && Objects.equals(collectorNumber, another.collectorNumber)
        && Objects.equals(setSearchUri, another.setSearchUri)
        && Objects.equals(scryfallSetUri, another.scryfallSetUri)
        && Objects.equals(imageUris, another.imageUris)
        && Objects.equals(highresImage, another.highresImage)
        && Objects.equals(reprint, another.reprint)
        && Objects.equals(digital, another.digital)
        && Objects.equals(rarity, another.rarity)
        && Objects.equals(flavorText, another.flavorText)
        && Objects.equals(artist, another.artist)
        && Objects.equals(illustrationId, another.illustrationId)
        && Objects.equals(frame, another.frame)
        && Objects.equals(fullArt, another.fullArt)
        && Objects.equals(watermark, another.watermark)
        && Objects.equals(borderColor, another.borderColor)
        && Objects.equals(storySpotlightNumber, another.storySpotlightNumber)
        && Objects.equals(storySpotlightUri, another.storySpotlightUri)
        && Objects.equals(timeshifted, another.timeshifted)
        && Objects.equals(colorshifted, another.colorshifted)
        && Objects.equals(futureshifted, another.futureshifted)
        && Objects.equals(purchaseUris, another.purchaseUris)
        && Objects.equals(relatedUris, another.relatedUris);
  }

  /**
   * Computes a hash code from attributes: {@code id}, {@code name}, {@code oracleId}, {@code multiverseIds}, {@code mtgoId}, {@code arenaId}, {@code mtgoFoilId}, {@code uri}, {@code scryfallUri}, {@code printsSearchUri}, {@code rulingsUri}, {@code layout}, {@code cmc}, {@code typeLine}, {@code oracleText}, {@code manaCost}, {@code power}, {@code toughness}, {@code loyalty}, {@code lifeModifier}, {@code handModifier}, {@code colors}, {@code colorIndicator}, {@code colorIdentity}, {@code allParts}, {@code cardFaces}, {@code legalities}, {@code reserved}, {@code edhrecRank}, {@code setCode}, {@code setName}, {@code collectorNumber}, {@code setSearchUri}, {@code scryfallSetUri}, {@code imageUris}, {@code highresImage}, {@code reprint}, {@code digital}, {@code rarity}, {@code flavorText}, {@code artist}, {@code illustrationId}, {@code frame}, {@code fullArt}, {@code watermark}, {@code borderColor}, {@code storySpotlightNumber}, {@code storySpotlightUri}, {@code timeshifted}, {@code colorshifted}, {@code futureshifted}, {@code purchaseUris}, {@code relatedUris}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Objects.hashCode(id);
    h += (h << 5) + Objects.hashCode(name);
    h += (h << 5) + Objects.hashCode(oracleId);
    h += (h << 5) + Objects.hashCode(multiverseIds);
    h += (h << 5) + Objects.hashCode(mtgoId);
    h += (h << 5) + Objects.hashCode(arenaId);
    h += (h << 5) + Objects.hashCode(mtgoFoilId);
    h += (h << 5) + Objects.hashCode(uri);
    h += (h << 5) + Objects.hashCode(scryfallUri);
    h += (h << 5) + Objects.hashCode(printsSearchUri);
    h += (h << 5) + Objects.hashCode(rulingsUri);
    h += (h << 5) + Objects.hashCode(layout);
    h += (h << 5) + Objects.hashCode(cmc);
    h += (h << 5) + Objects.hashCode(typeLine);
    h += (h << 5) + Objects.hashCode(oracleText);
    h += (h << 5) + Objects.hashCode(manaCost);
    h += (h << 5) + Objects.hashCode(power);
    h += (h << 5) + Objects.hashCode(toughness);
    h += (h << 5) + Objects.hashCode(loyalty);
    h += (h << 5) + Objects.hashCode(lifeModifier);
    h += (h << 5) + Objects.hashCode(handModifier);
    h += (h << 5) + Objects.hashCode(colors);
    h += (h << 5) + Objects.hashCode(colorIndicator);
    h += (h << 5) + Objects.hashCode(colorIdentity);
    h += (h << 5) + Objects.hashCode(allParts);
    h += (h << 5) + Objects.hashCode(cardFaces);
    h += (h << 5) + Objects.hashCode(legalities);
    h += (h << 5) + Objects.hashCode(reserved);
    h += (h << 5) + Objects.hashCode(edhrecRank);
    h += (h << 5) + Objects.hashCode(setCode);
    h += (h << 5) + Objects.hashCode(setName);
    h += (h << 5) + Objects.hashCode(collectorNumber);
    h += (h << 5) + Objects.hashCode(setSearchUri);
    h += (h << 5) + Objects.hashCode(scryfallSetUri);
    h += (h << 5) + Objects.hashCode(imageUris);
    h += (h << 5) + Objects.hashCode(highresImage);
    h += (h << 5) + Objects.hashCode(reprint);
    h += (h << 5) + Objects.hashCode(digital);
    h += (h << 5) + Objects.hashCode(rarity);
    h += (h << 5) + Objects.hashCode(flavorText);
    h += (h << 5) + Objects.hashCode(artist);
    h += (h << 5) + Objects.hashCode(illustrationId);
    h += (h << 5) + Objects.hashCode(frame);
    h += (h << 5) + Objects.hashCode(fullArt);
    h += (h << 5) + Objects.hashCode(watermark);
    h += (h << 5) + Objects.hashCode(borderColor);
    h += (h << 5) + Objects.hashCode(storySpotlightNumber);
    h += (h << 5) + Objects.hashCode(storySpotlightUri);
    h += (h << 5) + Objects.hashCode(timeshifted);
    h += (h << 5) + Objects.hashCode(colorshifted);
    h += (h << 5) + Objects.hashCode(futureshifted);
    h += (h << 5) + Objects.hashCode(purchaseUris);
    h += (h << 5) + Objects.hashCode(relatedUris);
    return h;
  }

  /**
   * Prints the immutable value {@code Card} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Card{"
        + "id=" + id
        + ", name=" + name
        + ", oracleId=" + oracleId
        + ", multiverseIds=" + multiverseIds
        + ", mtgoId=" + mtgoId
        + ", arenaId=" + arenaId
        + ", mtgoFoilId=" + mtgoFoilId
        + ", uri=" + uri
        + ", scryfallUri=" + scryfallUri
        + ", printsSearchUri=" + printsSearchUri
        + ", rulingsUri=" + rulingsUri
        + ", layout=" + layout
        + ", cmc=" + cmc
        + ", typeLine=" + typeLine
        + ", oracleText=" + oracleText
        + ", manaCost=" + manaCost
        + ", power=" + power
        + ", toughness=" + toughness
        + ", loyalty=" + loyalty
        + ", lifeModifier=" + lifeModifier
        + ", handModifier=" + handModifier
        + ", colors=" + colors
        + ", colorIndicator=" + colorIndicator
        + ", colorIdentity=" + colorIdentity
        + ", allParts=" + allParts
        + ", cardFaces=" + cardFaces
        + ", legalities=" + legalities
        + ", reserved=" + reserved
        + ", edhrecRank=" + edhrecRank
        + ", setCode=" + setCode
        + ", setName=" + setName
        + ", collectorNumber=" + collectorNumber
        + ", setSearchUri=" + setSearchUri
        + ", scryfallSetUri=" + scryfallSetUri
        + ", imageUris=" + imageUris
        + ", highresImage=" + highresImage
        + ", reprint=" + reprint
        + ", digital=" + digital
        + ", rarity=" + rarity
        + ", flavorText=" + flavorText
        + ", artist=" + artist
        + ", illustrationId=" + illustrationId
        + ", frame=" + frame
        + ", fullArt=" + fullArt
        + ", watermark=" + watermark
        + ", borderColor=" + borderColor
        + ", storySpotlightNumber=" + storySpotlightNumber
        + ", storySpotlightUri=" + storySpotlightUri
        + ", timeshifted=" + timeshifted
        + ", colorshifted=" + colorshifted
        + ", futureshifted=" + futureshifted
        + ", purchaseUris=" + purchaseUris
        + ", relatedUris=" + relatedUris
        + "}";
  }

  /**
   * Creates an immutable copy of a {@link com.alver.fatefall.api.base.Card} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable Card instance
   */
  public static ImmutableCard copyOf(Card instance) {
    if (instance instanceof ImmutableCard) {
      return (ImmutableCard) instance;
    }
    return ImmutableCard.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableCard ImmutableCard}.
   * <pre>
   * ImmutableCard.builder()
   *    .id(UUID | null) // nullable {@link com.alver.fatefall.api.base.Card#getId() id}
   *    .name(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getName() name}
   *    .oracleId(UUID | null) // nullable {@link com.alver.fatefall.api.base.Card#getOracleId() oracleId}
   *    .multiverseIds(List&amp;lt;Integer&amp;gt; | null) // nullable {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds}
   *    .mtgoId(Integer | null) // nullable {@link com.alver.fatefall.api.base.Card#getMtgoId() mtgoId}
   *    .arenaId(Integer | null) // nullable {@link com.alver.fatefall.api.base.Card#getArenaId() arenaId}
   *    .mtgoFoilId(Integer | null) // nullable {@link com.alver.fatefall.api.base.Card#getMtgoFoilId() mtgoFoilId}
   *    .uri(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getUri() uri}
   *    .scryfallUri(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getScryfallUri() scryfallUri}
   *    .printsSearchUri(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getPrintsSearchUri() printsSearchUri}
   *    .rulingsUri(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getRulingsUri() rulingsUri}
   *    .layout(com.alver.fatefall.api.models.Layouts | null) // nullable {@link com.alver.fatefall.api.base.Card#getLayout() layout}
   *    .cmc(Double | null) // nullable {@link com.alver.fatefall.api.base.Card#getCmc() cmc}
   *    .typeLine(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getTypeLine() typeLine}
   *    .oracleText(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getOracleText() oracleText}
   *    .manaCost(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getManaCost() manaCost}
   *    .power(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getPower() power}
   *    .toughness(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getToughness() toughness}
   *    .loyalty(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getLoyalty() loyalty}
   *    .lifeModifier(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getLifeModifier() lifeModifier}
   *    .handModifier(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getHandModifier() handModifier}
   *    .colors(List&amp;lt;com.alver.fatefall.api.models.Colors&amp;gt; | null) // nullable {@link com.alver.fatefall.api.base.Card#getColors() colors}
   *    .colorIndicator(List&amp;lt;com.alver.fatefall.api.models.Colors&amp;gt; | null) // nullable {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator}
   *    .colorIdentity(List&amp;lt;com.alver.fatefall.api.models.Colors&amp;gt; | null) // nullable {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity}
   *    .allParts(List&amp;lt;com.alver.fatefall.api.models.RelatedCards&amp;gt; | null) // nullable {@link com.alver.fatefall.api.base.Card#getAllParts() allParts}
   *    .cardFaces(List&amp;lt;com.alver.fatefall.api.models.CardFace&amp;gt; | null) // nullable {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces}
   *    .legalities(com.alver.fatefall.api.models.Legality | null) // nullable {@link com.alver.fatefall.api.base.Card#getLegalities() legalities}
   *    .reserved(Boolean | null) // nullable {@link com.alver.fatefall.api.base.Card#getReserved() reserved}
   *    .edhrecRank(Integer | null) // nullable {@link com.alver.fatefall.api.base.Card#getEdhrecRank() edhrecRank}
   *    .setCode(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getSetCode() setCode}
   *    .setName(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getSetName() setName}
   *    .collectorNumber(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getCollectorNumber() collectorNumber}
   *    .setSearchUri(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getSetSearchUri() setSearchUri}
   *    .scryfallSetUri(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getScryfallSetUri() scryfallSetUri}
   *    .imageUris(com.alver.fatefall.api.models.ImageUri | null) // nullable {@link com.alver.fatefall.api.base.Card#getImageUris() imageUris}
   *    .highresImage(Boolean | null) // nullable {@link com.alver.fatefall.api.base.Card#getHighresImage() highresImage}
   *    .reprint(Boolean | null) // nullable {@link com.alver.fatefall.api.base.Card#getReprint() reprint}
   *    .digital(Boolean | null) // nullable {@link com.alver.fatefall.api.base.Card#getDigital() digital}
   *    .rarity(com.alver.fatefall.api.models.Rarity | null) // nullable {@link com.alver.fatefall.api.base.Card#getRarity() rarity}
   *    .flavorText(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getFlavorText() flavorText}
   *    .artist(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getArtist() artist}
   *    .illustrationId(UUID | null) // nullable {@link com.alver.fatefall.api.base.Card#getIllustrationId() illustrationId}
   *    .frame(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getFrame() frame}
   *    .fullArt(Boolean | null) // nullable {@link com.alver.fatefall.api.base.Card#getFullArt() fullArt}
   *    .watermark(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getWatermark() watermark}
   *    .borderColor(com.alver.fatefall.api.models.BorderColors | null) // nullable {@link com.alver.fatefall.api.base.Card#getBorderColor() borderColor}
   *    .storySpotlightNumber(Integer | null) // nullable {@link com.alver.fatefall.api.base.Card#getStorySpotlightNumber() storySpotlightNumber}
   *    .storySpotlightUri(String | null) // nullable {@link com.alver.fatefall.api.base.Card#getStorySpotlightUri() storySpotlightUri}
   *    .timeshifted(Boolean | null) // nullable {@link com.alver.fatefall.api.base.Card#getTimeshifted() timeshifted}
   *    .colorshifted(Boolean | null) // nullable {@link com.alver.fatefall.api.base.Card#getColorshifted() colorshifted}
   *    .futureshifted(Boolean | null) // nullable {@link com.alver.fatefall.api.base.Card#getFutureshifted() futureshifted}
   *    .purchaseUris(Map&amp;lt;String, String&amp;gt; | null) // nullable {@link com.alver.fatefall.api.base.Card#getPurchaseUris() purchaseUris}
   *    .relatedUris(Map&amp;lt;String, String&amp;gt; | null) // nullable {@link com.alver.fatefall.api.base.Card#getRelatedUris() relatedUris}
   *    .build();
   * </pre>
   * @return A new ImmutableCard builder
   */
  public static ImmutableCard.Builder builder() {
    return new ImmutableCard.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableCard ImmutableCard}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "com.alver.fatefall.api.base.Card", generator = "Immutables")
  public static final class Builder {
    private UUID id;
    private String name;
    private UUID oracleId;
    private List<Integer> multiverseIds = null;
    private Integer mtgoId;
    private Integer arenaId;
    private Integer mtgoFoilId;
    private String uri;
    private String scryfallUri;
    private String printsSearchUri;
    private String rulingsUri;
    private Layouts layout;
    private Double cmc;
    private String typeLine;
    private String oracleText;
    private String manaCost;
    private String power;
    private String toughness;
    private String loyalty;
    private String lifeModifier;
    private String handModifier;
    private List<Colors> colors = null;
    private List<Colors> colorIndicator = null;
    private List<Colors> colorIdentity = null;
    private List<RelatedCards> allParts = null;
    private List<CardFace> cardFaces = null;
    private Legality legalities;
    private Boolean reserved;
    private Integer edhrecRank;
    private String setCode;
    private String setName;
    private String collectorNumber;
    private String setSearchUri;
    private String scryfallSetUri;
    private ImageUri imageUris;
    private Boolean highresImage;
    private Boolean reprint;
    private Boolean digital;
    private Rarity rarity;
    private String flavorText;
    private String artist;
    private UUID illustrationId;
    private String frame;
    private Boolean fullArt;
    private String watermark;
    private BorderColors borderColor;
    private Integer storySpotlightNumber;
    private String storySpotlightUri;
    private Boolean timeshifted;
    private Boolean colorshifted;
    private Boolean futureshifted;
    private Map<String, String> purchaseUris = null;
    private Map<String, String> relatedUris = null;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code ModifiableCard} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(ModifiableCard instance) {
      Objects.requireNonNull(instance, "instance");
      @Nullable UUID idValue = instance.getId();
      if (idValue != null) {
        id(idValue);
      }
      @Nullable String nameValue = instance.getName();
      if (nameValue != null) {
        name(nameValue);
      }
      @Nullable UUID oracleIdValue = instance.getOracleId();
      if (oracleIdValue != null) {
        oracleId(oracleIdValue);
      }
      @Nullable List<Integer> multiverseIdsValue = instance.getMultiverseIds();
      if (multiverseIdsValue != null) {
        addAllMultiverseIds(multiverseIdsValue);
      }
      @Nullable Integer mtgoIdValue = instance.getMtgoId();
      if (mtgoIdValue != null) {
        mtgoId(mtgoIdValue);
      }
      @Nullable Integer arenaIdValue = instance.getArenaId();
      if (arenaIdValue != null) {
        arenaId(arenaIdValue);
      }
      @Nullable Integer mtgoFoilIdValue = instance.getMtgoFoilId();
      if (mtgoFoilIdValue != null) {
        mtgoFoilId(mtgoFoilIdValue);
      }
      @Nullable String uriValue = instance.getUri();
      if (uriValue != null) {
        uri(uriValue);
      }
      @Nullable String scryfallUriValue = instance.getScryfallUri();
      if (scryfallUriValue != null) {
        scryfallUri(scryfallUriValue);
      }
      @Nullable String printsSearchUriValue = instance.getPrintsSearchUri();
      if (printsSearchUriValue != null) {
        printsSearchUri(printsSearchUriValue);
      }
      @Nullable String rulingsUriValue = instance.getRulingsUri();
      if (rulingsUriValue != null) {
        rulingsUri(rulingsUriValue);
      }
      @Nullable Layouts layoutValue = instance.getLayout();
      if (layoutValue != null) {
        layout(layoutValue);
      }
      @Nullable Double cmcValue = instance.getCmc();
      if (cmcValue != null) {
        cmc(cmcValue);
      }
      @Nullable String typeLineValue = instance.getTypeLine();
      if (typeLineValue != null) {
        typeLine(typeLineValue);
      }
      @Nullable String oracleTextValue = instance.getOracleText();
      if (oracleTextValue != null) {
        oracleText(oracleTextValue);
      }
      @Nullable String manaCostValue = instance.getManaCost();
      if (manaCostValue != null) {
        manaCost(manaCostValue);
      }
      @Nullable String powerValue = instance.getPower();
      if (powerValue != null) {
        power(powerValue);
      }
      @Nullable String toughnessValue = instance.getToughness();
      if (toughnessValue != null) {
        toughness(toughnessValue);
      }
      @Nullable String loyaltyValue = instance.getLoyalty();
      if (loyaltyValue != null) {
        loyalty(loyaltyValue);
      }
      @Nullable String lifeModifierValue = instance.getLifeModifier();
      if (lifeModifierValue != null) {
        lifeModifier(lifeModifierValue);
      }
      @Nullable String handModifierValue = instance.getHandModifier();
      if (handModifierValue != null) {
        handModifier(handModifierValue);
      }
      @Nullable List<Colors> colorsValue = instance.getColors();
      if (colorsValue != null) {
        addAllColors(colorsValue);
      }
      @Nullable List<Colors> colorIndicatorValue = instance.getColorIndicator();
      if (colorIndicatorValue != null) {
        addAllColorIndicator(colorIndicatorValue);
      }
      @Nullable List<Colors> colorIdentityValue = instance.getColorIdentity();
      if (colorIdentityValue != null) {
        addAllColorIdentity(colorIdentityValue);
      }
      @Nullable List<RelatedCards> allPartsValue = instance.getAllParts();
      if (allPartsValue != null) {
        addAllAllParts(allPartsValue);
      }
      @Nullable List<CardFace> cardFacesValue = instance.getCardFaces();
      if (cardFacesValue != null) {
        addAllCardFaces(cardFacesValue);
      }
      @Nullable Legality legalitiesValue = instance.getLegalities();
      if (legalitiesValue != null) {
        legalities(legalitiesValue);
      }
      @Nullable Boolean reservedValue = instance.getReserved();
      if (reservedValue != null) {
        reserved(reservedValue);
      }
      @Nullable Integer edhrecRankValue = instance.getEdhrecRank();
      if (edhrecRankValue != null) {
        edhrecRank(edhrecRankValue);
      }
      @Nullable String setCodeValue = instance.getSetCode();
      if (setCodeValue != null) {
        setCode(setCodeValue);
      }
      @Nullable String setNameValue = instance.getSetName();
      if (setNameValue != null) {
        setName(setNameValue);
      }
      @Nullable String collectorNumberValue = instance.getCollectorNumber();
      if (collectorNumberValue != null) {
        collectorNumber(collectorNumberValue);
      }
      @Nullable String setSearchUriValue = instance.getSetSearchUri();
      if (setSearchUriValue != null) {
        setSearchUri(setSearchUriValue);
      }
      @Nullable String scryfallSetUriValue = instance.getScryfallSetUri();
      if (scryfallSetUriValue != null) {
        scryfallSetUri(scryfallSetUriValue);
      }
      @Nullable ImageUri imageUrisValue = instance.getImageUris();
      if (imageUrisValue != null) {
        imageUris(imageUrisValue);
      }
      @Nullable Boolean highresImageValue = instance.getHighresImage();
      if (highresImageValue != null) {
        highresImage(highresImageValue);
      }
      @Nullable Boolean reprintValue = instance.getReprint();
      if (reprintValue != null) {
        reprint(reprintValue);
      }
      @Nullable Boolean digitalValue = instance.getDigital();
      if (digitalValue != null) {
        digital(digitalValue);
      }
      @Nullable Rarity rarityValue = instance.getRarity();
      if (rarityValue != null) {
        rarity(rarityValue);
      }
      @Nullable String flavorTextValue = instance.getFlavorText();
      if (flavorTextValue != null) {
        flavorText(flavorTextValue);
      }
      @Nullable String artistValue = instance.getArtist();
      if (artistValue != null) {
        artist(artistValue);
      }
      @Nullable UUID illustrationIdValue = instance.getIllustrationId();
      if (illustrationIdValue != null) {
        illustrationId(illustrationIdValue);
      }
      @Nullable String frameValue = instance.getFrame();
      if (frameValue != null) {
        frame(frameValue);
      }
      @Nullable Boolean fullArtValue = instance.getFullArt();
      if (fullArtValue != null) {
        fullArt(fullArtValue);
      }
      @Nullable String watermarkValue = instance.getWatermark();
      if (watermarkValue != null) {
        watermark(watermarkValue);
      }
      @Nullable BorderColors borderColorValue = instance.getBorderColor();
      if (borderColorValue != null) {
        borderColor(borderColorValue);
      }
      @Nullable Integer storySpotlightNumberValue = instance.getStorySpotlightNumber();
      if (storySpotlightNumberValue != null) {
        storySpotlightNumber(storySpotlightNumberValue);
      }
      @Nullable String storySpotlightUriValue = instance.getStorySpotlightUri();
      if (storySpotlightUriValue != null) {
        storySpotlightUri(storySpotlightUriValue);
      }
      @Nullable Boolean timeshiftedValue = instance.getTimeshifted();
      if (timeshiftedValue != null) {
        timeshifted(timeshiftedValue);
      }
      @Nullable Boolean colorshiftedValue = instance.getColorshifted();
      if (colorshiftedValue != null) {
        colorshifted(colorshiftedValue);
      }
      @Nullable Boolean futureshiftedValue = instance.getFutureshifted();
      if (futureshiftedValue != null) {
        futureshifted(futureshiftedValue);
      }
      @Nullable Map<String, String> purchaseUrisValue = instance.getPurchaseUris();
      if (purchaseUrisValue != null) {
        putAllPurchaseUris(purchaseUrisValue);
      }
      @Nullable Map<String, String> relatedUrisValue = instance.getRelatedUris();
      if (relatedUrisValue != null) {
        putAllRelatedUris(relatedUrisValue);
      }
      return this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code Card} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * Collection elements and entries will be added, not replaced.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(Card instance) {
      Objects.requireNonNull(instance, "instance");
      if (instance instanceof ModifiableCard) {
        return from((ModifiableCard) instance);
      }
      @Nullable UUID idValue = instance.getId();
      if (idValue != null) {
        id(idValue);
      }
      @Nullable String nameValue = instance.getName();
      if (nameValue != null) {
        name(nameValue);
      }
      @Nullable UUID oracleIdValue = instance.getOracleId();
      if (oracleIdValue != null) {
        oracleId(oracleIdValue);
      }
      @Nullable List<Integer> multiverseIdsValue = instance.getMultiverseIds();
      if (multiverseIdsValue != null) {
        addAllMultiverseIds(multiverseIdsValue);
      }
      @Nullable Integer mtgoIdValue = instance.getMtgoId();
      if (mtgoIdValue != null) {
        mtgoId(mtgoIdValue);
      }
      @Nullable Integer arenaIdValue = instance.getArenaId();
      if (arenaIdValue != null) {
        arenaId(arenaIdValue);
      }
      @Nullable Integer mtgoFoilIdValue = instance.getMtgoFoilId();
      if (mtgoFoilIdValue != null) {
        mtgoFoilId(mtgoFoilIdValue);
      }
      @Nullable String uriValue = instance.getUri();
      if (uriValue != null) {
        uri(uriValue);
      }
      @Nullable String scryfallUriValue = instance.getScryfallUri();
      if (scryfallUriValue != null) {
        scryfallUri(scryfallUriValue);
      }
      @Nullable String printsSearchUriValue = instance.getPrintsSearchUri();
      if (printsSearchUriValue != null) {
        printsSearchUri(printsSearchUriValue);
      }
      @Nullable String rulingsUriValue = instance.getRulingsUri();
      if (rulingsUriValue != null) {
        rulingsUri(rulingsUriValue);
      }
      @Nullable Layouts layoutValue = instance.getLayout();
      if (layoutValue != null) {
        layout(layoutValue);
      }
      @Nullable Double cmcValue = instance.getCmc();
      if (cmcValue != null) {
        cmc(cmcValue);
      }
      @Nullable String typeLineValue = instance.getTypeLine();
      if (typeLineValue != null) {
        typeLine(typeLineValue);
      }
      @Nullable String oracleTextValue = instance.getOracleText();
      if (oracleTextValue != null) {
        oracleText(oracleTextValue);
      }
      @Nullable String manaCostValue = instance.getManaCost();
      if (manaCostValue != null) {
        manaCost(manaCostValue);
      }
      @Nullable String powerValue = instance.getPower();
      if (powerValue != null) {
        power(powerValue);
      }
      @Nullable String toughnessValue = instance.getToughness();
      if (toughnessValue != null) {
        toughness(toughnessValue);
      }
      @Nullable String loyaltyValue = instance.getLoyalty();
      if (loyaltyValue != null) {
        loyalty(loyaltyValue);
      }
      @Nullable String lifeModifierValue = instance.getLifeModifier();
      if (lifeModifierValue != null) {
        lifeModifier(lifeModifierValue);
      }
      @Nullable String handModifierValue = instance.getHandModifier();
      if (handModifierValue != null) {
        handModifier(handModifierValue);
      }
      @Nullable List<Colors> colorsValue = instance.getColors();
      if (colorsValue != null) {
        addAllColors(colorsValue);
      }
      @Nullable List<Colors> colorIndicatorValue = instance.getColorIndicator();
      if (colorIndicatorValue != null) {
        addAllColorIndicator(colorIndicatorValue);
      }
      @Nullable List<Colors> colorIdentityValue = instance.getColorIdentity();
      if (colorIdentityValue != null) {
        addAllColorIdentity(colorIdentityValue);
      }
      @Nullable List<RelatedCards> allPartsValue = instance.getAllParts();
      if (allPartsValue != null) {
        addAllAllParts(allPartsValue);
      }
      @Nullable List<CardFace> cardFacesValue = instance.getCardFaces();
      if (cardFacesValue != null) {
        addAllCardFaces(cardFacesValue);
      }
      @Nullable Legality legalitiesValue = instance.getLegalities();
      if (legalitiesValue != null) {
        legalities(legalitiesValue);
      }
      @Nullable Boolean reservedValue = instance.getReserved();
      if (reservedValue != null) {
        reserved(reservedValue);
      }
      @Nullable Integer edhrecRankValue = instance.getEdhrecRank();
      if (edhrecRankValue != null) {
        edhrecRank(edhrecRankValue);
      }
      @Nullable String setCodeValue = instance.getSetCode();
      if (setCodeValue != null) {
        setCode(setCodeValue);
      }
      @Nullable String setNameValue = instance.getSetName();
      if (setNameValue != null) {
        setName(setNameValue);
      }
      @Nullable String collectorNumberValue = instance.getCollectorNumber();
      if (collectorNumberValue != null) {
        collectorNumber(collectorNumberValue);
      }
      @Nullable String setSearchUriValue = instance.getSetSearchUri();
      if (setSearchUriValue != null) {
        setSearchUri(setSearchUriValue);
      }
      @Nullable String scryfallSetUriValue = instance.getScryfallSetUri();
      if (scryfallSetUriValue != null) {
        scryfallSetUri(scryfallSetUriValue);
      }
      @Nullable ImageUri imageUrisValue = instance.getImageUris();
      if (imageUrisValue != null) {
        imageUris(imageUrisValue);
      }
      @Nullable Boolean highresImageValue = instance.getHighresImage();
      if (highresImageValue != null) {
        highresImage(highresImageValue);
      }
      @Nullable Boolean reprintValue = instance.getReprint();
      if (reprintValue != null) {
        reprint(reprintValue);
      }
      @Nullable Boolean digitalValue = instance.getDigital();
      if (digitalValue != null) {
        digital(digitalValue);
      }
      @Nullable Rarity rarityValue = instance.getRarity();
      if (rarityValue != null) {
        rarity(rarityValue);
      }
      @Nullable String flavorTextValue = instance.getFlavorText();
      if (flavorTextValue != null) {
        flavorText(flavorTextValue);
      }
      @Nullable String artistValue = instance.getArtist();
      if (artistValue != null) {
        artist(artistValue);
      }
      @Nullable UUID illustrationIdValue = instance.getIllustrationId();
      if (illustrationIdValue != null) {
        illustrationId(illustrationIdValue);
      }
      @Nullable String frameValue = instance.getFrame();
      if (frameValue != null) {
        frame(frameValue);
      }
      @Nullable Boolean fullArtValue = instance.getFullArt();
      if (fullArtValue != null) {
        fullArt(fullArtValue);
      }
      @Nullable String watermarkValue = instance.getWatermark();
      if (watermarkValue != null) {
        watermark(watermarkValue);
      }
      @Nullable BorderColors borderColorValue = instance.getBorderColor();
      if (borderColorValue != null) {
        borderColor(borderColorValue);
      }
      @Nullable Integer storySpotlightNumberValue = instance.getStorySpotlightNumber();
      if (storySpotlightNumberValue != null) {
        storySpotlightNumber(storySpotlightNumberValue);
      }
      @Nullable String storySpotlightUriValue = instance.getStorySpotlightUri();
      if (storySpotlightUriValue != null) {
        storySpotlightUri(storySpotlightUriValue);
      }
      @Nullable Boolean timeshiftedValue = instance.getTimeshifted();
      if (timeshiftedValue != null) {
        timeshifted(timeshiftedValue);
      }
      @Nullable Boolean colorshiftedValue = instance.getColorshifted();
      if (colorshiftedValue != null) {
        colorshifted(colorshiftedValue);
      }
      @Nullable Boolean futureshiftedValue = instance.getFutureshifted();
      if (futureshiftedValue != null) {
        futureshifted(futureshiftedValue);
      }
      @Nullable Map<String, String> purchaseUrisValue = instance.getPurchaseUris();
      if (purchaseUrisValue != null) {
        putAllPurchaseUris(purchaseUrisValue);
      }
      @Nullable Map<String, String> relatedUrisValue = instance.getRelatedUris();
      if (relatedUrisValue != null) {
        putAllRelatedUris(relatedUrisValue);
      }
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getId() id} attribute.
     * @param id The value for id (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("id")
    public final Builder id(@Nullable UUID id) {
      this.id = id;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getName() name} attribute.
     * @param name The value for name (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("name")
    public final Builder name(@Nullable String name) {
      this.name = name;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getOracleId() oracleId} attribute.
     * @param oracleId The value for oracleId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("oracleId")
    public final Builder oracleId(@Nullable UUID oracleId) {
      this.oracleId = oracleId;
      return this;
    }

    /**
     * Adds one element to {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds} list.
     * @param element A multiverseIds element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addMultiverseIds(int element) {
      if (this.multiverseIds == null) {
        this.multiverseIds = new ArrayList<Integer>();
      }
      this.multiverseIds.add(element);
      return this;
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds} list.
     * @param elements An array of multiverseIds elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addMultiverseIds(int... elements) {
      if (this.multiverseIds == null) {
        this.multiverseIds = new ArrayList<Integer>();
      }
      for (int element : elements) {
        this.multiverseIds.add(element);
      }
      return this;
    }


    /**
     * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds} list.
     * @param elements An iterable of multiverseIds elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("multiverseIds")
    public final Builder multiverseIds(@Nullable Iterable<Integer> elements) {
      if (elements == null) {
        this.multiverseIds = null;
        return this;
      }
      this.multiverseIds = new ArrayList<Integer>();
      return addAllMultiverseIds(elements);
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds} list.
     * @param elements An iterable of multiverseIds elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllMultiverseIds(Iterable<Integer> elements) {
      Objects.requireNonNull(elements, "multiverseIds element");
      if (this.multiverseIds == null) {
        this.multiverseIds = new ArrayList<Integer>();
      }
      for (Integer element : elements) {
        this.multiverseIds.add(Objects.requireNonNull(element, "multiverseIds element"));
      }
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getMtgoId() mtgoId} attribute.
     * @param mtgoId The value for mtgoId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("mtgoId")
    public final Builder mtgoId(@Nullable Integer mtgoId) {
      this.mtgoId = mtgoId;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getArenaId() arenaId} attribute.
     * @param arenaId The value for arenaId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("arenaId")
    public final Builder arenaId(@Nullable Integer arenaId) {
      this.arenaId = arenaId;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getMtgoFoilId() mtgoFoilId} attribute.
     * @param mtgoFoilId The value for mtgoFoilId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("mtgoFoilId")
    public final Builder mtgoFoilId(@Nullable Integer mtgoFoilId) {
      this.mtgoFoilId = mtgoFoilId;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getUri() uri} attribute.
     * @param uri The value for uri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("uri")
    public final Builder uri(@Nullable String uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getScryfallUri() scryfallUri} attribute.
     * @param scryfallUri The value for scryfallUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("scryfallUri")
    public final Builder scryfallUri(@Nullable String scryfallUri) {
      this.scryfallUri = scryfallUri;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getPrintsSearchUri() printsSearchUri} attribute.
     * @param printsSearchUri The value for printsSearchUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("printsSearchUri")
    public final Builder printsSearchUri(@Nullable String printsSearchUri) {
      this.printsSearchUri = printsSearchUri;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getRulingsUri() rulingsUri} attribute.
     * @param rulingsUri The value for rulingsUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("rulingsUri")
    public final Builder rulingsUri(@Nullable String rulingsUri) {
      this.rulingsUri = rulingsUri;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getLayout() layout} attribute.
     * @param layout The value for layout (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("layout")
    public final Builder layout(@Nullable Layouts layout) {
      this.layout = layout;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getCmc() cmc} attribute.
     * @param cmc The value for cmc (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("cmc")
    public final Builder cmc(@Nullable Double cmc) {
      this.cmc = cmc;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getTypeLine() typeLine} attribute.
     * @param typeLine The value for typeLine (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("typeLine")
    public final Builder typeLine(@Nullable String typeLine) {
      this.typeLine = typeLine;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getOracleText() oracleText} attribute.
     * @param oracleText The value for oracleText (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("oracleText")
    public final Builder oracleText(@Nullable String oracleText) {
      this.oracleText = oracleText;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getManaCost() manaCost} attribute.
     * @param manaCost The value for manaCost (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("manaCost")
    public final Builder manaCost(@Nullable String manaCost) {
      this.manaCost = manaCost;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getPower() power} attribute.
     * @param power The value for power (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("power")
    public final Builder power(@Nullable String power) {
      this.power = power;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getToughness() toughness} attribute.
     * @param toughness The value for toughness (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("toughness")
    public final Builder toughness(@Nullable String toughness) {
      this.toughness = toughness;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getLoyalty() loyalty} attribute.
     * @param loyalty The value for loyalty (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("loyalty")
    public final Builder loyalty(@Nullable String loyalty) {
      this.loyalty = loyalty;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getLifeModifier() lifeModifier} attribute.
     * @param lifeModifier The value for lifeModifier (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("lifeModifier")
    public final Builder lifeModifier(@Nullable String lifeModifier) {
      this.lifeModifier = lifeModifier;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getHandModifier() handModifier} attribute.
     * @param handModifier The value for handModifier (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("handModifier")
    public final Builder handModifier(@Nullable String handModifier) {
      this.handModifier = handModifier;
      return this;
    }

    /**
     * Adds one element to {@link com.alver.fatefall.api.base.Card#getColors() colors} list.
     * @param element A colors element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColors(Colors element) {
      if (this.colors == null) {
        this.colors = new ArrayList<Colors>();
      }
      this.colors.add(Objects.requireNonNull(element, "colors element"));
      return this;
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getColors() colors} list.
     * @param elements An array of colors elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColors(Colors... elements) {
      if (this.colors == null) {
        this.colors = new ArrayList<Colors>();
      }
      for (Colors element : elements) {
        this.colors.add(Objects.requireNonNull(element, "colors element"));
      }
      return this;
    }


    /**
     * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getColors() colors} list.
     * @param elements An iterable of colors elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("colors")
    public final Builder colors(@Nullable Iterable<? extends Colors> elements) {
      if (elements == null) {
        this.colors = null;
        return this;
      }
      this.colors = new ArrayList<Colors>();
      return addAllColors(elements);
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getColors() colors} list.
     * @param elements An iterable of colors elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllColors(Iterable<? extends Colors> elements) {
      Objects.requireNonNull(elements, "colors element");
      if (this.colors == null) {
        this.colors = new ArrayList<Colors>();
      }
      for (Colors element : elements) {
        this.colors.add(Objects.requireNonNull(element, "colors element"));
      }
      return this;
    }

    /**
     * Adds one element to {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator} list.
     * @param element A colorIndicator element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColorIndicator(Colors element) {
      if (this.colorIndicator == null) {
        this.colorIndicator = new ArrayList<Colors>();
      }
      this.colorIndicator.add(Objects.requireNonNull(element, "colorIndicator element"));
      return this;
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator} list.
     * @param elements An array of colorIndicator elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColorIndicator(Colors... elements) {
      if (this.colorIndicator == null) {
        this.colorIndicator = new ArrayList<Colors>();
      }
      for (Colors element : elements) {
        this.colorIndicator.add(Objects.requireNonNull(element, "colorIndicator element"));
      }
      return this;
    }


    /**
     * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator} list.
     * @param elements An iterable of colorIndicator elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("colorIndicator")
    public final Builder colorIndicator(@Nullable Iterable<? extends Colors> elements) {
      if (elements == null) {
        this.colorIndicator = null;
        return this;
      }
      this.colorIndicator = new ArrayList<Colors>();
      return addAllColorIndicator(elements);
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator} list.
     * @param elements An iterable of colorIndicator elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllColorIndicator(Iterable<? extends Colors> elements) {
      Objects.requireNonNull(elements, "colorIndicator element");
      if (this.colorIndicator == null) {
        this.colorIndicator = new ArrayList<Colors>();
      }
      for (Colors element : elements) {
        this.colorIndicator.add(Objects.requireNonNull(element, "colorIndicator element"));
      }
      return this;
    }

    /**
     * Adds one element to {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity} list.
     * @param element A colorIdentity element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColorIdentity(Colors element) {
      if (this.colorIdentity == null) {
        this.colorIdentity = new ArrayList<Colors>();
      }
      this.colorIdentity.add(Objects.requireNonNull(element, "colorIdentity element"));
      return this;
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity} list.
     * @param elements An array of colorIdentity elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColorIdentity(Colors... elements) {
      if (this.colorIdentity == null) {
        this.colorIdentity = new ArrayList<Colors>();
      }
      for (Colors element : elements) {
        this.colorIdentity.add(Objects.requireNonNull(element, "colorIdentity element"));
      }
      return this;
    }


    /**
     * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity} list.
     * @param elements An iterable of colorIdentity elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("colorIdentity")
    public final Builder colorIdentity(@Nullable Iterable<? extends Colors> elements) {
      if (elements == null) {
        this.colorIdentity = null;
        return this;
      }
      this.colorIdentity = new ArrayList<Colors>();
      return addAllColorIdentity(elements);
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity} list.
     * @param elements An iterable of colorIdentity elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllColorIdentity(Iterable<? extends Colors> elements) {
      Objects.requireNonNull(elements, "colorIdentity element");
      if (this.colorIdentity == null) {
        this.colorIdentity = new ArrayList<Colors>();
      }
      for (Colors element : elements) {
        this.colorIdentity.add(Objects.requireNonNull(element, "colorIdentity element"));
      }
      return this;
    }

    /**
     * Adds one element to {@link com.alver.fatefall.api.base.Card#getAllParts() allParts} list.
     * @param element A allParts element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllParts(RelatedCards element) {
      if (this.allParts == null) {
        this.allParts = new ArrayList<RelatedCards>();
      }
      this.allParts.add(Objects.requireNonNull(element, "allParts element"));
      return this;
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getAllParts() allParts} list.
     * @param elements An array of allParts elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllParts(RelatedCards... elements) {
      if (this.allParts == null) {
        this.allParts = new ArrayList<RelatedCards>();
      }
      for (RelatedCards element : elements) {
        this.allParts.add(Objects.requireNonNull(element, "allParts element"));
      }
      return this;
    }


    /**
     * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getAllParts() allParts} list.
     * @param elements An iterable of allParts elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("allParts")
    public final Builder allParts(@Nullable Iterable<? extends RelatedCards> elements) {
      if (elements == null) {
        this.allParts = null;
        return this;
      }
      this.allParts = new ArrayList<RelatedCards>();
      return addAllAllParts(elements);
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getAllParts() allParts} list.
     * @param elements An iterable of allParts elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllAllParts(Iterable<? extends RelatedCards> elements) {
      Objects.requireNonNull(elements, "allParts element");
      if (this.allParts == null) {
        this.allParts = new ArrayList<RelatedCards>();
      }
      for (RelatedCards element : elements) {
        this.allParts.add(Objects.requireNonNull(element, "allParts element"));
      }
      return this;
    }

    /**
     * Adds one element to {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces} list.
     * @param element A cardFaces element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addCardFaces(CardFace element) {
      if (this.cardFaces == null) {
        this.cardFaces = new ArrayList<CardFace>();
      }
      this.cardFaces.add(Objects.requireNonNull(element, "cardFaces element"));
      return this;
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces} list.
     * @param elements An array of cardFaces elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addCardFaces(CardFace... elements) {
      if (this.cardFaces == null) {
        this.cardFaces = new ArrayList<CardFace>();
      }
      for (CardFace element : elements) {
        this.cardFaces.add(Objects.requireNonNull(element, "cardFaces element"));
      }
      return this;
    }


    /**
     * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces} list.
     * @param elements An iterable of cardFaces elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("card_faces")
    public final Builder cardFaces(@Nullable Iterable<? extends CardFace> elements) {
      if (elements == null) {
        this.cardFaces = null;
        return this;
      }
      this.cardFaces = new ArrayList<CardFace>();
      return addAllCardFaces(elements);
    }

    /**
     * Adds elements to {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces} list.
     * @param elements An iterable of cardFaces elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllCardFaces(Iterable<? extends CardFace> elements) {
      Objects.requireNonNull(elements, "cardFaces element");
      if (this.cardFaces == null) {
        this.cardFaces = new ArrayList<CardFace>();
      }
      for (CardFace element : elements) {
        this.cardFaces.add(Objects.requireNonNull(element, "cardFaces element"));
      }
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getLegalities() legalities} attribute.
     * @param legalities The value for legalities (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("legalities")
    public final Builder legalities(@Nullable Legality legalities) {
      this.legalities = legalities;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getReserved() reserved} attribute.
     * @param reserved The value for reserved (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("reserved")
    public final Builder reserved(@Nullable Boolean reserved) {
      this.reserved = reserved;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getEdhrecRank() edhrecRank} attribute.
     * @param edhrecRank The value for edhrecRank (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("edhrecRank")
    public final Builder edhrecRank(@Nullable Integer edhrecRank) {
      this.edhrecRank = edhrecRank;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getSetCode() setCode} attribute.
     * @param setCode The value for setCode (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("setCode")
    public final Builder setCode(@Nullable String setCode) {
      this.setCode = setCode;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getSetName() setName} attribute.
     * @param setName The value for setName (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("setName")
    public final Builder setName(@Nullable String setName) {
      this.setName = setName;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getCollectorNumber() collectorNumber} attribute.
     * @param collectorNumber The value for collectorNumber (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("collectorNumber")
    public final Builder collectorNumber(@Nullable String collectorNumber) {
      this.collectorNumber = collectorNumber;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getSetSearchUri() setSearchUri} attribute.
     * @param setSearchUri The value for setSearchUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("setSearchUri")
    public final Builder setSearchUri(@Nullable String setSearchUri) {
      this.setSearchUri = setSearchUri;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getScryfallSetUri() scryfallSetUri} attribute.
     * @param scryfallSetUri The value for scryfallSetUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("scryfallSetUri")
    public final Builder scryfallSetUri(@Nullable String scryfallSetUri) {
      this.scryfallSetUri = scryfallSetUri;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getImageUris() imageUris} attribute.
     * @param imageUris The value for imageUris (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("image_uris")
    public final Builder imageUris(@Nullable ImageUri imageUris) {
      this.imageUris = imageUris;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getHighresImage() highresImage} attribute.
     * @param highresImage The value for highresImage (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("highresImage")
    public final Builder highresImage(@Nullable Boolean highresImage) {
      this.highresImage = highresImage;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getReprint() reprint} attribute.
     * @param reprint The value for reprint (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("reprint")
    public final Builder reprint(@Nullable Boolean reprint) {
      this.reprint = reprint;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getDigital() digital} attribute.
     * @param digital The value for digital (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("digital")
    public final Builder digital(@Nullable Boolean digital) {
      this.digital = digital;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getRarity() rarity} attribute.
     * @param rarity The value for rarity (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("rarity")
    public final Builder rarity(@Nullable Rarity rarity) {
      this.rarity = rarity;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getFlavorText() flavorText} attribute.
     * @param flavorText The value for flavorText (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("flavorText")
    public final Builder flavorText(@Nullable String flavorText) {
      this.flavorText = flavorText;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getArtist() artist} attribute.
     * @param artist The value for artist (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("artist")
    public final Builder artist(@Nullable String artist) {
      this.artist = artist;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getIllustrationId() illustrationId} attribute.
     * @param illustrationId The value for illustrationId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("illustrationId")
    public final Builder illustrationId(@Nullable UUID illustrationId) {
      this.illustrationId = illustrationId;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getFrame() frame} attribute.
     * @param frame The value for frame (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("frame")
    public final Builder frame(@Nullable String frame) {
      this.frame = frame;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getFullArt() fullArt} attribute.
     * @param fullArt The value for fullArt (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("fullArt")
    public final Builder fullArt(@Nullable Boolean fullArt) {
      this.fullArt = fullArt;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getWatermark() watermark} attribute.
     * @param watermark The value for watermark (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("watermark")
    public final Builder watermark(@Nullable String watermark) {
      this.watermark = watermark;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getBorderColor() borderColor} attribute.
     * @param borderColor The value for borderColor (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("borderColor")
    public final Builder borderColor(@Nullable BorderColors borderColor) {
      this.borderColor = borderColor;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getStorySpotlightNumber() storySpotlightNumber} attribute.
     * @param storySpotlightNumber The value for storySpotlightNumber (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("storySpotlightNumber")
    public final Builder storySpotlightNumber(@Nullable Integer storySpotlightNumber) {
      this.storySpotlightNumber = storySpotlightNumber;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getStorySpotlightUri() storySpotlightUri} attribute.
     * @param storySpotlightUri The value for storySpotlightUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("storySpotlightUri")
    public final Builder storySpotlightUri(@Nullable String storySpotlightUri) {
      this.storySpotlightUri = storySpotlightUri;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getTimeshifted() timeshifted} attribute.
     * @param timeshifted The value for timeshifted (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("timeshifted")
    public final Builder timeshifted(@Nullable Boolean timeshifted) {
      this.timeshifted = timeshifted;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getColorshifted() colorshifted} attribute.
     * @param colorshifted The value for colorshifted (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("colorshifted")
    public final Builder colorshifted(@Nullable Boolean colorshifted) {
      this.colorshifted = colorshifted;
      return this;
    }

    /**
     * Initializes the value for the {@link com.alver.fatefall.api.base.Card#getFutureshifted() futureshifted} attribute.
     * @param futureshifted The value for futureshifted (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("futureshifted")
    public final Builder futureshifted(@Nullable Boolean futureshifted) {
      this.futureshifted = futureshifted;
      return this;
    }

    /**
     * Put one entry to the {@link com.alver.fatefall.api.base.Card#getPurchaseUris() purchaseUris} map.
     * @param key The key in the purchaseUris map
     * @param value The associated value in the purchaseUris map
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putPurchaseUris(String key, String value) {
      if (this.purchaseUris == null) {
        this.purchaseUris = new LinkedHashMap<String, String>();
      }
      this.purchaseUris.put(
          Objects.requireNonNull(key, "purchaseUris key"),
          Objects.requireNonNull(value, "purchaseUris value"));
      return this;
    }

    /**
     * Put one entry to the {@link com.alver.fatefall.api.base.Card#getPurchaseUris() purchaseUris} map. Nulls are not permitted
     * @param entry The key and value entry
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putPurchaseUris(Map.Entry<String, ? extends String> entry) {
      if (this.purchaseUris == null) {
        this.purchaseUris = new LinkedHashMap<String, String>();
      }
      String k = entry.getKey();
      String v = entry.getValue();
      this.purchaseUris.put(
          Objects.requireNonNull(k, "purchaseUris key"),
          Objects.requireNonNull(v, "purchaseUris value"));
      return this;
    }

    /**
     * Sets or replaces all mappings from the specified map as entries for the {@link com.alver.fatefall.api.base.Card#getPurchaseUris() purchaseUris} map. Nulls are not permitted as keys or values, but parameter itself can be null
     * @param entries The entries that will be added to the purchaseUris map
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("purchaseUris")
    public final Builder purchaseUris(@Nullable Map<String, ? extends String> entries) {
      if (entries == null) {
        this.purchaseUris = null;
        return this;
      }
      this.purchaseUris = new LinkedHashMap<String, String>();
      return putAllPurchaseUris(entries);
    }

    /**
     * Put all mappings from the specified map as entries to {@link com.alver.fatefall.api.base.Card#getPurchaseUris() purchaseUris} map. Nulls are not permitted
     * @param entries The entries that will be added to the purchaseUris map
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putAllPurchaseUris(Map<String, ? extends String> entries) {
      if (this.purchaseUris == null) {
        this.purchaseUris = new LinkedHashMap<String, String>();
      }
      for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
        String k = e.getKey();
        String v = e.getValue();
        this.purchaseUris.put(
            Objects.requireNonNull(k, "purchaseUris key"),
            Objects.requireNonNull(v, "purchaseUris value"));
      }
      return this;
    }

    /**
     * Put one entry to the {@link com.alver.fatefall.api.base.Card#getRelatedUris() relatedUris} map.
     * @param key The key in the relatedUris map
     * @param value The associated value in the relatedUris map
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putRelatedUris(String key, String value) {
      if (this.relatedUris == null) {
        this.relatedUris = new LinkedHashMap<String, String>();
      }
      this.relatedUris.put(
          Objects.requireNonNull(key, "relatedUris key"),
          Objects.requireNonNull(value, "relatedUris value"));
      return this;
    }

    /**
     * Put one entry to the {@link com.alver.fatefall.api.base.Card#getRelatedUris() relatedUris} map. Nulls are not permitted
     * @param entry The key and value entry
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putRelatedUris(Map.Entry<String, ? extends String> entry) {
      if (this.relatedUris == null) {
        this.relatedUris = new LinkedHashMap<String, String>();
      }
      String k = entry.getKey();
      String v = entry.getValue();
      this.relatedUris.put(
          Objects.requireNonNull(k, "relatedUris key"),
          Objects.requireNonNull(v, "relatedUris value"));
      return this;
    }

    /**
     * Sets or replaces all mappings from the specified map as entries for the {@link com.alver.fatefall.api.base.Card#getRelatedUris() relatedUris} map. Nulls are not permitted as keys or values, but parameter itself can be null
     * @param entries The entries that will be added to the relatedUris map
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("relatedUris")
    public final Builder relatedUris(@Nullable Map<String, ? extends String> entries) {
      if (entries == null) {
        this.relatedUris = null;
        return this;
      }
      this.relatedUris = new LinkedHashMap<String, String>();
      return putAllRelatedUris(entries);
    }

    /**
     * Put all mappings from the specified map as entries to {@link com.alver.fatefall.api.base.Card#getRelatedUris() relatedUris} map. Nulls are not permitted
     * @param entries The entries that will be added to the relatedUris map
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putAllRelatedUris(Map<String, ? extends String> entries) {
      if (this.relatedUris == null) {
        this.relatedUris = new LinkedHashMap<String, String>();
      }
      for (Map.Entry<String, ? extends String> e : entries.entrySet()) {
        String k = e.getKey();
        String v = e.getValue();
        this.relatedUris.put(
            Objects.requireNonNull(k, "relatedUris key"),
            Objects.requireNonNull(v, "relatedUris value"));
      }
      return this;
    }

    /**
     * Builds a new {@link ImmutableCard ImmutableCard}.
     * @return An immutable instance of Card
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableCard build() {
      return new ImmutableCard(
          id,
          name,
          oracleId,
          multiverseIds == null ? null : createUnmodifiableList(true, multiverseIds),
          mtgoId,
          arenaId,
          mtgoFoilId,
          uri,
          scryfallUri,
          printsSearchUri,
          rulingsUri,
          layout,
          cmc,
          typeLine,
          oracleText,
          manaCost,
          power,
          toughness,
          loyalty,
          lifeModifier,
          handModifier,
          colors == null ? null : createUnmodifiableList(true, colors),
          colorIndicator == null ? null : createUnmodifiableList(true, colorIndicator),
          colorIdentity == null ? null : createUnmodifiableList(true, colorIdentity),
          allParts == null ? null : createUnmodifiableList(true, allParts),
          cardFaces == null ? null : createUnmodifiableList(true, cardFaces),
          legalities,
          reserved,
          edhrecRank,
          setCode,
          setName,
          collectorNumber,
          setSearchUri,
          scryfallSetUri,
          imageUris,
          highresImage,
          reprint,
          digital,
          rarity,
          flavorText,
          artist,
          illustrationId,
          frame,
          fullArt,
          watermark,
          borderColor,
          storySpotlightNumber,
          storySpotlightUri,
          timeshifted,
          colorshifted,
          futureshifted,
          purchaseUris == null ? null : createUnmodifiableMap(false, false, purchaseUris),
          relatedUris == null ? null : createUnmodifiableMap(false, false, relatedUris));
    }
  }

  private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean checkNulls, boolean skipNulls) {
    ArrayList<T> list;
    if (iterable instanceof Collection<?>) {
      int size = ((Collection<?>) iterable).size();
      if (size == 0) return Collections.emptyList();
      list = new ArrayList<>();
    } else {
      list = new ArrayList<>();
    }
    for (T element : iterable) {
      if (skipNulls && element == null) continue;
      if (checkNulls) Objects.requireNonNull(element, "element");
      list.add(element);
    }
    return list;
  }

  private static <T> List<T> createUnmodifiableList(boolean clone, List<T> list) {
    switch(list.size()) {
    case 0: return Collections.emptyList();
    case 1: return Collections.singletonList(list.get(0));
    default:
      if (clone) {
        return Collections.unmodifiableList(new ArrayList<>(list));
      } else {
        if (list instanceof ArrayList<?>) {
          ((ArrayList<?>) list).trimToSize();
        }
        return Collections.unmodifiableList(list);
      }
    }
  }

  private static <K, V> Map<K, V> createUnmodifiableMap(boolean checkNulls, boolean skipNulls, Map<? extends K, ? extends V> map) {
    switch (map.size()) {
    case 0: return Collections.emptyMap();
    case 1: {
      Map.Entry<? extends K, ? extends V> e = map.entrySet().iterator().next();
      K k = e.getKey();
      V v = e.getValue();
      if (checkNulls) {
        Objects.requireNonNull(k, "key");
        Objects.requireNonNull(v, "value");
      }
      if (skipNulls && (k == null || v == null)) {
        return Collections.emptyMap();
      }
      return Collections.singletonMap(k, v);
    }
    default: {
      Map<K, V> linkedMap = new LinkedHashMap<>(map.size());
      if (skipNulls || checkNulls) {
        for (Map.Entry<? extends K, ? extends V> e : map.entrySet()) {
          K k = e.getKey();
          V v = e.getValue();
          if (skipNulls) {
            if (k == null || v == null) continue;
          } else if (checkNulls) {
            Objects.requireNonNull(k, "key");
            Objects.requireNonNull(v, "value");
          }
          linkedMap.put(k, v);
        }
      } else {
        linkedMap.putAll(map);
      }
      return Collections.unmodifiableMap(linkedMap);
    }
    }
  }
}
