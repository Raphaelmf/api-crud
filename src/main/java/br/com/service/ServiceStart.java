package br.com.service;

import spark.Spark;

public class ServiceStart {
	
	public static  void start() {
		Spark.port(3000);
		System.out.println("Serviços está no ar!");
	}
	
	public static void main(String[]args) {
		
		ServiceStart.start();
		
	}
}
