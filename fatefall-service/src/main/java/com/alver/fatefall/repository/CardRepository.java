package com.alver.fatefall.repository;

import com.alver.fatefall.database.row.CardRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepository extends BaseRepository{

	private static final String SELECT_ALL_CARDS = """
			SELECT id, template_id, name FROM cards;
			""";
	private static final String SELECT_CARD_BY_ID = """
			SELECT id, template_id, name FROM cards WHERE id = ?;
			""";

	private static final ResultSetTransformer<CardRow> TRANSFORM = rs -> new CardRow(
			rs.getString("id"),
			rs.getString("template_id"),
			rs.getString("name"));

	@Autowired
	public CardRepository(DatabaseClient databaseClient) {
		super(databaseClient);
	}

	public List<CardRow> getAllCardRows() {
		return databaseClient.query(SELECT_ALL_CARDS, TRANSFORM);
	}

	public CardRow getCardRow(String id) {
		return exactlyOne(databaseClient.query(SELECT_CARD_BY_ID.replace("?", id), TRANSFORM));
	}

}
