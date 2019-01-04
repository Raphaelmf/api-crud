package br.com.module;

import com.google.inject.AbstractModule;

import br.com.controller.Controller;
import br.com.controller.ControllerDefault;
import br.com.core.BotMessage;
import br.com.core.BotMessageDefault;
import br.com.infra.BotMessageRepository;
import br.com.infra.BotMessageRepositoryDefault;
import br.com.infra.DatabaseConfigProvider;
import br.com.infra.DatabaseConfigProviderDefault;;

public class BotsModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(BotMessage.class).to(BotMessageDefault.class);
		bind(Controller.class).to(ControllerDefault.class);
		bind(DatabaseConfigProvider.class).to(DatabaseConfigProviderDefault.class);
		bind(BotMessageRepository.class).to(BotMessageRepositoryDefault.class);
		bind(DatabaseConfigProvider.class).to(DatabaseConfigProviderDefault.class);

	}

}
