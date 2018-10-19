package br.com.service;

import br.com.controller.Controller;
import br.com.controller.ControllerDefault;
import spark.Spark;

public class ServiceStart {
	
	public static void start() {
		Spark.port(3000);
		Controller c = new ControllerDefault();
		c.controller();
		System.out.println("Serviço está no ar!");
	}
	
	public static void main(String[]args) {
		
		ServiceStart.start();
		
	}
}
