package com.jatinkheradiya.app.handler;

import java.util.UUID;

import com.jatinkheradiya.app.entities.ServiceRequest;
import com.jatinkheradiya.app.entities.Vehicle;
import com.jatinkheradiya.app.enums.FuelType;
import com.jatinkheradiya.app.processor.ServiceRequestProcessor;
import com.jatinkheradiya.app.processor.VehicleProcessor;
import com.jatinkheradiya.app.utils.Constants;
import com.jatinkheradiya.app.utils.JsonUtil;

import io.netty.handler.codec.http.HttpResponseStatus;
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
//      ServiceRequest serviceRequest = new ServiceRequest();
//      serviceRequest.setVehicleId("test-vehicle-1");
//      serviceRequest.setStatus("test-status");
//      ServiceRequest serviceRequest2 =
//          JsonUtil.createObjectFromString(payload, ServiceRequest.class);

//			String jsonObject = JsonUtil.createJsonFromObject(serviceRequest);
//			ServiceRequest serviceRequest2 =
//					JsonUtil.createObjectFromString(payload, ServiceRequest.class);
      Vehicle vehicle = new Vehicle();
//      vehicle.setId(1);
      vehicle.setColor("Blue");
      vehicle.setMake("Maruti");
      vehicle.setModel("Swift Dzire");
      vehicle.setYear("2000");
      vehicle.setType("car");
      vehicle.setRegistrationNumber("KA01HA0001");
      vehicle.setFuelType(FuelType.PETROL.toString());
      vehicleProcessor.addVehicle(vehicle);

      //			routingContext.response().end(serviceRequest.getStatus());
      routingContext.response()
          .putHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON_CHARSET_UTF_8)
          .setStatusCode(HttpResponseStatus.OK.code()).end(vehicle.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}