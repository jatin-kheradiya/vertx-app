package com.jatinkheradiya.app.handler;

import com.jatinkheradiya.app.processor.GenericProcessor;

import io.vertx.ext.web.RoutingContext;

public class CommonHandler {

  private GenericProcessor genericProcessor = new GenericProcessor();

  public void getCarManufacturers(RoutingContext routingContext) {

    String response = "";
    try {
      response = genericProcessor.getCarManufacturers();
    } catch (Exception e) {
      e.printStackTrace();
    }
    routingContext.response().putHeader("Context-Type", "application/json").setStatusCode(200)
        .end(response);
  }

  public void getHomePage(RoutingContext routingContext) {

    routingContext.response().setStatusCode(200).end("Hello Home Page");
  }

  public void getCarModels(RoutingContext routingContext) {
    String response = "";
    try {
      response = genericProcessor.getCarModels();
    } catch (Exception e) {
      e.printStackTrace();
    }
    routingContext.response().putHeader("Context-Type", "application/json").setStatusCode(200)
        .end(response);
  }
}