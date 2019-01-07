package br.com.service;

import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.controller.Controller;
import br.com.controller.ControllerDefault;
import br.com.infra.DatabaseConfigProvider;
import br.com.infra.DatabaseConfigProviderDefault;
import br.com.module.BotsModule;
import spark.Spark;

public class Application {
	
	public static void start() {
		Spark.port(3000);
		Injector injector = Guice.createInjector(new BotsModule());
		Controller c = injector.getInstance(ControllerDefault.class);
		c.controller();
		DatabaseConfigProvider db = injector.getInstance(DatabaseConfigProviderDefault.class);
		db.getSession();
		System.out.println("Chat bots is Online");
	}
	
	public static void main(String[]args) {

		Application.start();
		
	}
}
