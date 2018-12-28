package br.com.core;

import java.util.UUID;

public class BotMessageDefault implements BotMessage {

	@Override
	public UUID getBot(UUID uuid) {
		
		return uuid.randomUUID();
		// TODO Auto-generated method stub
		
	}

}
