package com.jatinkheradiya.app.handler;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.Vehicle;
import com.jatinkheradiya.app.processor.VehicleProcessor;
import com.jatinkheradiya.app.utils.JsonUtil;

import io.vertx.ext.web.RoutingContext;

public class VehicleHandler {

  private VehicleProcessor vehicleProcessor = new VehicleProcessor();

  public void getVehicles(RoutingContext routingContext) {


    String userId = routingContext.request().getParam("userid");

    String response = "";

    try {
      response = vehicleProcessor.getResponse(userId);
    } catch (Exception e) {
      e.printStackTrace();
    }

    routingContext.response().putHeader("Context-Type", "application/json").setStatusCode(200)
        .end(response);
  }

  public void addVehicle(RoutingContext routingContext) {
    try {
      String payload = routingContext.getBodyAsString();
      Vehicle vehicle = JsonUtil.createObjectFromString(payload, Vehicle.class);
      vehicleProcessor.addVehicle(vehicle);

      //			routingContext.response().end(serviceRequest.getStatus());
      //      routingContext.response()
      //          .putHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON_CHARSET_UTF_8)
      //          .setStatusCode(HttpResponseStatus.OK.code()).end(JsonUtil.createJsonFromObject(vehicle));
      ResponseHandler.sendSuccessResponse(routingContext, vehicle, HttpStatus.SC_CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      ResponseHandler.sendErrorResponse(routingContext, e);
    }
  }

  public void getVehicleById(RoutingContext routingContext) {
    try {
      String vehicleId = routingContext.request().getParam("vehicleId");
      JSONObject vehicle = vehicleProcessor.getVehicleById(vehicleId);

      //      routingContext.response()
      //          .putHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON_CHARSET_UTF_8)
      //          .setStatusCode(HttpResponseStatus.OK.code()).end(JsonUtil.createJsonFromObject(vehicle));
      ResponseHandler.sendSuccessResponse(routingContext, vehicle, HttpStatus.SC_OK);

    } catch (Exception e) {
      ResponseHandler.sendErrorResponse(routingContext, e);
    }
  }

  public void getVehicleByUserId(RoutingContext routingContext) {
    try {
      String vehicleId = routingContext.request().getParam("vehicleId");
      JSONObject vehicle = vehicleProcessor.getVehicleById(vehicleId);

//      routingContext.response()
//          .putHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON_CHARSET_UTF_8)
//          .setStatusCode(HttpResponseStatus.OK.code()).end(JsonUtil.createJsonFromObject(vehicle));
      ResponseHandler.sendSuccessResponse(routingContext, vehicle, HttpStatus.SC_OK);
    } catch (Exception e) {
      ResponseHandler.sendErrorResponse(routingContext, e);
    }
  }
}