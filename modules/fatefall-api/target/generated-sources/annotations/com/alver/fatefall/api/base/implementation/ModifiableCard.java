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
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.immutables.value.Generated;

/**
 * A modifiable implementation of the {@link com.alver.fatefall.api.base.Card Card} type.
 * <p>Use the {@link #create()} static factory methods to create new instances.
 * Use the {@link #toImmutable()} method to convert to canonical immutable instances.
 * <p><em>ModifiableCard is not thread-safe</em>
 * @see ImmutableCard
 */
@Generated(from = "com.alver.fatefall.api.base.Card", generator = "Modifiables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated({"Modifiables.generator", "com.alver.fatefall.api.base.Card"})
public final class ModifiableCard extends Card {
  private @Nullable UUID id;
  private @Nullable String name;
  private @Nullable UUID oracleId;
  private @Nullable ArrayList<Integer> multiverseIds = null;
  private @Nullable Integer mtgoId;
  private @Nullable Integer arenaId;
  private @Nullable Integer mtgoFoilId;
  private @Nullable String uri;
  private @Nullable String scryfallUri;
  private @Nullable String printsSearchUri;
  private @Nullable String rulingsUri;
  private @Nullable Layouts layout;
  private @Nullable Double cmc;
  private @Nullable String typeLine;
  private @Nullable String oracleText;
  private @Nullable String manaCost;
  private @Nullable String power;
  private @Nullable String toughness;
  private @Nullable String loyalty;
  private @Nullable String lifeModifier;
  private @Nullable String handModifier;
  private @Nullable ArrayList<Colors> colors = null;
  private @Nullable ArrayList<Colors> colorIndicator = null;
  private @Nullable ArrayList<Colors> colorIdentity = null;
  private @Nullable ArrayList<RelatedCards> allParts = null;
  private @Nullable ArrayList<CardFace> cardFaces = null;
  private @Nullable Legality legalities;
  private @Nullable Boolean reserved;
  private @Nullable Integer edhrecRank;
  private @Nullable String setCode;
  private @Nullable String setName;
  private @Nullable String collectorNumber;
  private @Nullable String setSearchUri;
  private @Nullable String scryfallSetUri;
  private @Nullable ImageUri imageUris;
  private @Nullable Boolean highresImage;
  private @Nullable Boolean reprint;
  private @Nullable Boolean digital;
  private @Nullable Rarity rarity;
  private @Nullable String flavorText;
  private @Nullable String artist;
  private @Nullable UUID illustrationId;
  private @Nullable String frame;
  private @Nullable Boolean fullArt;
  private @Nullable String watermark;
  private @Nullable BorderColors borderColor;
  private @Nullable Integer storySpotlightNumber;
  private @Nullable String storySpotlightUri;
  private @Nullable Boolean timeshifted;
  private @Nullable Boolean colorshifted;
  private @Nullable Boolean futureshifted;
  private @Nullable Map<String, String> purchaseUris = null;
  private @Nullable Map<String, String> relatedUris = null;

  private ModifiableCard() {}

  /**
   * Construct a modifiable instance of {@code Card}.
   * @return A new modifiable instance
   */
  public static ModifiableCard create() {
    return new ModifiableCard();
  }

  /**
   * @return value of {@code id} attribute, may be {@code null}
   */
  @JsonProperty("id")
  @Override
  public final @Nullable UUID getId() {
    return id;
  }

  /**
   * @return value of {@code name} attribute, may be {@code null}
   */
  @JsonProperty("name")
  @Override
  public final @Nullable String getName() {
    return name;
  }

  /**
   * @return value of {@code oracleId} attribute, may be {@code null}
   */
  @JsonProperty("oracleId")
  @Override
  public final @Nullable UUID getOracleId() {
    return oracleId;
  }

  /**
   * @return modifiable list {@code multiverseIds}
   */
  @JsonProperty("multiverseIds")
  @Override
  public final @Nullable List<Integer> getMultiverseIds() {
    return multiverseIds;
  }

  /**
   * @return value of {@code mtgoId} attribute, may be {@code null}
   */
  @JsonProperty("mtgoId")
  @Override
  public final @Nullable Integer getMtgoId() {
    return mtgoId;
  }

  /**
   * @return value of {@code arenaId} attribute, may be {@code null}
   */
  @JsonProperty("arenaId")
  @Override
  public final @Nullable Integer getArenaId() {
    return arenaId;
  }

  /**
   * @return value of {@code mtgoFoilId} attribute, may be {@code null}
   */
  @JsonProperty("mtgoFoilId")
  @Override
  public final @Nullable Integer getMtgoFoilId() {
    return mtgoFoilId;
  }

  /**
   * @return value of {@code uri} attribute, may be {@code null}
   */
  @JsonProperty("uri")
  @Override
  public final @Nullable String getUri() {
    return uri;
  }

  /**
   * @return value of {@code scryfallUri} attribute, may be {@code null}
   */
  @JsonProperty("scryfallUri")
  @Override
  public final @Nullable String getScryfallUri() {
    return scryfallUri;
  }

  /**
   * @return value of {@code printsSearchUri} attribute, may be {@code null}
   */
  @JsonProperty("printsSearchUri")
  @Override
  public final @Nullable String getPrintsSearchUri() {
    return printsSearchUri;
  }

  /**
   * @return value of {@code rulingsUri} attribute, may be {@code null}
   */
  @JsonProperty("rulingsUri")
  @Override
  public final @Nullable String getRulingsUri() {
    return rulingsUri;
  }

  /**
   * @return value of {@code layout} attribute, may be {@code null}
   */
  @JsonProperty("layout")
  @Override
  public final @Nullable Layouts getLayout() {
    return layout;
  }

  /**
   * @return value of {@code cmc} attribute, may be {@code null}
   */
  @JsonProperty("cmc")
  @Override
  public final @Nullable Double getCmc() {
    return cmc;
  }

  /**
   * @return value of {@code typeLine} attribute, may be {@code null}
   */
  @JsonProperty("typeLine")
  @Override
  public final @Nullable String getTypeLine() {
    return typeLine;
  }

  /**
   * @return value of {@code oracleText} attribute, may be {@code null}
   */
  @JsonProperty("oracleText")
  @Override
  public final @Nullable String getOracleText() {
    return oracleText;
  }

  /**
   * @return value of {@code manaCost} attribute, may be {@code null}
   */
  @JsonProperty("manaCost")
  @Override
  public final @Nullable String getManaCost() {
    return manaCost;
  }

  /**
   * @return value of {@code power} attribute, may be {@code null}
   */
  @JsonProperty("power")
  @Override
  public final @Nullable String getPower() {
    return power;
  }

  /**
   * @return value of {@code toughness} attribute, may be {@code null}
   */
  @JsonProperty("toughness")
  @Override
  public final @Nullable String getToughness() {
    return toughness;
  }

  /**
   * @return value of {@code loyalty} attribute, may be {@code null}
   */
  @JsonProperty("loyalty")
  @Override
  public final @Nullable String getLoyalty() {
    return loyalty;
  }

  /**
   * @return value of {@code lifeModifier} attribute, may be {@code null}
   */
  @JsonProperty("lifeModifier")
  @Override
  public final @Nullable String getLifeModifier() {
    return lifeModifier;
  }

  /**
   * @return value of {@code handModifier} attribute, may be {@code null}
   */
  @JsonProperty("handModifier")
  @Override
  public final @Nullable String getHandModifier() {
    return handModifier;
  }

  /**
   * @return modifiable list {@code colors}
   */
  @JsonProperty("colors")
  @Override
  public final @Nullable List<Colors> getColors() {
    return colors;
  }

  /**
   * @return modifiable list {@code colorIndicator}
   */
  @JsonProperty("colorIndicator")
  @Override
  public final @Nullable List<Colors> getColorIndicator() {
    return colorIndicator;
  }

  /**
   * @return modifiable list {@code colorIdentity}
   */
  @JsonProperty("colorIdentity")
  @Override
  public final @Nullable List<Colors> getColorIdentity() {
    return colorIdentity;
  }

  /**
   * @return modifiable list {@code allParts}
   */
  @JsonProperty("allParts")
  @Override
  public final @Nullable List<RelatedCards> getAllParts() {
    return allParts;
  }

  /**
   * @return modifiable list {@code cardFaces}
   */
  @JsonProperty("card_faces")
  @Override
  public final @Nullable List<CardFace> getCardFaces() {
    return cardFaces;
  }

  /**
   * @return value of {@code legalities} attribute, may be {@code null}
   */
  @JsonProperty("legalities")
  @Override
  public final @Nullable Legality getLegalities() {
    return legalities;
  }

  /**
   * @return value of {@code reserved} attribute, may be {@code null}
   */
  @JsonProperty("reserved")
  @Override
  public final @Nullable Boolean getReserved() {
    return reserved;
  }

  /**
   * @return value of {@code edhrecRank} attribute, may be {@code null}
   */
  @JsonProperty("edhrecRank")
  @Override
  public final @Nullable Integer getEdhrecRank() {
    return edhrecRank;
  }

  /**
   * @return value of {@code setCode} attribute, may be {@code null}
   */
  @JsonProperty("setCode")
  @Override
  public final @Nullable String getSetCode() {
    return setCode;
  }

  /**
   * @return value of {@code setName} attribute, may be {@code null}
   */
  @JsonProperty("setName")
  @Override
  public final @Nullable String getSetName() {
    return setName;
  }

  /**
   * @return value of {@code collectorNumber} attribute, may be {@code null}
   */
  @JsonProperty("collectorNumber")
  @Override
  public final @Nullable String getCollectorNumber() {
    return collectorNumber;
  }

  /**
   * @return value of {@code setSearchUri} attribute, may be {@code null}
   */
  @JsonProperty("setSearchUri")
  @Override
  public final @Nullable String getSetSearchUri() {
    return setSearchUri;
  }

  /**
   * @return value of {@code scryfallSetUri} attribute, may be {@code null}
   */
  @JsonProperty("scryfallSetUri")
  @Override
  public final @Nullable String getScryfallSetUri() {
    return scryfallSetUri;
  }

  /**
   * @return value of {@code imageUris} attribute, may be {@code null}
   */
  @JsonProperty("image_uris")
  @Override
  public final @Nullable ImageUri getImageUris() {
    return imageUris;
  }

  /**
   * @return value of {@code highresImage} attribute, may be {@code null}
   */
  @JsonProperty("highresImage")
  @Override
  public final @Nullable Boolean getHighresImage() {
    return highresImage;
  }

  /**
   * @return value of {@code reprint} attribute, may be {@code null}
   */
  @JsonProperty("reprint")
  @Override
  public final @Nullable Boolean getReprint() {
    return reprint;
  }

  /**
   * @return value of {@code digital} attribute, may be {@code null}
   */
  @JsonProperty("digital")
  @Override
  public final @Nullable Boolean getDigital() {
    return digital;
  }

  /**
   * @return value of {@code rarity} attribute, may be {@code null}
   */
  @JsonProperty("rarity")
  @Override
  public final @Nullable Rarity getRarity() {
    return rarity;
  }

  /**
   * @return value of {@code flavorText} attribute, may be {@code null}
   */
  @JsonProperty("flavorText")
  @Override
  public final @Nullable String getFlavorText() {
    return flavorText;
  }

  /**
   * @return value of {@code artist} attribute, may be {@code null}
   */
  @JsonProperty("artist")
  @Override
  public final @Nullable String getArtist() {
    return artist;
  }

  /**
   * @return value of {@code illustrationId} attribute, may be {@code null}
   */
  @JsonProperty("illustrationId")
  @Override
  public final @Nullable UUID getIllustrationId() {
    return illustrationId;
  }

  /**
   * @return value of {@code frame} attribute, may be {@code null}
   */
  @JsonProperty("frame")
  @Override
  public final @Nullable String getFrame() {
    return frame;
  }

  /**
   * @return value of {@code fullArt} attribute, may be {@code null}
   */
  @JsonProperty("fullArt")
  @Override
  public final @Nullable Boolean getFullArt() {
    return fullArt;
  }

  /**
   * @return value of {@code watermark} attribute, may be {@code null}
   */
  @JsonProperty("watermark")
  @Override
  public final @Nullable String getWatermark() {
    return watermark;
  }

  /**
   * @return value of {@code borderColor} attribute, may be {@code null}
   */
  @JsonProperty("borderColor")
  @Override
  public final @Nullable BorderColors getBorderColor() {
    return borderColor;
  }

  /**
   * @return value of {@code storySpotlightNumber} attribute, may be {@code null}
   */
  @JsonProperty("storySpotlightNumber")
  @Override
  public final @Nullable Integer getStorySpotlightNumber() {
    return storySpotlightNumber;
  }

  /**
   * @return value of {@code storySpotlightUri} attribute, may be {@code null}
   */
  @JsonProperty("storySpotlightUri")
  @Override
  public final @Nullable String getStorySpotlightUri() {
    return storySpotlightUri;
  }

  /**
   * @return value of {@code timeshifted} attribute, may be {@code null}
   */
  @JsonProperty("timeshifted")
  @Override
  public final @Nullable Boolean getTimeshifted() {
    return timeshifted;
  }

  /**
   * @return value of {@code colorshifted} attribute, may be {@code null}
   */
  @JsonProperty("colorshifted")
  @Override
  public final @Nullable Boolean getColorshifted() {
    return colorshifted;
  }

  /**
   * @return value of {@code futureshifted} attribute, may be {@code null}
   */
  @JsonProperty("futureshifted")
  @Override
  public final @Nullable Boolean getFutureshifted() {
    return futureshifted;
  }

  /**
   * @return value of {@code purchaseUris} attribute, may be {@code null}
   */
  @JsonProperty("purchaseUris")
  @Override
  public final @Nullable Map<String, String> getPurchaseUris() {
    return purchaseUris;
  }

  /**
   * @return value of {@code relatedUris} attribute, may be {@code null}
   */
  @JsonProperty("relatedUris")
  @Override
  public final @Nullable Map<String, String> getRelatedUris() {
    return relatedUris;
  }

  /**
   * Clears the object by setting all attributes to their initial values.
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard clear() {
    id = null;
    name = null;
    oracleId = null;
    multiverseIds = null;
    mtgoId = null;
    arenaId = null;
    mtgoFoilId = null;
    uri = null;
    scryfallUri = null;
    printsSearchUri = null;
    rulingsUri = null;
    layout = null;
    cmc = null;
    typeLine = null;
    oracleText = null;
    manaCost = null;
    power = null;
    toughness = null;
    loyalty = null;
    lifeModifier = null;
    handModifier = null;
    colors = null;
    colorIndicator = null;
    colorIdentity = null;
    allParts = null;
    cardFaces = null;
    legalities = null;
    reserved = null;
    edhrecRank = null;
    setCode = null;
    setName = null;
    collectorNumber = null;
    setSearchUri = null;
    scryfallSetUri = null;
    imageUris = null;
    highresImage = null;
    reprint = null;
    digital = null;
    rarity = null;
    flavorText = null;
    artist = null;
    illustrationId = null;
    frame = null;
    fullArt = null;
    watermark = null;
    borderColor = null;
    storySpotlightNumber = null;
    storySpotlightUri = null;
    timeshifted = null;
    colorshifted = null;
    futureshifted = null;
    purchaseUris = null;
    relatedUris = null;
    return this;
  }

  /**
   * Fill this modifiable instance with attribute values from the provided {@link com.alver.fatefall.api.base.Card} instance.
   * Regular attribute values will be overridden, i.e. replaced with ones of an instance.
   * Any of the instance's absent optional values will not be copied (will not override current values).
   * Collection elements and entries will be added, not replaced.
   * @param instance The instance from which to copy values
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard from(Card instance) {
    Objects.requireNonNull(instance, "instance");
    if (instance instanceof ModifiableCard) {
      from((ModifiableCard) instance);
      return this;
    }
    @Nullable UUID idValue = instance.getId();
    if (idValue != null) {
      setId(idValue);
    }
    @Nullable String nameValue = instance.getName();
    if (nameValue != null) {
      setName(nameValue);
    }
    @Nullable UUID oracleIdValue = instance.getOracleId();
    if (oracleIdValue != null) {
      setOracleId(oracleIdValue);
    }
    addAllMultiverseIds(instance.getMultiverseIds());
    @Nullable Integer mtgoIdValue = instance.getMtgoId();
    if (mtgoIdValue != null) {
      setMtgoId(mtgoIdValue);
    }
    @Nullable Integer arenaIdValue = instance.getArenaId();
    if (arenaIdValue != null) {
      setArenaId(arenaIdValue);
    }
    @Nullable Integer mtgoFoilIdValue = instance.getMtgoFoilId();
    if (mtgoFoilIdValue != null) {
      setMtgoFoilId(mtgoFoilIdValue);
    }
    @Nullable String uriValue = instance.getUri();
    if (uriValue != null) {
      setUri(uriValue);
    }
    @Nullable String scryfallUriValue = instance.getScryfallUri();
    if (scryfallUriValue != null) {
      setScryfallUri(scryfallUriValue);
    }
    @Nullable String printsSearchUriValue = instance.getPrintsSearchUri();
    if (printsSearchUriValue != null) {
      setPrintsSearchUri(printsSearchUriValue);
    }
    @Nullable String rulingsUriValue = instance.getRulingsUri();
    if (rulingsUriValue != null) {
      setRulingsUri(rulingsUriValue);
    }
    @Nullable Layouts layoutValue = instance.getLayout();
    if (layoutValue != null) {
      setLayout(layoutValue);
    }
    @Nullable Double cmcValue = instance.getCmc();
    if (cmcValue != null) {
      setCmc(cmcValue);
    }
    @Nullable String typeLineValue = instance.getTypeLine();
    if (typeLineValue != null) {
      setTypeLine(typeLineValue);
    }
    @Nullable String oracleTextValue = instance.getOracleText();
    if (oracleTextValue != null) {
      setOracleText(oracleTextValue);
    }
    @Nullable String manaCostValue = instance.getManaCost();
    if (manaCostValue != null) {
      setManaCost(manaCostValue);
    }
    @Nullable String powerValue = instance.getPower();
    if (powerValue != null) {
      setPower(powerValue);
    }
    @Nullable String toughnessValue = instance.getToughness();
    if (toughnessValue != null) {
      setToughness(toughnessValue);
    }
    @Nullable String loyaltyValue = instance.getLoyalty();
    if (loyaltyValue != null) {
      setLoyalty(loyaltyValue);
    }
    @Nullable String lifeModifierValue = instance.getLifeModifier();
    if (lifeModifierValue != null) {
      setLifeModifier(lifeModifierValue);
    }
    @Nullable String handModifierValue = instance.getHandModifier();
    if (handModifierValue != null) {
      setHandModifier(handModifierValue);
    }
    addAllColors(instance.getColors());
    addAllColorIndicator(instance.getColorIndicator());
    addAllColorIdentity(instance.getColorIdentity());
    addAllAllParts(instance.getAllParts());
    addAllCardFaces(instance.getCardFaces());
    @Nullable Legality legalitiesValue = instance.getLegalities();
    if (legalitiesValue != null) {
      setLegalities(legalitiesValue);
    }
    @Nullable Boolean reservedValue = instance.getReserved();
    if (reservedValue != null) {
      setReserved(reservedValue);
    }
    @Nullable Integer edhrecRankValue = instance.getEdhrecRank();
    if (edhrecRankValue != null) {
      setEdhrecRank(edhrecRankValue);
    }
    @Nullable String setCodeValue = instance.getSetCode();
    if (setCodeValue != null) {
      setSetCode(setCodeValue);
    }
    @Nullable String setNameValue = instance.getSetName();
    if (setNameValue != null) {
      setSetName(setNameValue);
    }
    @Nullable String collectorNumberValue = instance.getCollectorNumber();
    if (collectorNumberValue != null) {
      setCollectorNumber(collectorNumberValue);
    }
    @Nullable String setSearchUriValue = instance.getSetSearchUri();
    if (setSearchUriValue != null) {
      setSetSearchUri(setSearchUriValue);
    }
    @Nullable String scryfallSetUriValue = instance.getScryfallSetUri();
    if (scryfallSetUriValue != null) {
      setScryfallSetUri(scryfallSetUriValue);
    }
    @Nullable ImageUri imageUrisValue = instance.getImageUris();
    if (imageUrisValue != null) {
      setImageUris(imageUrisValue);
    }
    @Nullable Boolean highresImageValue = instance.getHighresImage();
    if (highresImageValue != null) {
      setHighresImage(highresImageValue);
    }
    @Nullable Boolean reprintValue = instance.getReprint();
    if (reprintValue != null) {
      setReprint(reprintValue);
    }
    @Nullable Boolean digitalValue = instance.getDigital();
    if (digitalValue != null) {
      setDigital(digitalValue);
    }
    @Nullable Rarity rarityValue = instance.getRarity();
    if (rarityValue != null) {
      setRarity(rarityValue);
    }
    @Nullable String flavorTextValue = instance.getFlavorText();
    if (flavorTextValue != null) {
      setFlavorText(flavorTextValue);
    }
    @Nullable String artistValue = instance.getArtist();
    if (artistValue != null) {
      setArtist(artistValue);
    }
    @Nullable UUID illustrationIdValue = instance.getIllustrationId();
    if (illustrationIdValue != null) {
      setIllustrationId(illustrationIdValue);
    }
    @Nullable String frameValue = instance.getFrame();
    if (frameValue != null) {
      setFrame(frameValue);
    }
    @Nullable Boolean fullArtValue = instance.getFullArt();
    if (fullArtValue != null) {
      setFullArt(fullArtValue);
    }
    @Nullable String watermarkValue = instance.getWatermark();
    if (watermarkValue != null) {
      setWatermark(watermarkValue);
    }
    @Nullable BorderColors borderColorValue = instance.getBorderColor();
    if (borderColorValue != null) {
      setBorderColor(borderColorValue);
    }
    @Nullable Integer storySpotlightNumberValue = instance.getStorySpotlightNumber();
    if (storySpotlightNumberValue != null) {
      setStorySpotlightNumber(storySpotlightNumberValue);
    }
    @Nullable String storySpotlightUriValue = instance.getStorySpotlightUri();
    if (storySpotlightUriValue != null) {
      setStorySpotlightUri(storySpotlightUriValue);
    }
    @Nullable Boolean timeshiftedValue = instance.getTimeshifted();
    if (timeshiftedValue != null) {
      setTimeshifted(timeshiftedValue);
    }
    @Nullable Boolean colorshiftedValue = instance.getColorshifted();
    if (colorshiftedValue != null) {
      setColorshifted(colorshiftedValue);
    }
    @Nullable Boolean futureshiftedValue = instance.getFutureshifted();
    if (futureshiftedValue != null) {
      setFutureshifted(futureshiftedValue);
    }
    putAllPurchaseUris(instance.getPurchaseUris());
    putAllRelatedUris(instance.getRelatedUris());
    return this;
  }

  /**
   * Fill this modifiable instance with attribute values from the provided {@link com.alver.fatefall.api.base.Card} instance.
   * Regular attribute values will be overridden, i.e. replaced with ones of an instance.
   * Any of the instance's absent optional values will not be copied (will not override current values).
   * Collection elements and entries will be added, not replaced.
   * @param instance The instance from which to copy values
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard from(ModifiableCard instance) {
    Objects.requireNonNull(instance, "instance");
    @Nullable UUID idValue = instance.getId();
    if (idValue != null) {
      setId(idValue);
    }
    @Nullable String nameValue = instance.getName();
    if (nameValue != null) {
      setName(nameValue);
    }
    @Nullable UUID oracleIdValue = instance.getOracleId();
    if (oracleIdValue != null) {
      setOracleId(oracleIdValue);
    }
    addAllMultiverseIds(instance.getMultiverseIds());
    @Nullable Integer mtgoIdValue = instance.getMtgoId();
    if (mtgoIdValue != null) {
      setMtgoId(mtgoIdValue);
    }
    @Nullable Integer arenaIdValue = instance.getArenaId();
    if (arenaIdValue != null) {
      setArenaId(arenaIdValue);
    }
    @Nullable Integer mtgoFoilIdValue = instance.getMtgoFoilId();
    if (mtgoFoilIdValue != null) {
      setMtgoFoilId(mtgoFoilIdValue);
    }
    @Nullable String uriValue = instance.getUri();
    if (uriValue != null) {
      setUri(uriValue);
    }
    @Nullable String scryfallUriValue = instance.getScryfallUri();
    if (scryfallUriValue != null) {
      setScryfallUri(scryfallUriValue);
    }
    @Nullable String printsSearchUriValue = instance.getPrintsSearchUri();
    if (printsSearchUriValue != null) {
      setPrintsSearchUri(printsSearchUriValue);
    }
    @Nullable String rulingsUriValue = instance.getRulingsUri();
    if (rulingsUriValue != null) {
      setRulingsUri(rulingsUriValue);
    }
    @Nullable Layouts layoutValue = instance.getLayout();
    if (layoutValue != null) {
      setLayout(layoutValue);
    }
    @Nullable Double cmcValue = instance.getCmc();
    if (cmcValue != null) {
      setCmc(cmcValue);
    }
    @Nullable String typeLineValue = instance.getTypeLine();
    if (typeLineValue != null) {
      setTypeLine(typeLineValue);
    }
    @Nullable String oracleTextValue = instance.getOracleText();
    if (oracleTextValue != null) {
      setOracleText(oracleTextValue);
    }
    @Nullable String manaCostValue = instance.getManaCost();
    if (manaCostValue != null) {
      setManaCost(manaCostValue);
    }
    @Nullable String powerValue = instance.getPower();
    if (powerValue != null) {
      setPower(powerValue);
    }
    @Nullable String toughnessValue = instance.getToughness();
    if (toughnessValue != null) {
      setToughness(toughnessValue);
    }
    @Nullable String loyaltyValue = instance.getLoyalty();
    if (loyaltyValue != null) {
      setLoyalty(loyaltyValue);
    }
    @Nullable String lifeModifierValue = instance.getLifeModifier();
    if (lifeModifierValue != null) {
      setLifeModifier(lifeModifierValue);
    }
    @Nullable String handModifierValue = instance.getHandModifier();
    if (handModifierValue != null) {
      setHandModifier(handModifierValue);
    }
    addAllColors(instance.getColors());
    addAllColorIndicator(instance.getColorIndicator());
    addAllColorIdentity(instance.getColorIdentity());
    addAllAllParts(instance.getAllParts());
    addAllCardFaces(instance.getCardFaces());
    @Nullable Legality legalitiesValue = instance.getLegalities();
    if (legalitiesValue != null) {
      setLegalities(legalitiesValue);
    }
    @Nullable Boolean reservedValue = instance.getReserved();
    if (reservedValue != null) {
      setReserved(reservedValue);
    }
    @Nullable Integer edhrecRankValue = instance.getEdhrecRank();
    if (edhrecRankValue != null) {
      setEdhrecRank(edhrecRankValue);
    }
    @Nullable String setCodeValue = instance.getSetCode();
    if (setCodeValue != null) {
      setSetCode(setCodeValue);
    }
    @Nullable String setNameValue = instance.getSetName();
    if (setNameValue != null) {
      setSetName(setNameValue);
    }
    @Nullable String collectorNumberValue = instance.getCollectorNumber();
    if (collectorNumberValue != null) {
      setCollectorNumber(collectorNumberValue);
    }
    @Nullable String setSearchUriValue = instance.getSetSearchUri();
    if (setSearchUriValue != null) {
      setSetSearchUri(setSearchUriValue);
    }
    @Nullable String scryfallSetUriValue = instance.getScryfallSetUri();
    if (scryfallSetUriValue != null) {
      setScryfallSetUri(scryfallSetUriValue);
    }
    @Nullable ImageUri imageUrisValue = instance.getImageUris();
    if (imageUrisValue != null) {
      setImageUris(imageUrisValue);
    }
    @Nullable Boolean highresImageValue = instance.getHighresImage();
    if (highresImageValue != null) {
      setHighresImage(highresImageValue);
    }
    @Nullable Boolean reprintValue = instance.getReprint();
    if (reprintValue != null) {
      setReprint(reprintValue);
    }
    @Nullable Boolean digitalValue = instance.getDigital();
    if (digitalValue != null) {
      setDigital(digitalValue);
    }
    @Nullable Rarity rarityValue = instance.getRarity();
    if (rarityValue != null) {
      setRarity(rarityValue);
    }
    @Nullable String flavorTextValue = instance.getFlavorText();
    if (flavorTextValue != null) {
      setFlavorText(flavorTextValue);
    }
    @Nullable String artistValue = instance.getArtist();
    if (artistValue != null) {
      setArtist(artistValue);
    }
    @Nullable UUID illustrationIdValue = instance.getIllustrationId();
    if (illustrationIdValue != null) {
      setIllustrationId(illustrationIdValue);
    }
    @Nullable String frameValue = instance.getFrame();
    if (frameValue != null) {
      setFrame(frameValue);
    }
    @Nullable Boolean fullArtValue = instance.getFullArt();
    if (fullArtValue != null) {
      setFullArt(fullArtValue);
    }
    @Nullable String watermarkValue = instance.getWatermark();
    if (watermarkValue != null) {
      setWatermark(watermarkValue);
    }
    @Nullable BorderColors borderColorValue = instance.getBorderColor();
    if (borderColorValue != null) {
      setBorderColor(borderColorValue);
    }
    @Nullable Integer storySpotlightNumberValue = instance.getStorySpotlightNumber();
    if (storySpotlightNumberValue != null) {
      setStorySpotlightNumber(storySpotlightNumberValue);
    }
    @Nullable String storySpotlightUriValue = instance.getStorySpotlightUri();
    if (storySpotlightUriValue != null) {
      setStorySpotlightUri(storySpotlightUriValue);
    }
    @Nullable Boolean timeshiftedValue = instance.getTimeshifted();
    if (timeshiftedValue != null) {
      setTimeshifted(timeshiftedValue);
    }
    @Nullable Boolean colorshiftedValue = instance.getColorshifted();
    if (colorshiftedValue != null) {
      setColorshifted(colorshiftedValue);
    }
    @Nullable Boolean futureshiftedValue = instance.getFutureshifted();
    if (futureshiftedValue != null) {
      setFutureshifted(futureshiftedValue);
    }
    putAllPurchaseUris(instance.getPurchaseUris());
    putAllRelatedUris(instance.getRelatedUris());
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getId() id} attribute.
   * @param id The value for id, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setId(@Nullable UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getName() name} attribute.
   * @param name The value for name, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setName(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getOracleId() oracleId} attribute.
   * @param oracleId The value for oracleId, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setOracleId(@Nullable UUID oracleId) {
    this.oracleId = oracleId;
    return this;
  }

  /**
   * Adds one element to {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds} list.
   * @param element The multiverseIds element
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addMultiverseIds(int element) {
    if (this.multiverseIds == null) {
      this.multiverseIds = new ArrayList<Integer>();
    }
    this.multiverseIds.add(element);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds} list.
   * @param elements An array of multiverseIds elements
   * @return {@code this} for use in a chained invocation
   */
  public final ModifiableCard addMultiverseIds(int... elements) {
    for (int e : elements) {
      addMultiverseIds(Objects.requireNonNull(e, "multiverseIds element"));
    }
    return this;
  }

  /**
   * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds} list.
   * @param elements An iterable of multiverseIds elements, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setMultiverseIds(@Nullable Iterable<Integer> elements) {
    if (elements == null) {
      this.multiverseIds = null;
      return this;
    }
    if (this.multiverseIds == null) {
      this.multiverseIds = new ArrayList<Integer>();
    } else {
      this.multiverseIds.clear();
    }
    addAllMultiverseIds(elements);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getMultiverseIds() multiverseIds} list.
   * @param elements An iterable of multiverseIds elements
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addAllMultiverseIds(Iterable<Integer> elements) {
    if (elements == null) return this;
    if (this.multiverseIds == null) {
      this.multiverseIds = new ArrayList<Integer>();
    }
    for (int e : elements) {
      addMultiverseIds(e);
    }
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getMtgoId() mtgoId} attribute.
   * @param mtgoId The value for mtgoId, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setMtgoId(@Nullable Integer mtgoId) {
    this.mtgoId = mtgoId;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getArenaId() arenaId} attribute.
   * @param arenaId The value for arenaId, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setArenaId(@Nullable Integer arenaId) {
    this.arenaId = arenaId;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getMtgoFoilId() mtgoFoilId} attribute.
   * @param mtgoFoilId The value for mtgoFoilId, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setMtgoFoilId(@Nullable Integer mtgoFoilId) {
    this.mtgoFoilId = mtgoFoilId;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getUri() uri} attribute.
   * @param uri The value for uri, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setUri(@Nullable String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getScryfallUri() scryfallUri} attribute.
   * @param scryfallUri The value for scryfallUri, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setScryfallUri(@Nullable String scryfallUri) {
    this.scryfallUri = scryfallUri;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getPrintsSearchUri() printsSearchUri} attribute.
   * @param printsSearchUri The value for printsSearchUri, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setPrintsSearchUri(@Nullable String printsSearchUri) {
    this.printsSearchUri = printsSearchUri;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getRulingsUri() rulingsUri} attribute.
   * @param rulingsUri The value for rulingsUri, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setRulingsUri(@Nullable String rulingsUri) {
    this.rulingsUri = rulingsUri;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getLayout() layout} attribute.
   * @param layout The value for layout, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setLayout(@Nullable Layouts layout) {
    this.layout = layout;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getCmc() cmc} attribute.
   * @param cmc The value for cmc, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setCmc(@Nullable Double cmc) {
    this.cmc = cmc;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getTypeLine() typeLine} attribute.
   * @param typeLine The value for typeLine, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setTypeLine(@Nullable String typeLine) {
    this.typeLine = typeLine;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getOracleText() oracleText} attribute.
   * @param oracleText The value for oracleText, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setOracleText(@Nullable String oracleText) {
    this.oracleText = oracleText;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getManaCost() manaCost} attribute.
   * @param manaCost The value for manaCost, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setManaCost(@Nullable String manaCost) {
    this.manaCost = manaCost;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getPower() power} attribute.
   * @param power The value for power, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setPower(@Nullable String power) {
    this.power = power;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getToughness() toughness} attribute.
   * @param toughness The value for toughness, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setToughness(@Nullable String toughness) {
    this.toughness = toughness;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getLoyalty() loyalty} attribute.
   * @param loyalty The value for loyalty, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setLoyalty(@Nullable String loyalty) {
    this.loyalty = loyalty;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getLifeModifier() lifeModifier} attribute.
   * @param lifeModifier The value for lifeModifier, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setLifeModifier(@Nullable String lifeModifier) {
    this.lifeModifier = lifeModifier;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getHandModifier() handModifier} attribute.
   * @param handModifier The value for handModifier, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setHandModifier(@Nullable String handModifier) {
    this.handModifier = handModifier;
    return this;
  }

  /**
   * Adds one element to {@link com.alver.fatefall.api.base.Card#getColors() colors} list.
   * @param element The colors element
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addColors(Colors element) {
    if (this.colors == null) {
      this.colors = new ArrayList<Colors>();
    }
    Objects.requireNonNull(element, "colors element");
    this.colors.add(element);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getColors() colors} list.
   * @param elements An array of colors elements
   * @return {@code this} for use in a chained invocation
   */
  public final ModifiableCard addColors(Colors... elements) {
    for (Colors e : elements) {
      addColors(e);
    }
    return this;
  }

  /**
   * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getColors() colors} list.
   * @param elements An iterable of colors elements, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setColors(@Nullable Iterable<? extends Colors> elements) {
    if (elements == null) {
      this.colors = null;
      return this;
    }
    if (this.colors == null) {
      this.colors = new ArrayList<Colors>();
    } else {
      this.colors.clear();
    }
    addAllColors(elements);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getColors() colors} list.
   * @param elements An iterable of colors elements
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addAllColors(Iterable<? extends Colors> elements) {
    if (elements == null) return this;
    if (this.colors == null) {
      this.colors = new ArrayList<Colors>();
    }
    for (Colors e : elements) {
      addColors(e);
    }
    return this;
  }

  /**
   * Adds one element to {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator} list.
   * @param element The colorIndicator element
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addColorIndicator(Colors element) {
    if (this.colorIndicator == null) {
      this.colorIndicator = new ArrayList<Colors>();
    }
    Objects.requireNonNull(element, "colorIndicator element");
    this.colorIndicator.add(element);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator} list.
   * @param elements An array of colorIndicator elements
   * @return {@code this} for use in a chained invocation
   */
  public final ModifiableCard addColorIndicator(Colors... elements) {
    for (Colors e : elements) {
      addColorIndicator(e);
    }
    return this;
  }

  /**
   * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator} list.
   * @param elements An iterable of colorIndicator elements, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setColorIndicator(@Nullable Iterable<? extends Colors> elements) {
    if (elements == null) {
      this.colorIndicator = null;
      return this;
    }
    if (this.colorIndicator == null) {
      this.colorIndicator = new ArrayList<Colors>();
    } else {
      this.colorIndicator.clear();
    }
    addAllColorIndicator(elements);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getColorIndicator() colorIndicator} list.
   * @param elements An iterable of colorIndicator elements
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addAllColorIndicator(Iterable<? extends Colors> elements) {
    if (elements == null) return this;
    if (this.colorIndicator == null) {
      this.colorIndicator = new ArrayList<Colors>();
    }
    for (Colors e : elements) {
      addColorIndicator(e);
    }
    return this;
  }

  /**
   * Adds one element to {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity} list.
   * @param element The colorIdentity element
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addColorIdentity(Colors element) {
    if (this.colorIdentity == null) {
      this.colorIdentity = new ArrayList<Colors>();
    }
    Objects.requireNonNull(element, "colorIdentity element");
    this.colorIdentity.add(element);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity} list.
   * @param elements An array of colorIdentity elements
   * @return {@code this} for use in a chained invocation
   */
  public final ModifiableCard addColorIdentity(Colors... elements) {
    for (Colors e : elements) {
      addColorIdentity(e);
    }
    return this;
  }

  /**
   * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity} list.
   * @param elements An iterable of colorIdentity elements, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setColorIdentity(@Nullable Iterable<? extends Colors> elements) {
    if (elements == null) {
      this.colorIdentity = null;
      return this;
    }
    if (this.colorIdentity == null) {
      this.colorIdentity = new ArrayList<Colors>();
    } else {
      this.colorIdentity.clear();
    }
    addAllColorIdentity(elements);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getColorIdentity() colorIdentity} list.
   * @param elements An iterable of colorIdentity elements
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addAllColorIdentity(Iterable<? extends Colors> elements) {
    if (elements == null) return this;
    if (this.colorIdentity == null) {
      this.colorIdentity = new ArrayList<Colors>();
    }
    for (Colors e : elements) {
      addColorIdentity(e);
    }
    return this;
  }

  /**
   * Adds one element to {@link com.alver.fatefall.api.base.Card#getAllParts() allParts} list.
   * @param element The allParts element
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addAllParts(RelatedCards element) {
    if (this.allParts == null) {
      this.allParts = new ArrayList<RelatedCards>();
    }
    Objects.requireNonNull(element, "allParts element");
    this.allParts.add(element);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getAllParts() allParts} list.
   * @param elements An array of allParts elements
   * @return {@code this} for use in a chained invocation
   */
  public final ModifiableCard addAllParts(RelatedCards... elements) {
    for (RelatedCards e : elements) {
      addAllParts(e);
    }
    return this;
  }

  /**
   * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getAllParts() allParts} list.
   * @param elements An iterable of allParts elements, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setAllParts(@Nullable Iterable<? extends RelatedCards> elements) {
    if (elements == null) {
      this.allParts = null;
      return this;
    }
    if (this.allParts == null) {
      this.allParts = new ArrayList<RelatedCards>();
    } else {
      this.allParts.clear();
    }
    addAllAllParts(elements);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getAllParts() allParts} list.
   * @param elements An iterable of allParts elements
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addAllAllParts(Iterable<? extends RelatedCards> elements) {
    if (elements == null) return this;
    if (this.allParts == null) {
      this.allParts = new ArrayList<RelatedCards>();
    }
    for (RelatedCards e : elements) {
      addAllParts(e);
    }
    return this;
  }

  /**
   * Adds one element to {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces} list.
   * @param element The cardFaces element
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addCardFaces(CardFace element) {
    if (this.cardFaces == null) {
      this.cardFaces = new ArrayList<CardFace>();
    }
    Objects.requireNonNull(element, "cardFaces element");
    this.cardFaces.add(element);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces} list.
   * @param elements An array of cardFaces elements
   * @return {@code this} for use in a chained invocation
   */
  public final ModifiableCard addCardFaces(CardFace... elements) {
    for (CardFace e : elements) {
      addCardFaces(e);
    }
    return this;
  }

  /**
   * Sets or replaces all elements for {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces} list.
   * @param elements An iterable of cardFaces elements, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setCardFaces(@Nullable Iterable<? extends CardFace> elements) {
    if (elements == null) {
      this.cardFaces = null;
      return this;
    }
    if (this.cardFaces == null) {
      this.cardFaces = new ArrayList<CardFace>();
    } else {
      this.cardFaces.clear();
    }
    addAllCardFaces(elements);
    return this;
  }

  /**
   * Adds elements to {@link com.alver.fatefall.api.base.Card#getCardFaces() cardFaces} list.
   * @param elements An iterable of cardFaces elements
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard addAllCardFaces(Iterable<? extends CardFace> elements) {
    if (elements == null) return this;
    if (this.cardFaces == null) {
      this.cardFaces = new ArrayList<CardFace>();
    }
    for (CardFace e : elements) {
      addCardFaces(e);
    }
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getLegalities() legalities} attribute.
   * @param legalities The value for legalities, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setLegalities(@Nullable Legality legalities) {
    this.legalities = legalities;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getReserved() reserved} attribute.
   * @param reserved The value for reserved, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setReserved(@Nullable Boolean reserved) {
    this.reserved = reserved;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getEdhrecRank() edhrecRank} attribute.
   * @param edhrecRank The value for edhrecRank, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setEdhrecRank(@Nullable Integer edhrecRank) {
    this.edhrecRank = edhrecRank;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getSetCode() setCode} attribute.
   * @param setCode The value for setCode, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setSetCode(@Nullable String setCode) {
    this.setCode = setCode;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getSetName() setName} attribute.
   * @param setName The value for setName, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setSetName(@Nullable String setName) {
    this.setName = setName;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getCollectorNumber() collectorNumber} attribute.
   * @param collectorNumber The value for collectorNumber, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setCollectorNumber(@Nullable String collectorNumber) {
    this.collectorNumber = collectorNumber;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getSetSearchUri() setSearchUri} attribute.
   * @param setSearchUri The value for setSearchUri, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setSetSearchUri(@Nullable String setSearchUri) {
    this.setSearchUri = setSearchUri;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getScryfallSetUri() scryfallSetUri} attribute.
   * @param scryfallSetUri The value for scryfallSetUri, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setScryfallSetUri(@Nullable String scryfallSetUri) {
    this.scryfallSetUri = scryfallSetUri;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getImageUris() imageUris} attribute.
   * @param imageUris The value for imageUris, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setImageUris(@Nullable ImageUri imageUris) {
    this.imageUris = imageUris;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getHighresImage() highresImage} attribute.
   * @param highresImage The value for highresImage, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setHighresImage(@Nullable Boolean highresImage) {
    this.highresImage = highresImage;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getReprint() reprint} attribute.
   * @param reprint The value for reprint, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setReprint(@Nullable Boolean reprint) {
    this.reprint = reprint;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getDigital() digital} attribute.
   * @param digital The value for digital, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setDigital(@Nullable Boolean digital) {
    this.digital = digital;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getRarity() rarity} attribute.
   * @param rarity The value for rarity, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setRarity(@Nullable Rarity rarity) {
    this.rarity = rarity;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getFlavorText() flavorText} attribute.
   * @param flavorText The value for flavorText, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setFlavorText(@Nullable String flavorText) {
    this.flavorText = flavorText;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getArtist() artist} attribute.
   * @param artist The value for artist, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setArtist(@Nullable String artist) {
    this.artist = artist;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getIllustrationId() illustrationId} attribute.
   * @param illustrationId The value for illustrationId, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setIllustrationId(@Nullable UUID illustrationId) {
    this.illustrationId = illustrationId;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getFrame() frame} attribute.
   * @param frame The value for frame, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setFrame(@Nullable String frame) {
    this.frame = frame;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getFullArt() fullArt} attribute.
   * @param fullArt The value for fullArt, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setFullArt(@Nullable Boolean fullArt) {
    this.fullArt = fullArt;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getWatermark() watermark} attribute.
   * @param watermark The value for watermark, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setWatermark(@Nullable String watermark) {
    this.watermark = watermark;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getBorderColor() borderColor} attribute.
   * @param borderColor The value for borderColor, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setBorderColor(@Nullable BorderColors borderColor) {
    this.borderColor = borderColor;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getStorySpotlightNumber() storySpotlightNumber} attribute.
   * @param storySpotlightNumber The value for storySpotlightNumber, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setStorySpotlightNumber(@Nullable Integer storySpotlightNumber) {
    this.storySpotlightNumber = storySpotlightNumber;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getStorySpotlightUri() storySpotlightUri} attribute.
   * @param storySpotlightUri The value for storySpotlightUri, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setStorySpotlightUri(@Nullable String storySpotlightUri) {
    this.storySpotlightUri = storySpotlightUri;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getTimeshifted() timeshifted} attribute.
   * @param timeshifted The value for timeshifted, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setTimeshifted(@Nullable Boolean timeshifted) {
    this.timeshifted = timeshifted;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getColorshifted() colorshifted} attribute.
   * @param colorshifted The value for colorshifted, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setColorshifted(@Nullable Boolean colorshifted) {
    this.colorshifted = colorshifted;
    return this;
  }

  /**
   * Assigns a value to the {@link com.alver.fatefall.api.base.Card#getFutureshifted() futureshifted} attribute.
   * @param futureshifted The value for futureshifted, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setFutureshifted(@Nullable Boolean futureshifted) {
    this.futureshifted = futureshifted;
    return this;
  }

  /**
   * Put one entry to the {@link com.alver.fatefall.api.base.Card#getPurchaseUris() purchaseUris} map.
   * @param key The key in purchaseUris map
   * @param value The associated value in the purchaseUris map
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard putPurchaseUris(String key, String value) {
    if (this.purchaseUris == null) {
      this.purchaseUris = new LinkedHashMap<String, String>();
    }
    this.purchaseUris.put(
        Objects.requireNonNull(key, "purchaseUris key"),
        Objects.requireNonNull(value, "purchaseUris value"));
    return this;
  }

  /**
   * Sets or replaces all mappings from the specified map as entries for the {@link com.alver.fatefall.api.base.Card#getPurchaseUris() purchaseUris} map.
   * Nulls are not permitted as keys or values.
   * @param entries The entries that will be added to the purchaseUris map, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setPurchaseUris(@Nullable Map<String, ? extends String> entries) {
    if (entries == null) {
      this.purchaseUris = null;
      return this;
    }
    if (this.purchaseUris == null) {
      this.purchaseUris = new LinkedHashMap<String, String>();
    } else {
      this.purchaseUris.clear();
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
   * Put all mappings from the specified map as entries to the {@link com.alver.fatefall.api.base.Card#getPurchaseUris() purchaseUris} map.
   * Nulls are not permitted as keys or values.
   * @param entries to be added to purchaseUris map
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard putAllPurchaseUris(Map<String, ? extends String> entries) {
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
   * @param key The key in relatedUris map
   * @param value The associated value in the relatedUris map
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard putRelatedUris(String key, String value) {
    if (this.relatedUris == null) {
      this.relatedUris = new LinkedHashMap<String, String>();
    }
    this.relatedUris.put(
        Objects.requireNonNull(key, "relatedUris key"),
        Objects.requireNonNull(value, "relatedUris value"));
    return this;
  }

  /**
   * Sets or replaces all mappings from the specified map as entries for the {@link com.alver.fatefall.api.base.Card#getRelatedUris() relatedUris} map.
   * Nulls are not permitted as keys or values.
   * @param entries The entries that will be added to the relatedUris map, can be {@code null}
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard setRelatedUris(@Nullable Map<String, ? extends String> entries) {
    if (entries == null) {
      this.relatedUris = null;
      return this;
    }
    if (this.relatedUris == null) {
      this.relatedUris = new LinkedHashMap<String, String>();
    } else {
      this.relatedUris.clear();
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
   * Put all mappings from the specified map as entries to the {@link com.alver.fatefall.api.base.Card#getRelatedUris() relatedUris} map.
   * Nulls are not permitted as keys or values.
   * @param entries to be added to relatedUris map
   * @return {@code this} for use in a chained invocation
   */
  public ModifiableCard putAllRelatedUris(Map<String, ? extends String> entries) {
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
   * Returns {@code true} if all required attributes are set, indicating that the object is initialized.
   * @return {@code true} if set
   */
  public final boolean isInitialized() {
    return true;
  }

  /**
   * Converts to {@link ImmutableCard ImmutableCard}.
   * @return An immutable instance of Card
   */
  public final ImmutableCard toImmutable() {
    return ImmutableCard.copyOf(this);
  }

  /**
   * This instance is equal to all instances of {@code ModifiableCard} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    if (!(another instanceof ModifiableCard)) return false;
    ModifiableCard other = (ModifiableCard) another;
    return equalTo(other);
  }

  private boolean equalTo(ModifiableCard another) {
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
   * Generates a string representation of this {@code Card}.
   * If uninitialized, some attribute values may appear as question marks.
   * @return A string representation
   */
  @Override
  public String toString() {
    return "ModifiableCard{"
        + "id=" + getId()
        + ", name=" + getName()
        + ", oracleId=" + getOracleId()
        + ", multiverseIds=" + getMultiverseIds()
        + ", mtgoId=" + getMtgoId()
        + ", arenaId=" + getArenaId()
        + ", mtgoFoilId=" + getMtgoFoilId()
        + ", uri=" + getUri()
        + ", scryfallUri=" + getScryfallUri()
        + ", printsSearchUri=" + getPrintsSearchUri()
        + ", rulingsUri=" + getRulingsUri()
        + ", layout=" + getLayout()
        + ", cmc=" + getCmc()
        + ", typeLine=" + getTypeLine()
        + ", oracleText=" + getOracleText()
        + ", manaCost=" + getManaCost()
        + ", power=" + getPower()
        + ", toughness=" + getToughness()
        + ", loyalty=" + getLoyalty()
        + ", lifeModifier=" + getLifeModifier()
        + ", handModifier=" + getHandModifier()
        + ", colors=" + getColors()
        + ", colorIndicator=" + getColorIndicator()
        + ", colorIdentity=" + getColorIdentity()
        + ", allParts=" + getAllParts()
        + ", cardFaces=" + getCardFaces()
        + ", legalities=" + getLegalities()
        + ", reserved=" + getReserved()
        + ", edhrecRank=" + getEdhrecRank()
        + ", setCode=" + getSetCode()
        + ", setName=" + getSetName()
        + ", collectorNumber=" + getCollectorNumber()
        + ", setSearchUri=" + getSetSearchUri()
        + ", scryfallSetUri=" + getScryfallSetUri()
        + ", imageUris=" + getImageUris()
        + ", highresImage=" + getHighresImage()
        + ", reprint=" + getReprint()
        + ", digital=" + getDigital()
        + ", rarity=" + getRarity()
        + ", flavorText=" + getFlavorText()
        + ", artist=" + getArtist()
        + ", illustrationId=" + getIllustrationId()
        + ", frame=" + getFrame()
        + ", fullArt=" + getFullArt()
        + ", watermark=" + getWatermark()
        + ", borderColor=" + getBorderColor()
        + ", storySpotlightNumber=" + getStorySpotlightNumber()
        + ", storySpotlightUri=" + getStorySpotlightUri()
        + ", timeshifted=" + getTimeshifted()
        + ", colorshifted=" + getColorshifted()
        + ", futureshifted=" + getFutureshifted()
        + ", purchaseUris=" + getPurchaseUris()
        + ", relatedUris=" + getRelatedUris()
        + "}";
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
