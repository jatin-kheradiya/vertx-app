package com.jatinkheradiya.app.handler;

import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.ServiceRequest;
import com.jatinkheradiya.app.processor.ServiceRequestProcessor;
import com.jatinkheradiya.app.utils.Constants;
import com.jatinkheradiya.app.utils.JsonUtil;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.ext.web.RoutingContext;

public class ServiceRequestHandler {

  private ServiceRequestProcessor serviceRequestProcessor = new ServiceRequestProcessor();

  public void getServiceRequests(RoutingContext routingContext) {


    String userId = routingContext.request().getParam("userid");

    String response = "";

    try {
      response = serviceRequestProcessor.getResponse(userId);
    } catch (Exception e) {
      e.printStackTrace();
    }

    routingContext.response().putHeader("Context-Type", "application/json").setStatusCode(200)
        .end(response);
  }

  public void getHomePage(RoutingContext routingContext) {

    routingContext.response().setStatusCode(200).end("Hello Home Page");
  }

  public void storeServiceRequest(RoutingContext routingContext) {
    try {
      String payload = routingContext.getBodyAsString();
      ServiceRequest serviceRequest = new ServiceRequest();
      serviceRequest.setVehicleId("test-vehicle-1");
      serviceRequest.setStatus("test-status");
//      ServiceRequest serviceRequest2 =
//          JsonUtil.createObjectFromString(payload, ServiceRequest.class);

			String jsonObject = JsonUtil.createJsonFromObject(serviceRequest);
			ServiceRequest serviceRequest2 =
					JsonUtil.createObjectFromString(payload, ServiceRequest.class);
      serviceRequestProcessor.storeServiceRequest(serviceRequest);

      //			routingContext.response().end(serviceRequest.getStatus());
      routingContext.response()
          .putHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON_CHARSET_UTF_8)
          .setStatusCode(HttpResponseStatus.OK.code()).end(serviceRequest.getStatus().toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}