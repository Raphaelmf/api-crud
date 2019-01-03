package br.com.core;

import java.util.UUID;

import com.google.inject.Inject;

import br.com.infra.BotMessageRepository;

public class BotMessageDefault implements BotMessage {
	
	private final BotMessageRepository repository;

	@Inject
	BotMessageDefault(BotMessageRepository repository){
		this.repository = repository;	
	}
	@Override
	public UUID getBot(UUID uuid) {
		
		return uuid.randomUUID();
		// TODO Auto-generated method stub
		
	}

}
