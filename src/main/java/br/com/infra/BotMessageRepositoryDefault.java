package br.com.infra;

import java.util.UUID;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.ResultSet;
import com.google.inject.Inject;

public class BotMessageRepositoryDefault implements BotMessageRepository {

	private final DatabaseConfigProvider db;

	@Inject
	BotMessageRepositoryDefault(DatabaseConfigProvider db) {
		this.db = db;
	}

	@Override
	public String getBotById(UUID uuid) {
		String json = null;
		Statement query = QueryBuilder.select().json().all().from("chat", "bots").where(QueryBuilder.eq("id", uuid));
		json = execCommand(query);
		return json;
	}

	public String execCommand(Statement stm) {
		Session session = db.getSession();
		StringBuilder jsonBuilder = new StringBuilder();
		ResultSet r = session.execute(stm);
		boolean isFirst = true;
		while (!r.isExhausted()) {
			if (!isFirst) {
				jsonBuilder.append(",");
			} else {
				isFirst = false;
			}
			jsonBuilder.append(r.one().getString(0));
		}
		String json = jsonBuilder.toString();
		return json;
	}

}
