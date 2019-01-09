package br.com.core;

import java.util.UUID;

public interface BotMessage {

	Object getBot(UUID uuid);

	String addBot(String bot);

	String deteleBot(UUID uuid);
}
