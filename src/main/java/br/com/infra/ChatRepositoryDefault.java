package br.com.infra;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ServerSideTimestampGenerator;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import com.google.inject.Inject;

import br.com.model.BotModel;
import br.com.model.MessageModel;

public class ChatRepositoryDefault implements ChatRepository {

	private final DatabaseConfigProvider db;

	@Inject
	ChatRepositoryDefault(DatabaseConfigProvider db) {
		this.db = db;
	}

	@Override
	public String getBotById(UUID uuid) {
		String json = null;
		Statement query = QueryBuilder.select().json().all().from("chat", "bots").where(QueryBuilder.eq("id", uuid));
		json = execCommand(query);
		return json;
	}

	@Override
	public String addBot(BotModel bot) {
		String json = null;
		Statement query = QueryBuilder.insertInto("chat", "bots").value("id", bot.getId()).value("name", bot.getName());
		json = execCommand(query);
		return json;
	}

	@Override
	public String deleteBot(UUID id) {
		String json = null;
		Statement query = QueryBuilder.delete().from("chat", "bots").where(eq("id", id));
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
		return json= "{\"data\":["+json+"]}";
	}

	@Override
	public String getMessageByConversationId(UUID conversationId) {
		System.out.println("teste 2  " + conversationId);
		String json = null;
		Statement query = QueryBuilder.select().json().all().from("chat", "mensagens_por_idconversa")
				.where(QueryBuilder.eq("conversationid", conversationId));
		json = execCommand(query);
		return json;
	}

	@Override
	public String getMessageById(UUID id) {
		String json = null;
		Statement query = QueryBuilder.select().json().all().from("chat", "mensagens_por_id")
				.where(QueryBuilder.eq("id", id));
		json = execCommand(query);
		return json;
	}

	@Override
	public String addMessageById(MessageModel message) {
		String json = null;
		Statement query = QueryBuilder.insertInto("chat", "mensagens_por_id").value("id", message.getId())
				.value("textmsg", message.getTextMsg());
		System.out.println("addMessageById: " + query);
		System.out.println(query);
		json = execCommand(query);
		System.out.println(json);
		return json;
	}

	@Override
	public String addMessageByConversationId(MessageModel message) {
		String json = null;
		Date date = null;
		date = Date.from(Instant.now());

		Statement query = QueryBuilder.insertInto("chat", "mensagens_por_idconversa")
				.value("conversationId", message.getConversationId())
				.value("timestampmsg", date)
				.value("toid", message.getToId())
				.value("textmsg", message.getTextMsg());
		json = execCommand(query);
		return json;
	}

}
