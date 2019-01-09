package br.com.module;

import com.google.inject.AbstractModule;

import br.com.controller.Controller;
import br.com.controller.ControllerDefault;
import br.com.core.BotMessage;
import br.com.core.BotMessageDefault;
import br.com.core.MessageDefault;
import br.com.infra.ChatRepository;
import br.com.infra.ChatRepositoryDefault;
import br.com.infra.DatabaseConfigProvider;
import br.com.infra.DatabaseConfigProviderDefault;
import br.com.core.Message;;

public class BotsModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(BotMessage.class).to(BotMessageDefault.class);
		bind(Controller.class).to(ControllerDefault.class);
		bind(DatabaseConfigProvider.class).to(DatabaseConfigProviderDefault.class);
		bind(ChatRepository.class).to(ChatRepositoryDefault.class);
		bind(DatabaseConfigProvider.class).to(DatabaseConfigProviderDefault.class);
		bind(Message.class).to(MessageDefault.class);

	}

}
