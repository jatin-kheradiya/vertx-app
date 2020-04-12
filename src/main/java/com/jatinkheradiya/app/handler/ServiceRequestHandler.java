package com.jatinkheradiya.app.handler;

import java.util.List;

import org.apache.http.HttpStatus;

import com.jatinkheradiya.app.entities.ServiceRequest;
import com.jatinkheradiya.app.processor.ServiceRequestProcessor;
import com.jatinkheradiya.app.utils.JsonUtil;

import io.vertx.ext.web.RoutingContext;

public class ServiceRequestHandler {

  private ServiceRequestProcessor serviceRequestProcessor = new ServiceRequestProcessor();

  public void getServiceRequests(RoutingContext routingContext) {

//    String userId = routingContext.request().getParam("userid");
//    String response = "";
    try {
      String payload = routingContext.getBodyAsString();
      Long vehicleId = Long.parseLong(routingContext.request().getParam("vehicleId"));
//      List<ServiceRequest> serviceRequests =
//          JsonUtil.createObjectFromString(payload, ServiceRequest.class);
      List<ServiceRequest> serviceRequests = serviceRequestProcessor.getServiceRequest(vehicleId);
      ResponseHandler.sendSuccessResponse(routingContext, serviceRequests, HttpStatus.SC_OK);
    } catch (Exception e) {
      e.printStackTrace();
      ResponseHandler.sendErrorResponse(routingContext, e);
    }
//    routingContext.response().putHeader("Context-Type", "application/json").setStatusCode(200)
//        .end(response);
  }

  //  public void getHomePage(RoutingContext routingContext) {
  //    routingContext.response().setStatusCode(200).end("Hello Home Page");
  //  }

  public void storeServiceRequest(RoutingContext routingContext) {
    try {
      String payload = routingContext.getBodyAsString();
      Long vehicleId = Long.parseLong(routingContext.request().getParam("vehicleId"));
      ServiceRequest serviceRequest =
          JsonUtil.createObjectFromString(payload, ServiceRequest.class);
      serviceRequestProcessor.storeServiceRequest(serviceRequest, vehicleId);
      ResponseHandler.sendSuccessResponse(routingContext, serviceRequest, HttpStatus.SC_CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      ResponseHandler.sendErrorResponse(routingContext, e);
    }
  }
}