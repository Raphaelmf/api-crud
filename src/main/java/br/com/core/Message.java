package br.com.core;

import java.util.UUID;

public interface  Message {
	
	String getMessageByConversationId(UUID conversationId);
	String getMessageById(UUID id);
	String addMessages(String body);

}
