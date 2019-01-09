package br.com.infra;

import java.util.UUID;

import br.com.model.BotModel;
import br.com.model.MessageModel;

public interface  ChatRepository {
	
	String getBotById(UUID uuid);

	String addBot(BotModel bot);

	String deleteBot(UUID bot);

	String getMessageByConversationId(UUID conversationId);

	String getMessageById(UUID id);

	String addMessageById(MessageModel message);

	String addMessageByConversationId(MessageModel message);


}
