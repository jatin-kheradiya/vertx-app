package com.jatinkheradiya.app;

import com.jatinkheradiya.app.handler.BasicHandler;
import com.jatinkheradiya.app.handler.ServiceRequestHandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;


public class MyFirstVerticle extends AbstractVerticle {

  BasicHandler basicHandler = new BasicHandler();

  ServiceRequestHandler serviceRequestHandler = new ServiceRequestHandler();

  @Override
  public void start(Future<Void> fut) {

    Router router = Router.router(vertx);

    //		router.get("/").handler(basicHandler::getHomePage);
    router.get("/service-requests/:userid").handler(serviceRequestHandler::getServiceRequests);

    vertx
        // this createHttpServer creates an HTTP server
        .createHttpServer()
        // any request first comes to the requestHandler
        .requestHandler(router::accept).listen(8089, result -> {
      if (result.succeeded()) {
        fut.complete();
      } else {
        fut.fail(result.cause());
      }
    });
  }

}