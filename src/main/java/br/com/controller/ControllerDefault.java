package br.com.controller;

import br.com.core.BotMessage;
import br.com.model.MyMessage;
import br.com.router.Router.router;
import static spark.Spark.*;
import java.util.UUID;

import javax.xml.ws.Response;

import org.eclipse.jetty.client.api.Request;

import com.google.gson.Gson;
import com.google.inject.Inject;

public class ControllerDefault implements Controller {

	private final BotMessage message;

	@Inject
	ControllerDefault(BotMessage message) {
		this.message = message;
	}

	@Override
	public String controller() {

		path("/api/v1", () -> {
			// http://localhost:3000/api/v1/bots/?uuid=3b1cd2a1-f6f9-4561-8669-d293af43fc43
			get(router.BOTS, (req, res) -> {
				UUID uuid = UUID.fromString(req.queryParams("uuid"));
				return message.getBot(uuid);
			});
			post(router.BOTS, (req, res) -> "Hello World");
			put(router.BOTS, (req, res) -> "Hello World");
			delete(router.BOTS, (req, res) -> "Hello World");
			get(router.HELLO, (request, response) -> {
				message.getBot(UUID.randomUUID());
				return new Gson().toJson(new MyMessage("{\"teste\":\"rapha\"}"));
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
