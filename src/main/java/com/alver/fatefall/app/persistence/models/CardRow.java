package com.alver.fatefall.app.persistence.models;

import com.alver.fatefall.api.models.Card;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "card")
public class CardRow extends AbstractRow<Card> {

}
