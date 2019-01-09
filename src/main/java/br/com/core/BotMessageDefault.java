package br.com.core;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.inject.Inject;

import br.com.infra.ChatRepository;
import br.com.model.BotModel;

public class BotMessageDefault implements BotMessage {

	private final ChatRepository repository;

	@Inject
	BotMessageDefault(ChatRepository repository) {
		this.repository = repository;
	}

	@Override
	public String getBot(UUID uuid) {
		return repository.getBotById(uuid);

	}

	@Override
	public String addBot(String bot) {
		Gson gson = new Gson();
		BotModel b = gson.fromJson(bot, BotModel.class);

		return repository.addBot(b);

	}

	@Override
	public String deteleBot(UUID uuid) {
	    String json = null;
		json = repository.deleteBot(uuid);
		return json;

	}

}
