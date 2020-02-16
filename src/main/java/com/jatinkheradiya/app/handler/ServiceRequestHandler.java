package com.jatinkheradiya.app.handler;

import com.jatinkheradiya.app.processor.ServiceRequestProcessor;

import io.vertx.ext.web.RoutingContext;

public class ServiceRequestHandler {

	public void getServiceRequests(RoutingContext routingContext) {
		
		String userId = routingContext.request().getParam("userid");

		ServiceRequestProcessor serviceRequestProcessor = new ServiceRequestProcessor();
		String response = "";
		
		try {
			response = serviceRequestProcessor.getResponse(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		routingContext.response()
			.putHeader("Context-Type", "application/json")
			.setStatusCode(200)
			.end(response);
	}

	public void getHomePage(RoutingContext routingContext) {

		routingContext
			.response()
			.setStatusCode(200)
			.end("Hello Home Page");
	}
}