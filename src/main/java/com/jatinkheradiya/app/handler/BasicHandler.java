package com.jatinkheradiya.app.handler;

import org.json.simple.JSONObject;
import io.vertx.core.*;
import io.vertx.ext.web.RoutingContext;

public class BasicHandler {

	public void getPages(RoutingContext routingContext) {
		String pageId = routingContext.request().getParam("id");

		// String jsonResponse = "{\"pageName\": \"Jatin\"}";
		JSONObject json = new JSONObject();
		json.put("pageName", "Jatin page");
		json.put("page owner", "Ankita");

 
		routingContext.response()
			.putHeader("Context-Type", "application/json")
			.setStatusCode(200)
			.end(json.toString());
			// .end(Json.encodePrettily(json.toString()));

	}

	public void getHomePage(RoutingContext routingContext) {

		routingContext
			.response()
			.setStatusCode(200)
			.end("Hello Jatin Kheradiya");
	}

	public void optionsHandle(RoutingContext routingContext) {
		try {
			routingContext.response().putHeader("Access-Control-Allow-Origin", "*")
					.putHeader("Access-Control-Allow-Methods", "GET, POST, PUT , DELETE, OPTIONS")
					.putHeader("Access-Control-Allow-Headers", "Content-Type,cache-control, x-requested-with")
					.putHeader("Access-Control-Max-Age", "86400");
		} catch (final Exception e) {
			System.out.println("Exception while setting routing context option calls." + e);
		}
	}

	public void defaultHandle(RoutingContext routingContext) {
	}
}