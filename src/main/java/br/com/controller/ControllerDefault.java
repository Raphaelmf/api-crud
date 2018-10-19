package br.com.controller;

import br.com.router.Router;
import br.com.router.RouterDefault;
import static spark.Spark.*;

public class ControllerDefault implements Controller {

	@Override
	public String controller() {
		// TODO Auto-generated method stub

		Router r = new RouterDefault();
		
		
		get("/hello", (req, res) -> "Hello World");
		
		
		after((req, res) -> {
			res.type("application/json");
			});
		return null;
	}

}
