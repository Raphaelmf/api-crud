package br.com.infra;

import java.util.UUID;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.ResultSet;
import com.google.inject.Inject;

public class BotMessageRepositoryDefault implements BotMessageRepository{
	
	private final DatabaseConfigProvider db;
	
	@Inject
	BotMessageRepositoryDefault(DatabaseConfigProvider db) {
		this.db = db;
	}

	@Override
	public String getBotById(UUID uuid) {
		// TODO Auto-generated method stub
		System.out.println("chegou no repository");
		Statement query = QueryBuilder.select().all().from("chat", "bots");
		System.out.println(query);
		//Session session;
		Session session = db.getSession();
		System.out.println("passou da sessao");
		ResultSet r =  session.execute(query);
		System.out.println(r);
		return null;
	}

}
