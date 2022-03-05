package com.alver.scryfall.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Ints;
import com.sun.istack.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link AbstractCard}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableCard.builder()}.
 */
@Generated(from = "AbstractCard", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutableCard extends AbstractCard {
  private final @Nullable UUID id;
  private final @Nullable String name;
  private final @Nullable UUID oracleId;
  private final @Nullable ImmutableList<Integer> multiverseIds;
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
  private final @Nullable ImmutableList<Colors> colors;
  private final @Nullable ImmutableList<Colors> colorIndicator;
  private final @Nullable ImmutableList<Colors> colorIdentity;
  private final @Nullable ImmutableList<RelatedCards> allParts;
  private final @Nullable ImmutableList<CardFace> cardFaces;
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
  private final @Nullable ImmutableMap<String, String> purchaseUris;
  private final @Nullable ImmutableMap<String, String> relatedUris;

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
      @Nullable ImmutableList<Integer> multiverseIds,
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
      @Nullable ImmutableList<Colors> colors,
      @Nullable ImmutableList<Colors> colorIndicator,
      @Nullable ImmutableList<Colors> colorIdentity,
      @Nullable ImmutableList<RelatedCards> allParts,
      @Nullable ImmutableList<CardFace> cardFaces,
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
      @Nullable ImmutableMap<String, String> purchaseUris,
      @Nullable ImmutableMap<String, String> relatedUris) {
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
   * The id property.
   */
  @JsonProperty("id")
  @Override
  public @Nullable UUID id() {
    return id;
  }

  /**
   * The name property.
   */
  @JsonProperty("name")
  @Override
  public @Nullable String name() {
    return name;
  }

  /**
   * The oracleId property.
   */
  @JsonProperty("oracle_id")
  @Override
  public @Nullable UUID oracleId() {
    return oracleId;
  }

  /**
   * The multiverseIds property.
   */
  @JsonProperty("multiverse_ids")
  @Override
  public @Nullable ImmutableList<Integer> multiverseIds() {
    return multiverseIds;
  }

  /**
   * The mtgoId property.
   */
  @JsonProperty("mtgo_id")
  @Override
  public @Nullable Integer mtgoId() {
    return mtgoId;
  }

  /**
   * The arenaId property.
   */
  @JsonProperty("arena_id")
  @Override
  public @Nullable Integer arenaId() {
    return arenaId;
  }

  /**
   * The mtgoFoilId property.
   */
  @JsonProperty("mtgo_foil_id")
  @Override
  public @Nullable Integer mtgoFoilId() {
    return mtgoFoilId;
  }

  /**
   * The uri property.
   */
  @JsonProperty("uri")
  @Override
  public @Nullable String uri() {
    return uri;
  }

  /**
   * The scryfallUri property.
   */
  @JsonProperty("scryfall_uri")
  @Override
  public @Nullable String scryfallUri() {
    return scryfallUri;
  }

  /**
   * The printsSearchUri property.
   */
  @JsonProperty("prints_search_uri")
  @Override
  public @Nullable String printsSearchUri() {
    return printsSearchUri;
  }

  /**
   * The rulingsUri property.
   */
  @JsonProperty("rulings_uri")
  @Override
  public @Nullable String rulingsUri() {
    return rulingsUri;
  }

  /**
   * Possible values include: 'normal', 'split', 'flip', 'transform', 'meld',
   * 'leveler', 'saga', 'planar', 'scheme', 'vanguard', 'token',
   * 'double_faced_token', 'emblem', 'augment', 'host'.
   */
  @JsonProperty("layout")
  @Override
  public @Nullable Layouts layout() {
    return layout;
  }

  /**
   * The cmc property.
   */
  @JsonProperty("cmc")
  @Override
  public @Nullable Double cmc() {
    return cmc;
  }

  /**
   * The typeLine property.
   */
  @JsonProperty("type_line")
  @Override
  public @Nullable String typeLine() {
    return typeLine;
  }

  /**
   * The oracleText property.
   */
  @JsonProperty("oracle_text")
  @Override
  public @Nullable String oracleText() {
    return oracleText;
  }

  /**
   * The manaCost property.
   */
  @JsonProperty("mana_cost")
  @Override
  public @Nullable String manaCost() {
    return manaCost;
  }

  /**
   * The power property.
   */
  @JsonProperty("power")
  @Override
  public @Nullable String power() {
    return power;
  }

  /**
   * The toughness property.
   */
  @JsonProperty("toughness")
  @Override
  public @Nullable String toughness() {
    return toughness;
  }

  /**
   * The loyalty property.
   */
  @JsonProperty("loyalty")
  @Override
  public @Nullable String loyalty() {
    return loyalty;
  }

  /**
   * The lifeModifier property.
   */
  @JsonProperty("life_modifier")
  @Override
  public @Nullable String lifeModifier() {
    return lifeModifier;
  }

  /**
   * The handModifier property.
   */
  @JsonProperty("hand_modifier")
  @Override
  public @Nullable String handModifier() {
    return handModifier;
  }

  /**
   * The colors property.
   */
  @JsonProperty("colors")
  @Override
  public @Nullable ImmutableList<Colors> colors() {
    return colors;
  }

  /**
   * The colorIndicator property.
   */
  @JsonProperty("color_indicator")
  @Override
  public @Nullable ImmutableList<Colors> colorIndicator() {
    return colorIndicator;
  }

  /**
   * The colorIdentity property.
   */
  @JsonProperty("color_identity")
  @Override
  public @Nullable ImmutableList<Colors> colorIdentity() {
    return colorIdentity;
  }

  /**
   * The allParts property.
   */
  @JsonProperty("all_parts")
  @Override
  public @Nullable ImmutableList<RelatedCards> allParts() {
    return allParts;
  }

  /**
   * The cardFaces property.
   */
  @JsonProperty("card_faces")
  @Override
  public @Nullable ImmutableList<CardFace> cardFaces() {
    return cardFaces;
  }

  /**
   * The legalities property.
   */
  @JsonProperty("legalities")
  @Override
  public @Nullable Legality legalities() {
    return legalities;
  }

  /**
   * The reserved property.
   */
  @JsonProperty("reserved")
  @Override
  public @Nullable Boolean reserved() {
    return reserved;
  }

  /**
   * The edhrecRank property.
   */
  @JsonProperty("edhrec_rank")
  @Override
  public @Nullable Integer edhrecRank() {
    return edhrecRank;
  }

  /**
   * The set property.
   */
  @JsonProperty("set")
  @Override
  public @Nullable String setCode() {
    return setCode;
  }

  /**
   * The setName property.
   */
  @JsonProperty("set_name")
  @Override
  public @Nullable String setName() {
    return setName;
  }

  /**
   * The collectorNumber property.
   */
  @JsonProperty("collector_number")
  @Override
  public @Nullable String collectorNumber() {
    return collectorNumber;
  }

  /**
   * The setSearchUri property.
   */
  @JsonProperty("set_search_uri")
  @Override
  public @Nullable String setSearchUri() {
    return setSearchUri;
  }

  /**
   * The scryfallSetUri property.
   */
  @JsonProperty("scryfall_set_uri")
  @Override
  public @Nullable String scryfallSetUri() {
    return scryfallSetUri;
  }

  /**
   * The imageUris property.
   */
  @JsonProperty("image_uris")
  @Override
  public @Nullable ImageUri imageUris() {
    return imageUris;
  }

  /**
   * The highresImage property.
   */
  @JsonProperty("highres_image")
  @Override
  public @Nullable Boolean highresImage() {
    return highresImage;
  }

  /**
   * The reprint property.
   */
  @JsonProperty("reprint")
  @Override
  public @Nullable Boolean reprint() {
    return reprint;
  }

  /**
   * The digital property.
   */
  @JsonProperty("digital")
  @Override
  public @Nullable Boolean digital() {
    return digital;
  }

  /**
   * Possible values include: 'common', 'uncommon', 'rare', 'mythic'.
   */
  @JsonProperty("rarity")
  @Override
  public @Nullable Rarity rarity() {
    return rarity;
  }

  /**
   * The flavorText property.
   */
  @JsonProperty("flavor_text")
  @Override
  public @Nullable String flavorText() {
    return flavorText;
  }

  /**
   * The artist property.
   */
  @JsonProperty("artist")
  @Override
  public @Nullable String artist() {
    return artist;
  }

  /**
   * The illustrationId property.
   */
  @JsonProperty("illustration_id")
  @Override
  public @Nullable UUID illustrationId() {
    return illustrationId;
  }

  /**
   * The frame property.
   */
  @JsonProperty("frame")
  @Override
  public @Nullable String frame() {
    return frame;
  }

  /**
   * The fullArt property.
   */
  @JsonProperty("full_art")
  @Override
  public @Nullable Boolean fullArt() {
    return fullArt;
  }

  /**
   * The watermark property.
   */
  @JsonProperty("watermark")
  @Override
  public @Nullable String watermark() {
    return watermark;
  }

  /**
   * Possible values include: 'black', 'borderless', 'gold', 'silver',
   * 'white'.
   */
  @JsonProperty("border_color")
  @Override
  public @Nullable BorderColors borderColor() {
    return borderColor;
  }

  /**
   * The storySpotlightNumber property.
   */
  @JsonProperty("story_spotlight_number")
  @Override
  public @Nullable Integer storySpotlightNumber() {
    return storySpotlightNumber;
  }

  /**
   * The storySpotlightUri property.
   */
  @JsonProperty("story_spotlight_uri")
  @Override
  public @Nullable String storySpotlightUri() {
    return storySpotlightUri;
  }

  /**
   * The timeshifted property.
   */
  @JsonProperty("timeshifted")
  @Override
  public @Nullable Boolean timeshifted() {
    return timeshifted;
  }

  /**
   * The colorshifted property.
   */
  @JsonProperty("colorshifted")
  @Override
  public @Nullable Boolean colorshifted() {
    return colorshifted;
  }

  /**
   * The futureshifted property.
   */
  @JsonProperty("futureshifted")
  @Override
  public @Nullable Boolean futureshifted() {
    return futureshifted;
  }

  /**
   * The purchaseUris property.
   */
  @JsonProperty("purchase_uris")
  @Override
  public @Nullable ImmutableMap<String, String> purchaseUris() {
    return purchaseUris;
  }

  /**
   * The relatedUris property.
   */
  @JsonProperty("related_uris")
  @Override
  public @Nullable ImmutableMap<String, String> relatedUris() {
    return relatedUris;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link AbstractCard#id() id} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#name() name} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#oracleId() oracleId} attribute.
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#multiverseIds() multiverseIds}.
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
    @Nullable ImmutableList<Integer> newValue = Ints.asList(elements) == null ? null : ImmutableList.copyOf(Ints.asList(elements));
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#multiverseIds() multiverseIds}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of multiverseIds elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withMultiverseIds(@Nullable Iterable<Integer> elements) {
    if (this.multiverseIds == elements) return this;
    @Nullable ImmutableList<Integer> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#mtgoId() mtgoId} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#arenaId() arenaId} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#mtgoFoilId() mtgoFoilId} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#uri() uri} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#scryfallUri() scryfallUri} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#printsSearchUri() printsSearchUri} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#rulingsUri() rulingsUri} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#layout() layout} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#cmc() cmc} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#typeLine() typeLine} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#oracleText() oracleText} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#manaCost() manaCost} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#power() power} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#toughness() toughness} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#loyalty() loyalty} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#lifeModifier() lifeModifier} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#handModifier() handModifier} attribute.
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#colors() colors}.
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
    @Nullable ImmutableList<Colors> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#colors() colors}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of colors elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withColors(@Nullable Iterable<? extends Colors> elements) {
    if (this.colors == elements) return this;
    @Nullable ImmutableList<Colors> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#colorIndicator() colorIndicator}.
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
    @Nullable ImmutableList<Colors> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#colorIndicator() colorIndicator}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of colorIndicator elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withColorIndicator(@Nullable Iterable<? extends Colors> elements) {
    if (this.colorIndicator == elements) return this;
    @Nullable ImmutableList<Colors> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#colorIdentity() colorIdentity}.
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
    @Nullable ImmutableList<Colors> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#colorIdentity() colorIdentity}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of colorIdentity elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withColorIdentity(@Nullable Iterable<? extends Colors> elements) {
    if (this.colorIdentity == elements) return this;
    @Nullable ImmutableList<Colors> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#allParts() allParts}.
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
    @Nullable ImmutableList<RelatedCards> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#allParts() allParts}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of allParts elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withAllParts(@Nullable Iterable<? extends RelatedCards> elements) {
    if (this.allParts == elements) return this;
    @Nullable ImmutableList<RelatedCards> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#cardFaces() cardFaces}.
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
    @Nullable ImmutableList<CardFace> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object with elements that replace the content of {@link AbstractCard#cardFaces() cardFaces}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of cardFaces elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withCardFaces(@Nullable Iterable<? extends CardFace> elements) {
    if (this.cardFaces == elements) return this;
    @Nullable ImmutableList<CardFace> newValue = elements == null ? null : ImmutableList.copyOf(elements);
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#legalities() legalities} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#reserved() reserved} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#edhrecRank() edhrecRank} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#setCode() setCode} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#setName() setName} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#collectorNumber() collectorNumber} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#setSearchUri() setSearchUri} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#scryfallSetUri() scryfallSetUri} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#imageUris() imageUris} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#highresImage() highresImage} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#reprint() reprint} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#digital() digital} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#rarity() rarity} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#flavorText() flavorText} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#artist() artist} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#illustrationId() illustrationId} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#frame() frame} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#fullArt() fullArt} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#watermark() watermark} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#borderColor() borderColor} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#storySpotlightNumber() storySpotlightNumber} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#storySpotlightUri() storySpotlightUri} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#timeshifted() timeshifted} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#colorshifted() colorshifted} attribute.
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
   * Copy the current immutable object by setting a value for the {@link AbstractCard#futureshifted() futureshifted} attribute.
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
   * Copy the current immutable object by replacing the {@link AbstractCard#purchaseUris() purchaseUris} map with the specified map.
   * Nulls are not permitted as keys or values.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param entries The entries to be added to the purchaseUris map
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withPurchaseUris(@Nullable Map<String, ? extends String> entries) {
    if (this.purchaseUris == entries) return this;
    @Nullable ImmutableMap<String, String> newValue = entries == null ? null : ImmutableMap.copyOf(entries);
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
   * Copy the current immutable object by replacing the {@link AbstractCard#relatedUris() relatedUris} map with the specified map.
   * Nulls are not permitted as keys or values.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param entries The entries to be added to the relatedUris map
   * @return A modified copy of {@code this} object
   */
  public final ImmutableCard withRelatedUris(@Nullable Map<String, ? extends String> entries) {
    if (this.relatedUris == entries) return this;
    @Nullable ImmutableMap<String, String> newValue = entries == null ? null : ImmutableMap.copyOf(entries);
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
    return MoreObjects.toStringHelper("Card")
        .omitNullValues()
        .add("id", id)
        .add("name", name)
        .add("oracleId", oracleId)
        .add("multiverseIds", multiverseIds)
        .add("mtgoId", mtgoId)
        .add("arenaId", arenaId)
        .add("mtgoFoilId", mtgoFoilId)
        .add("uri", uri)
        .add("scryfallUri", scryfallUri)
        .add("printsSearchUri", printsSearchUri)
        .add("rulingsUri", rulingsUri)
        .add("layout", layout)
        .add("cmc", cmc)
        .add("typeLine", typeLine)
        .add("oracleText", oracleText)
        .add("manaCost", manaCost)
        .add("power", power)
        .add("toughness", toughness)
        .add("loyalty", loyalty)
        .add("lifeModifier", lifeModifier)
        .add("handModifier", handModifier)
        .add("colors", colors)
        .add("colorIndicator", colorIndicator)
        .add("colorIdentity", colorIdentity)
        .add("allParts", allParts)
        .add("cardFaces", cardFaces)
        .add("legalities", legalities)
        .add("reserved", reserved)
        .add("edhrecRank", edhrecRank)
        .add("setCode", setCode)
        .add("setName", setName)
        .add("collectorNumber", collectorNumber)
        .add("setSearchUri", setSearchUri)
        .add("scryfallSetUri", scryfallSetUri)
        .add("imageUris", imageUris)
        .add("highresImage", highresImage)
        .add("reprint", reprint)
        .add("digital", digital)
        .add("rarity", rarity)
        .add("flavorText", flavorText)
        .add("artist", artist)
        .add("illustrationId", illustrationId)
        .add("frame", frame)
        .add("fullArt", fullArt)
        .add("watermark", watermark)
        .add("borderColor", borderColor)
        .add("storySpotlightNumber", storySpotlightNumber)
        .add("storySpotlightUri", storySpotlightUri)
        .add("timeshifted", timeshifted)
        .add("colorshifted", colorshifted)
        .add("futureshifted", futureshifted)
        .add("purchaseUris", purchaseUris)
        .add("relatedUris", relatedUris)
        .toString();
  }

  /**
   * Creates an immutable copy of a {@link AbstractCard} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable Card instance
   */
  public static ImmutableCard copyOf(AbstractCard instance) {
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
   *    .id(UUID | null) // nullable {@link AbstractCard#id() id}
   *    .name(String | null) // nullable {@link AbstractCard#name() name}
   *    .oracleId(UUID | null) // nullable {@link AbstractCard#oracleId() oracleId}
   *    .multiverseIds(List&amp;lt;Integer&amp;gt; | null) // nullable {@link AbstractCard#multiverseIds() multiverseIds}
   *    .mtgoId(Integer | null) // nullable {@link AbstractCard#mtgoId() mtgoId}
   *    .arenaId(Integer | null) // nullable {@link AbstractCard#arenaId() arenaId}
   *    .mtgoFoilId(Integer | null) // nullable {@link AbstractCard#mtgoFoilId() mtgoFoilId}
   *    .uri(String | null) // nullable {@link AbstractCard#uri() uri}
   *    .scryfallUri(String | null) // nullable {@link AbstractCard#scryfallUri() scryfallUri}
   *    .printsSearchUri(String | null) // nullable {@link AbstractCard#printsSearchUri() printsSearchUri}
   *    .rulingsUri(String | null) // nullable {@link AbstractCard#rulingsUri() rulingsUri}
   *    .layout(com.alver.scryfall.api.models.Layouts | null) // nullable {@link AbstractCard#layout() layout}
   *    .cmc(Double | null) // nullable {@link AbstractCard#cmc() cmc}
   *    .typeLine(String | null) // nullable {@link AbstractCard#typeLine() typeLine}
   *    .oracleText(String | null) // nullable {@link AbstractCard#oracleText() oracleText}
   *    .manaCost(String | null) // nullable {@link AbstractCard#manaCost() manaCost}
   *    .power(String | null) // nullable {@link AbstractCard#power() power}
   *    .toughness(String | null) // nullable {@link AbstractCard#toughness() toughness}
   *    .loyalty(String | null) // nullable {@link AbstractCard#loyalty() loyalty}
   *    .lifeModifier(String | null) // nullable {@link AbstractCard#lifeModifier() lifeModifier}
   *    .handModifier(String | null) // nullable {@link AbstractCard#handModifier() handModifier}
   *    .colors(List&amp;lt;com.alver.scryfall.api.models.Colors&amp;gt; | null) // nullable {@link AbstractCard#colors() colors}
   *    .colorIndicator(List&amp;lt;com.alver.scryfall.api.models.Colors&amp;gt; | null) // nullable {@link AbstractCard#colorIndicator() colorIndicator}
   *    .colorIdentity(List&amp;lt;com.alver.scryfall.api.models.Colors&amp;gt; | null) // nullable {@link AbstractCard#colorIdentity() colorIdentity}
   *    .allParts(List&amp;lt;com.alver.scryfall.api.models.RelatedCards&amp;gt; | null) // nullable {@link AbstractCard#allParts() allParts}
   *    .cardFaces(List&amp;lt;com.alver.scryfall.api.models.CardFace&amp;gt; | null) // nullable {@link AbstractCard#cardFaces() cardFaces}
   *    .legalities(com.alver.scryfall.api.models.Legality | null) // nullable {@link AbstractCard#legalities() legalities}
   *    .reserved(Boolean | null) // nullable {@link AbstractCard#reserved() reserved}
   *    .edhrecRank(Integer | null) // nullable {@link AbstractCard#edhrecRank() edhrecRank}
   *    .setCode(String | null) // nullable {@link AbstractCard#setCode() setCode}
   *    .setName(String | null) // nullable {@link AbstractCard#setName() setName}
   *    .collectorNumber(String | null) // nullable {@link AbstractCard#collectorNumber() collectorNumber}
   *    .setSearchUri(String | null) // nullable {@link AbstractCard#setSearchUri() setSearchUri}
   *    .scryfallSetUri(String | null) // nullable {@link AbstractCard#scryfallSetUri() scryfallSetUri}
   *    .imageUris(com.alver.scryfall.api.models.ImageUri | null) // nullable {@link AbstractCard#imageUris() imageUris}
   *    .highresImage(Boolean | null) // nullable {@link AbstractCard#highresImage() highresImage}
   *    .reprint(Boolean | null) // nullable {@link AbstractCard#reprint() reprint}
   *    .digital(Boolean | null) // nullable {@link AbstractCard#digital() digital}
   *    .rarity(com.alver.scryfall.api.models.Rarity | null) // nullable {@link AbstractCard#rarity() rarity}
   *    .flavorText(String | null) // nullable {@link AbstractCard#flavorText() flavorText}
   *    .artist(String | null) // nullable {@link AbstractCard#artist() artist}
   *    .illustrationId(UUID | null) // nullable {@link AbstractCard#illustrationId() illustrationId}
   *    .frame(String | null) // nullable {@link AbstractCard#frame() frame}
   *    .fullArt(Boolean | null) // nullable {@link AbstractCard#fullArt() fullArt}
   *    .watermark(String | null) // nullable {@link AbstractCard#watermark() watermark}
   *    .borderColor(com.alver.scryfall.api.models.BorderColors | null) // nullable {@link AbstractCard#borderColor() borderColor}
   *    .storySpotlightNumber(Integer | null) // nullable {@link AbstractCard#storySpotlightNumber() storySpotlightNumber}
   *    .storySpotlightUri(String | null) // nullable {@link AbstractCard#storySpotlightUri() storySpotlightUri}
   *    .timeshifted(Boolean | null) // nullable {@link AbstractCard#timeshifted() timeshifted}
   *    .colorshifted(Boolean | null) // nullable {@link AbstractCard#colorshifted() colorshifted}
   *    .futureshifted(Boolean | null) // nullable {@link AbstractCard#futureshifted() futureshifted}
   *    .purchaseUris(Map&amp;lt;String, String&amp;gt; | null) // nullable {@link AbstractCard#purchaseUris() purchaseUris}
   *    .relatedUris(Map&amp;lt;String, String&amp;gt; | null) // nullable {@link AbstractCard#relatedUris() relatedUris}
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
  @Generated(from = "AbstractCard", generator = "Immutables")
  public static final class Builder {
    private UUID id;
    private String name;
    private UUID oracleId;
    private ImmutableList.Builder<Integer> multiverseIds = null;
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
    private ImmutableList.Builder<Colors> colors = null;
    private ImmutableList.Builder<Colors> colorIndicator = null;
    private ImmutableList.Builder<Colors> colorIdentity = null;
    private ImmutableList.Builder<RelatedCards> allParts = null;
    private ImmutableList.Builder<CardFace> cardFaces = null;
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
    private ImmutableMap.Builder<String, String> purchaseUris = null;
    private ImmutableMap.Builder<String, String> relatedUris = null;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code AbstractCard} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * Collection elements and entries will be added, not replaced.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(AbstractCard instance) {
      Objects.requireNonNull(instance, "instance");
      @Nullable UUID idValue = instance.id();
      if (idValue != null) {
        id(idValue);
      }
      @Nullable String nameValue = instance.name();
      if (nameValue != null) {
        name(nameValue);
      }
      @Nullable UUID oracleIdValue = instance.oracleId();
      if (oracleIdValue != null) {
        oracleId(oracleIdValue);
      }
      @Nullable List<Integer> multiverseIdsValue = instance.multiverseIds();
      if (multiverseIdsValue != null) {
        addAllMultiverseIds(multiverseIdsValue);
      }
      @Nullable Integer mtgoIdValue = instance.mtgoId();
      if (mtgoIdValue != null) {
        mtgoId(mtgoIdValue);
      }
      @Nullable Integer arenaIdValue = instance.arenaId();
      if (arenaIdValue != null) {
        arenaId(arenaIdValue);
      }
      @Nullable Integer mtgoFoilIdValue = instance.mtgoFoilId();
      if (mtgoFoilIdValue != null) {
        mtgoFoilId(mtgoFoilIdValue);
      }
      @Nullable String uriValue = instance.uri();
      if (uriValue != null) {
        uri(uriValue);
      }
      @Nullable String scryfallUriValue = instance.scryfallUri();
      if (scryfallUriValue != null) {
        scryfallUri(scryfallUriValue);
      }
      @Nullable String printsSearchUriValue = instance.printsSearchUri();
      if (printsSearchUriValue != null) {
        printsSearchUri(printsSearchUriValue);
      }
      @Nullable String rulingsUriValue = instance.rulingsUri();
      if (rulingsUriValue != null) {
        rulingsUri(rulingsUriValue);
      }
      @Nullable Layouts layoutValue = instance.layout();
      if (layoutValue != null) {
        layout(layoutValue);
      }
      @Nullable Double cmcValue = instance.cmc();
      if (cmcValue != null) {
        cmc(cmcValue);
      }
      @Nullable String typeLineValue = instance.typeLine();
      if (typeLineValue != null) {
        typeLine(typeLineValue);
      }
      @Nullable String oracleTextValue = instance.oracleText();
      if (oracleTextValue != null) {
        oracleText(oracleTextValue);
      }
      @Nullable String manaCostValue = instance.manaCost();
      if (manaCostValue != null) {
        manaCost(manaCostValue);
      }
      @Nullable String powerValue = instance.power();
      if (powerValue != null) {
        power(powerValue);
      }
      @Nullable String toughnessValue = instance.toughness();
      if (toughnessValue != null) {
        toughness(toughnessValue);
      }
      @Nullable String loyaltyValue = instance.loyalty();
      if (loyaltyValue != null) {
        loyalty(loyaltyValue);
      }
      @Nullable String lifeModifierValue = instance.lifeModifier();
      if (lifeModifierValue != null) {
        lifeModifier(lifeModifierValue);
      }
      @Nullable String handModifierValue = instance.handModifier();
      if (handModifierValue != null) {
        handModifier(handModifierValue);
      }
      @Nullable List<Colors> colorsValue = instance.colors();
      if (colorsValue != null) {
        addAllColors(colorsValue);
      }
      @Nullable List<Colors> colorIndicatorValue = instance.colorIndicator();
      if (colorIndicatorValue != null) {
        addAllColorIndicator(colorIndicatorValue);
      }
      @Nullable List<Colors> colorIdentityValue = instance.colorIdentity();
      if (colorIdentityValue != null) {
        addAllColorIdentity(colorIdentityValue);
      }
      @Nullable List<RelatedCards> allPartsValue = instance.allParts();
      if (allPartsValue != null) {
        addAllAllParts(allPartsValue);
      }
      @Nullable List<CardFace> cardFacesValue = instance.cardFaces();
      if (cardFacesValue != null) {
        addAllCardFaces(cardFacesValue);
      }
      @Nullable Legality legalitiesValue = instance.legalities();
      if (legalitiesValue != null) {
        legalities(legalitiesValue);
      }
      @Nullable Boolean reservedValue = instance.reserved();
      if (reservedValue != null) {
        reserved(reservedValue);
      }
      @Nullable Integer edhrecRankValue = instance.edhrecRank();
      if (edhrecRankValue != null) {
        edhrecRank(edhrecRankValue);
      }
      @Nullable String setCodeValue = instance.setCode();
      if (setCodeValue != null) {
        setCode(setCodeValue);
      }
      @Nullable String setNameValue = instance.setName();
      if (setNameValue != null) {
        setName(setNameValue);
      }
      @Nullable String collectorNumberValue = instance.collectorNumber();
      if (collectorNumberValue != null) {
        collectorNumber(collectorNumberValue);
      }
      @Nullable String setSearchUriValue = instance.setSearchUri();
      if (setSearchUriValue != null) {
        setSearchUri(setSearchUriValue);
      }
      @Nullable String scryfallSetUriValue = instance.scryfallSetUri();
      if (scryfallSetUriValue != null) {
        scryfallSetUri(scryfallSetUriValue);
      }
      @Nullable ImageUri imageUrisValue = instance.imageUris();
      if (imageUrisValue != null) {
        imageUris(imageUrisValue);
      }
      @Nullable Boolean highresImageValue = instance.highresImage();
      if (highresImageValue != null) {
        highresImage(highresImageValue);
      }
      @Nullable Boolean reprintValue = instance.reprint();
      if (reprintValue != null) {
        reprint(reprintValue);
      }
      @Nullable Boolean digitalValue = instance.digital();
      if (digitalValue != null) {
        digital(digitalValue);
      }
      @Nullable Rarity rarityValue = instance.rarity();
      if (rarityValue != null) {
        rarity(rarityValue);
      }
      @Nullable String flavorTextValue = instance.flavorText();
      if (flavorTextValue != null) {
        flavorText(flavorTextValue);
      }
      @Nullable String artistValue = instance.artist();
      if (artistValue != null) {
        artist(artistValue);
      }
      @Nullable UUID illustrationIdValue = instance.illustrationId();
      if (illustrationIdValue != null) {
        illustrationId(illustrationIdValue);
      }
      @Nullable String frameValue = instance.frame();
      if (frameValue != null) {
        frame(frameValue);
      }
      @Nullable Boolean fullArtValue = instance.fullArt();
      if (fullArtValue != null) {
        fullArt(fullArtValue);
      }
      @Nullable String watermarkValue = instance.watermark();
      if (watermarkValue != null) {
        watermark(watermarkValue);
      }
      @Nullable BorderColors borderColorValue = instance.borderColor();
      if (borderColorValue != null) {
        borderColor(borderColorValue);
      }
      @Nullable Integer storySpotlightNumberValue = instance.storySpotlightNumber();
      if (storySpotlightNumberValue != null) {
        storySpotlightNumber(storySpotlightNumberValue);
      }
      @Nullable String storySpotlightUriValue = instance.storySpotlightUri();
      if (storySpotlightUriValue != null) {
        storySpotlightUri(storySpotlightUriValue);
      }
      @Nullable Boolean timeshiftedValue = instance.timeshifted();
      if (timeshiftedValue != null) {
        timeshifted(timeshiftedValue);
      }
      @Nullable Boolean colorshiftedValue = instance.colorshifted();
      if (colorshiftedValue != null) {
        colorshifted(colorshiftedValue);
      }
      @Nullable Boolean futureshiftedValue = instance.futureshifted();
      if (futureshiftedValue != null) {
        futureshifted(futureshiftedValue);
      }
      @Nullable Map<String, String> purchaseUrisValue = instance.purchaseUris();
      if (purchaseUrisValue != null) {
        putAllPurchaseUris(purchaseUrisValue);
      }
      @Nullable Map<String, String> relatedUrisValue = instance.relatedUris();
      if (relatedUrisValue != null) {
        putAllRelatedUris(relatedUrisValue);
      }
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#id() id} attribute.
     * @param id The value for id (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("id")
    public final Builder id(@Nullable UUID id) {
      this.id = id;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#name() name} attribute.
     * @param name The value for name (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("name")
    public final Builder name(@Nullable String name) {
      this.name = name;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#oracleId() oracleId} attribute.
     * @param oracleId The value for oracleId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("oracle_id")
    public final Builder oracleId(@Nullable UUID oracleId) {
      this.oracleId = oracleId;
      return this;
    }

    /**
     * Adds one element to {@link AbstractCard#multiverseIds() multiverseIds} list.
     * @param element A multiverseIds element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addMultiverseIds(int element) {
      if (this.multiverseIds == null) {
        this.multiverseIds = ImmutableList.builder();
      }
      this.multiverseIds.add(element);
      return this;
    }

    /**
     * Adds elements to {@link AbstractCard#multiverseIds() multiverseIds} list.
     * @param elements An array of multiverseIds elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addMultiverseIds(int... elements) {
      if (this.multiverseIds == null) {
        this.multiverseIds = ImmutableList.builder();
      }
      this.multiverseIds.addAll(Ints.asList(elements));
      return this;
    }


    /**
     * Sets or replaces all elements for {@link AbstractCard#multiverseIds() multiverseIds} list.
     * @param elements An iterable of multiverseIds elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("multiverse_ids")
    public final Builder multiverseIds(@Nullable Iterable<Integer> elements) {
      if (elements == null) {
        this.multiverseIds = null;
        return this;
      }
      this.multiverseIds = ImmutableList.builder();
      return addAllMultiverseIds(elements);
    }

    /**
     * Adds elements to {@link AbstractCard#multiverseIds() multiverseIds} list.
     * @param elements An iterable of multiverseIds elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllMultiverseIds(Iterable<Integer> elements) {
      Objects.requireNonNull(elements, "multiverseIds element");
      if (this.multiverseIds == null) {
        this.multiverseIds = ImmutableList.builder();
      }
      this.multiverseIds.addAll(elements);
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#mtgoId() mtgoId} attribute.
     * @param mtgoId The value for mtgoId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("mtgo_id")
    public final Builder mtgoId(@Nullable Integer mtgoId) {
      this.mtgoId = mtgoId;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#arenaId() arenaId} attribute.
     * @param arenaId The value for arenaId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("arena_id")
    public final Builder arenaId(@Nullable Integer arenaId) {
      this.arenaId = arenaId;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#mtgoFoilId() mtgoFoilId} attribute.
     * @param mtgoFoilId The value for mtgoFoilId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("mtgo_foil_id")
    public final Builder mtgoFoilId(@Nullable Integer mtgoFoilId) {
      this.mtgoFoilId = mtgoFoilId;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#uri() uri} attribute.
     * @param uri The value for uri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("uri")
    public final Builder uri(@Nullable String uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#scryfallUri() scryfallUri} attribute.
     * @param scryfallUri The value for scryfallUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("scryfall_uri")
    public final Builder scryfallUri(@Nullable String scryfallUri) {
      this.scryfallUri = scryfallUri;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#printsSearchUri() printsSearchUri} attribute.
     * @param printsSearchUri The value for printsSearchUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("prints_search_uri")
    public final Builder printsSearchUri(@Nullable String printsSearchUri) {
      this.printsSearchUri = printsSearchUri;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#rulingsUri() rulingsUri} attribute.
     * @param rulingsUri The value for rulingsUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("rulings_uri")
    public final Builder rulingsUri(@Nullable String rulingsUri) {
      this.rulingsUri = rulingsUri;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#layout() layout} attribute.
     * @param layout The value for layout (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("layout")
    public final Builder layout(@Nullable Layouts layout) {
      this.layout = layout;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#cmc() cmc} attribute.
     * @param cmc The value for cmc (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("cmc")
    public final Builder cmc(@Nullable Double cmc) {
      this.cmc = cmc;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#typeLine() typeLine} attribute.
     * @param typeLine The value for typeLine (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("type_line")
    public final Builder typeLine(@Nullable String typeLine) {
      this.typeLine = typeLine;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#oracleText() oracleText} attribute.
     * @param oracleText The value for oracleText (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("oracle_text")
    public final Builder oracleText(@Nullable String oracleText) {
      this.oracleText = oracleText;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#manaCost() manaCost} attribute.
     * @param manaCost The value for manaCost (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("mana_cost")
    public final Builder manaCost(@Nullable String manaCost) {
      this.manaCost = manaCost;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#power() power} attribute.
     * @param power The value for power (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("power")
    public final Builder power(@Nullable String power) {
      this.power = power;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#toughness() toughness} attribute.
     * @param toughness The value for toughness (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("toughness")
    public final Builder toughness(@Nullable String toughness) {
      this.toughness = toughness;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#loyalty() loyalty} attribute.
     * @param loyalty The value for loyalty (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("loyalty")
    public final Builder loyalty(@Nullable String loyalty) {
      this.loyalty = loyalty;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#lifeModifier() lifeModifier} attribute.
     * @param lifeModifier The value for lifeModifier (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("life_modifier")
    public final Builder lifeModifier(@Nullable String lifeModifier) {
      this.lifeModifier = lifeModifier;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#handModifier() handModifier} attribute.
     * @param handModifier The value for handModifier (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("hand_modifier")
    public final Builder handModifier(@Nullable String handModifier) {
      this.handModifier = handModifier;
      return this;
    }

    /**
     * Adds one element to {@link AbstractCard#colors() colors} list.
     * @param element A colors element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColors(Colors element) {
      if (this.colors == null) {
        this.colors = ImmutableList.builder();
      }
      this.colors.add(element);
      return this;
    }

    /**
     * Adds elements to {@link AbstractCard#colors() colors} list.
     * @param elements An array of colors elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColors(Colors... elements) {
      if (this.colors == null) {
        this.colors = ImmutableList.builder();
      }
      this.colors.add(elements);
      return this;
    }


    /**
     * Sets or replaces all elements for {@link AbstractCard#colors() colors} list.
     * @param elements An iterable of colors elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("colors")
    public final Builder colors(@Nullable Iterable<? extends Colors> elements) {
      if (elements == null) {
        this.colors = null;
        return this;
      }
      this.colors = ImmutableList.builder();
      return addAllColors(elements);
    }

    /**
     * Adds elements to {@link AbstractCard#colors() colors} list.
     * @param elements An iterable of colors elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllColors(Iterable<? extends Colors> elements) {
      Objects.requireNonNull(elements, "colors element");
      if (this.colors == null) {
        this.colors = ImmutableList.builder();
      }
      this.colors.addAll(elements);
      return this;
    }

    /**
     * Adds one element to {@link AbstractCard#colorIndicator() colorIndicator} list.
     * @param element A colorIndicator element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColorIndicator(Colors element) {
      if (this.colorIndicator == null) {
        this.colorIndicator = ImmutableList.builder();
      }
      this.colorIndicator.add(element);
      return this;
    }

    /**
     * Adds elements to {@link AbstractCard#colorIndicator() colorIndicator} list.
     * @param elements An array of colorIndicator elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColorIndicator(Colors... elements) {
      if (this.colorIndicator == null) {
        this.colorIndicator = ImmutableList.builder();
      }
      this.colorIndicator.add(elements);
      return this;
    }


    /**
     * Sets or replaces all elements for {@link AbstractCard#colorIndicator() colorIndicator} list.
     * @param elements An iterable of colorIndicator elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("color_indicator")
    public final Builder colorIndicator(@Nullable Iterable<? extends Colors> elements) {
      if (elements == null) {
        this.colorIndicator = null;
        return this;
      }
      this.colorIndicator = ImmutableList.builder();
      return addAllColorIndicator(elements);
    }

    /**
     * Adds elements to {@link AbstractCard#colorIndicator() colorIndicator} list.
     * @param elements An iterable of colorIndicator elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllColorIndicator(Iterable<? extends Colors> elements) {
      Objects.requireNonNull(elements, "colorIndicator element");
      if (this.colorIndicator == null) {
        this.colorIndicator = ImmutableList.builder();
      }
      this.colorIndicator.addAll(elements);
      return this;
    }

    /**
     * Adds one element to {@link AbstractCard#colorIdentity() colorIdentity} list.
     * @param element A colorIdentity element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColorIdentity(Colors element) {
      if (this.colorIdentity == null) {
        this.colorIdentity = ImmutableList.builder();
      }
      this.colorIdentity.add(element);
      return this;
    }

    /**
     * Adds elements to {@link AbstractCard#colorIdentity() colorIdentity} list.
     * @param elements An array of colorIdentity elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addColorIdentity(Colors... elements) {
      if (this.colorIdentity == null) {
        this.colorIdentity = ImmutableList.builder();
      }
      this.colorIdentity.add(elements);
      return this;
    }


    /**
     * Sets or replaces all elements for {@link AbstractCard#colorIdentity() colorIdentity} list.
     * @param elements An iterable of colorIdentity elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("color_identity")
    public final Builder colorIdentity(@Nullable Iterable<? extends Colors> elements) {
      if (elements == null) {
        this.colorIdentity = null;
        return this;
      }
      this.colorIdentity = ImmutableList.builder();
      return addAllColorIdentity(elements);
    }

    /**
     * Adds elements to {@link AbstractCard#colorIdentity() colorIdentity} list.
     * @param elements An iterable of colorIdentity elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllColorIdentity(Iterable<? extends Colors> elements) {
      Objects.requireNonNull(elements, "colorIdentity element");
      if (this.colorIdentity == null) {
        this.colorIdentity = ImmutableList.builder();
      }
      this.colorIdentity.addAll(elements);
      return this;
    }

    /**
     * Adds one element to {@link AbstractCard#allParts() allParts} list.
     * @param element A allParts element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllParts(RelatedCards element) {
      if (this.allParts == null) {
        this.allParts = ImmutableList.builder();
      }
      this.allParts.add(element);
      return this;
    }

    /**
     * Adds elements to {@link AbstractCard#allParts() allParts} list.
     * @param elements An array of allParts elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllParts(RelatedCards... elements) {
      if (this.allParts == null) {
        this.allParts = ImmutableList.builder();
      }
      this.allParts.add(elements);
      return this;
    }


    /**
     * Sets or replaces all elements for {@link AbstractCard#allParts() allParts} list.
     * @param elements An iterable of allParts elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("all_parts")
    public final Builder allParts(@Nullable Iterable<? extends RelatedCards> elements) {
      if (elements == null) {
        this.allParts = null;
        return this;
      }
      this.allParts = ImmutableList.builder();
      return addAllAllParts(elements);
    }

    /**
     * Adds elements to {@link AbstractCard#allParts() allParts} list.
     * @param elements An iterable of allParts elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllAllParts(Iterable<? extends RelatedCards> elements) {
      Objects.requireNonNull(elements, "allParts element");
      if (this.allParts == null) {
        this.allParts = ImmutableList.builder();
      }
      this.allParts.addAll(elements);
      return this;
    }

    /**
     * Adds one element to {@link AbstractCard#cardFaces() cardFaces} list.
     * @param element A cardFaces element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addCardFaces(CardFace element) {
      if (this.cardFaces == null) {
        this.cardFaces = ImmutableList.builder();
      }
      this.cardFaces.add(element);
      return this;
    }

    /**
     * Adds elements to {@link AbstractCard#cardFaces() cardFaces} list.
     * @param elements An array of cardFaces elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addCardFaces(CardFace... elements) {
      if (this.cardFaces == null) {
        this.cardFaces = ImmutableList.builder();
      }
      this.cardFaces.add(elements);
      return this;
    }


    /**
     * Sets or replaces all elements for {@link AbstractCard#cardFaces() cardFaces} list.
     * @param elements An iterable of cardFaces elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("card_faces")
    public final Builder cardFaces(@Nullable Iterable<? extends CardFace> elements) {
      if (elements == null) {
        this.cardFaces = null;
        return this;
      }
      this.cardFaces = ImmutableList.builder();
      return addAllCardFaces(elements);
    }

    /**
     * Adds elements to {@link AbstractCard#cardFaces() cardFaces} list.
     * @param elements An iterable of cardFaces elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder addAllCardFaces(Iterable<? extends CardFace> elements) {
      Objects.requireNonNull(elements, "cardFaces element");
      if (this.cardFaces == null) {
        this.cardFaces = ImmutableList.builder();
      }
      this.cardFaces.addAll(elements);
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#legalities() legalities} attribute.
     * @param legalities The value for legalities (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("legalities")
    public final Builder legalities(@Nullable Legality legalities) {
      this.legalities = legalities;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#reserved() reserved} attribute.
     * @param reserved The value for reserved (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("reserved")
    public final Builder reserved(@Nullable Boolean reserved) {
      this.reserved = reserved;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#edhrecRank() edhrecRank} attribute.
     * @param edhrecRank The value for edhrecRank (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("edhrec_rank")
    public final Builder edhrecRank(@Nullable Integer edhrecRank) {
      this.edhrecRank = edhrecRank;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#setCode() setCode} attribute.
     * @param setCode The value for setCode (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("set")
    public final Builder setCode(@Nullable String setCode) {
      this.setCode = setCode;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#setName() setName} attribute.
     * @param setName The value for setName (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("set_name")
    public final Builder setName(@Nullable String setName) {
      this.setName = setName;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#collectorNumber() collectorNumber} attribute.
     * @param collectorNumber The value for collectorNumber (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("collector_number")
    public final Builder collectorNumber(@Nullable String collectorNumber) {
      this.collectorNumber = collectorNumber;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#setSearchUri() setSearchUri} attribute.
     * @param setSearchUri The value for setSearchUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("set_search_uri")
    public final Builder setSearchUri(@Nullable String setSearchUri) {
      this.setSearchUri = setSearchUri;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#scryfallSetUri() scryfallSetUri} attribute.
     * @param scryfallSetUri The value for scryfallSetUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("scryfall_set_uri")
    public final Builder scryfallSetUri(@Nullable String scryfallSetUri) {
      this.scryfallSetUri = scryfallSetUri;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#imageUris() imageUris} attribute.
     * @param imageUris The value for imageUris (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("image_uris")
    public final Builder imageUris(@Nullable ImageUri imageUris) {
      this.imageUris = imageUris;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#highresImage() highresImage} attribute.
     * @param highresImage The value for highresImage (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("highres_image")
    public final Builder highresImage(@Nullable Boolean highresImage) {
      this.highresImage = highresImage;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#reprint() reprint} attribute.
     * @param reprint The value for reprint (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("reprint")
    public final Builder reprint(@Nullable Boolean reprint) {
      this.reprint = reprint;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#digital() digital} attribute.
     * @param digital The value for digital (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("digital")
    public final Builder digital(@Nullable Boolean digital) {
      this.digital = digital;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#rarity() rarity} attribute.
     * @param rarity The value for rarity (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("rarity")
    public final Builder rarity(@Nullable Rarity rarity) {
      this.rarity = rarity;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#flavorText() flavorText} attribute.
     * @param flavorText The value for flavorText (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("flavor_text")
    public final Builder flavorText(@Nullable String flavorText) {
      this.flavorText = flavorText;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#artist() artist} attribute.
     * @param artist The value for artist (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("artist")
    public final Builder artist(@Nullable String artist) {
      this.artist = artist;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#illustrationId() illustrationId} attribute.
     * @param illustrationId The value for illustrationId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("illustration_id")
    public final Builder illustrationId(@Nullable UUID illustrationId) {
      this.illustrationId = illustrationId;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#frame() frame} attribute.
     * @param frame The value for frame (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("frame")
    public final Builder frame(@Nullable String frame) {
      this.frame = frame;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#fullArt() fullArt} attribute.
     * @param fullArt The value for fullArt (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("full_art")
    public final Builder fullArt(@Nullable Boolean fullArt) {
      this.fullArt = fullArt;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#watermark() watermark} attribute.
     * @param watermark The value for watermark (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("watermark")
    public final Builder watermark(@Nullable String watermark) {
      this.watermark = watermark;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#borderColor() borderColor} attribute.
     * @param borderColor The value for borderColor (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("border_color")
    public final Builder borderColor(@Nullable BorderColors borderColor) {
      this.borderColor = borderColor;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#storySpotlightNumber() storySpotlightNumber} attribute.
     * @param storySpotlightNumber The value for storySpotlightNumber (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("story_spotlight_number")
    public final Builder storySpotlightNumber(@Nullable Integer storySpotlightNumber) {
      this.storySpotlightNumber = storySpotlightNumber;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#storySpotlightUri() storySpotlightUri} attribute.
     * @param storySpotlightUri The value for storySpotlightUri (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("story_spotlight_uri")
    public final Builder storySpotlightUri(@Nullable String storySpotlightUri) {
      this.storySpotlightUri = storySpotlightUri;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#timeshifted() timeshifted} attribute.
     * @param timeshifted The value for timeshifted (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("timeshifted")
    public final Builder timeshifted(@Nullable Boolean timeshifted) {
      this.timeshifted = timeshifted;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#colorshifted() colorshifted} attribute.
     * @param colorshifted The value for colorshifted (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("colorshifted")
    public final Builder colorshifted(@Nullable Boolean colorshifted) {
      this.colorshifted = colorshifted;
      return this;
    }

    /**
     * Initializes the value for the {@link AbstractCard#futureshifted() futureshifted} attribute.
     * @param futureshifted The value for futureshifted (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("futureshifted")
    public final Builder futureshifted(@Nullable Boolean futureshifted) {
      this.futureshifted = futureshifted;
      return this;
    }

    /**
     * Put one entry to the {@link AbstractCard#purchaseUris() purchaseUris} map.
     * @param key The key in the purchaseUris map
     * @param value The associated value in the purchaseUris map
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putPurchaseUris(String key, String value) {
      if (this.purchaseUris == null) {
        this.purchaseUris = ImmutableMap.builder();
      }
      this.purchaseUris.put(key, value);
      return this;
    }

    /**
     * Put one entry to the {@link AbstractCard#purchaseUris() purchaseUris} map. Nulls are not permitted
     * @param entry The key and value entry
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putPurchaseUris(Map.Entry<String, ? extends String> entry) {
      if (this.purchaseUris == null) {
        this.purchaseUris = ImmutableMap.builder();
      }
      this.purchaseUris.put(entry);
      return this;
    }

    /**
     * Sets or replaces all mappings from the specified map as entries for the {@link AbstractCard#purchaseUris() purchaseUris} map. Nulls are not permitted as keys or values, but parameter itself can be null
     * @param entries The entries that will be added to the purchaseUris map
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("purchase_uris")
    public final Builder purchaseUris(@Nullable Map<String, ? extends String> entries) {
      if (entries == null) {
        this.purchaseUris = null;
        return this;
      }
      this.purchaseUris = ImmutableMap.builder();
      return putAllPurchaseUris(entries);
    }

    /**
     * Put all mappings from the specified map as entries to {@link AbstractCard#purchaseUris() purchaseUris} map. Nulls are not permitted
     * @param entries The entries that will be added to the purchaseUris map
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putAllPurchaseUris(Map<String, ? extends String> entries) {
      if (this.purchaseUris == null) {
        this.purchaseUris = ImmutableMap.builder();
      }
      this.purchaseUris.putAll(entries);
      return this;
    }

    /**
     * Put one entry to the {@link AbstractCard#relatedUris() relatedUris} map.
     * @param key The key in the relatedUris map
     * @param value The associated value in the relatedUris map
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putRelatedUris(String key, String value) {
      if (this.relatedUris == null) {
        this.relatedUris = ImmutableMap.builder();
      }
      this.relatedUris.put(key, value);
      return this;
    }

    /**
     * Put one entry to the {@link AbstractCard#relatedUris() relatedUris} map. Nulls are not permitted
     * @param entry The key and value entry
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putRelatedUris(Map.Entry<String, ? extends String> entry) {
      if (this.relatedUris == null) {
        this.relatedUris = ImmutableMap.builder();
      }
      this.relatedUris.put(entry);
      return this;
    }

    /**
     * Sets or replaces all mappings from the specified map as entries for the {@link AbstractCard#relatedUris() relatedUris} map. Nulls are not permitted as keys or values, but parameter itself can be null
     * @param entries The entries that will be added to the relatedUris map
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("related_uris")
    public final Builder relatedUris(@Nullable Map<String, ? extends String> entries) {
      if (entries == null) {
        this.relatedUris = null;
        return this;
      }
      this.relatedUris = ImmutableMap.builder();
      return putAllRelatedUris(entries);
    }

    /**
     * Put all mappings from the specified map as entries to {@link AbstractCard#relatedUris() relatedUris} map. Nulls are not permitted
     * @param entries The entries that will be added to the relatedUris map
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder putAllRelatedUris(Map<String, ? extends String> entries) {
      if (this.relatedUris == null) {
        this.relatedUris = ImmutableMap.builder();
      }
      this.relatedUris.putAll(entries);
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
          multiverseIds == null ? null : multiverseIds.build(),
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
          colors == null ? null : colors.build(),
          colorIndicator == null ? null : colorIndicator.build(),
          colorIdentity == null ? null : colorIdentity.build(),
          allParts == null ? null : allParts.build(),
          cardFaces == null ? null : cardFaces.build(),
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
          purchaseUris == null ? null : purchaseUris.build(),
          relatedUris == null ? null : relatedUris.build());
    }
  }
}
