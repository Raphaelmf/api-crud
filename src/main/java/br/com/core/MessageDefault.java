package br.com.core;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.inject.Inject;

import br.com.infra.ChatRepository;
import br.com.model.BotModel;
import br.com.model.MessageModel;
public class MessageDefault implements Message {
	
	private final ChatRepository repository;

	@Inject
	MessageDefault(ChatRepository repository) {
		this.repository = repository;
	}

	@Override
	public String getMessageByConversationId(UUID conversationId) {
		System.out.println("teste 1");
		return repository.getMessageByConversationId(conversationId);
	}

	@Override
	public String getMessageById(UUID id) {
		return repository.getMessageById(id);
	}
	
	@Override
	public String addMessages(String message) {
		Gson gson = new Gson();
		System.out.println("t1");
		MessageModel m = gson.fromJson(message, MessageModel.class);
		System.out.println(m);
		repository.addMessageByConversationId(m);
		repository.addMessageById(m);
		return m.toString();
		
	}

}
