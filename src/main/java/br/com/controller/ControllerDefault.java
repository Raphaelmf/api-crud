package br.com.controller;

import br.com.core.BotMessage;
import br.com.core.Message;
import br.com.router.Router.router;
import static spark.Spark.*;
import java.util.UUID;
import com.google.inject.Inject;

public class ControllerDefault implements Controller {

	private final BotMessage botMessage;
	private final Message msg;

	@Inject
	ControllerDefault(BotMessage botMessage, Message msg) {
		this.botMessage = botMessage;
		this.msg = msg;
	}

	@Override
	public String controller() {

		path("/api/v1", () -> {

			get(router.BOTS, (req, res) -> {
				UUID uuid = UUID.fromString(req.queryParams("uuid"));
				return botMessage.getBot(uuid);
			});
			post(router.BOTS, (req, res) -> {
				String body = req.body();
				return botMessage.addBot(body);
			});
			delete(router.BOTS, (req, res) -> {
				UUID uuid = UUID.fromString(req.queryParams("uuid"));
				return botMessage.deteleBot(uuid);
			});

			get(router.MESSAGES_ID, (req, res) -> {
				UUID id = UUID.fromString(req.params("id"));
				return msg.getMessageById(id);
			});

			get(router.MESSAGES, (req, res) -> {
				UUID conversationId = UUID.fromString(req.queryParams("conversationId"));
				System.out.println(conversationId);
				return msg.getMessageByConversationId(conversationId);
			});
			
			post(router.MESSAGES, (req, res) -> {
				String body = req.body();
				return msg.addMessages(body);
			});


		});

		notFound((req, res) -> {
			res.type("application/json");
			return "{\"message\":\"Custom 404\"}";
		});

		internalServerError((req, res) -> {
			res.type("application/json");
			return "{\"message\":\"Custom 500 handling\"}";
		});

		after((req, res) -> {
			res.type("application/json");
		});

		return null;
	}

}
