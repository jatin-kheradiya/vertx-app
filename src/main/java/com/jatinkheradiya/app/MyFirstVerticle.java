package com.jatinkheradiya.app;

import com.jatinkheradiya.app.handler.BasicHandler;
import com.jatinkheradiya.app.handler.CommonHandler;
import com.jatinkheradiya.app.handler.ServiceRequestHandler;
import com.jatinkheradiya.app.handler.UserHandler;
import com.jatinkheradiya.app.handler.VehicleHandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;


public class MyFirstVerticle extends AbstractVerticle {

  BasicHandler basicHandler = new BasicHandler();

  ServiceRequestHandler serviceRequestHandler = new ServiceRequestHandler();

  CommonHandler commonHandler = new CommonHandler();

  VehicleHandler vehicleHandler = new VehicleHandler();

  UserHandler userHandler = new UserHandler();

  @Override
  public void start(Future<Void> fut) {

    Router router = Router.router(vertx);

    router.route().order(-2).handler(BodyHandler.create());
    router.options("/*").order(-1).handler(this.basicHandler::optionsHandle);

    router.get("/car-manufacturers").handler(commonHandler::getCarManufacturers);
    router.get("/car-models").handler(commonHandler::getCarModels);

    //		router.get("/").handler(basicHandler::getHomePage);
    router.get("/service-requests/:userid").handler(serviceRequestHandler::getServiceRequests);
    router.post("/service-requests").consumes("application/json").handler(serviceRequestHandler::storeServiceRequest);

    // vehicle routes
    router.post("/vehicles").handler(vehicleHandler::addVehicle);
    router.get("/vehicles/:vehicleId").handler(vehicleHandler::getVehicleById);
    router.get("/vehicles/user/:userId").handler(vehicleHandler::getVehicleByUserId);

    // user routes
    router.post("/users").handler(userHandler::addUser);
    router.get("/users/:userId").handler(userHandler::getUserById);

    //    router.route().last().handler(this.basicHandler::defaultHandle);

    vertx
        // this createHttpServer creates an HTTP server
        .createHttpServer()
        // any request first comes to the requestHandler
        .requestHandler(router::accept).listen(8089, "0.0.0.0", result -> {
      if (result.succeeded()) {
        fut.complete();
      } else {
        fut.fail(result.cause());
      }
    });
  }

}