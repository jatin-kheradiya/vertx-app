package com.jatinkheradiya.app.handler;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.User;
import com.jatinkheradiya.app.processor.UserProcessor;
import com.jatinkheradiya.app.utils.JsonUtil;

import io.vertx.ext.web.RoutingContext;

public class UserHandler {

  private UserProcessor userProcessor = new UserProcessor();

//  public void getUsers(RoutingContext routingContext) {
//
//    String userId = routingContext.request().getParam("userid");
//    String response = "";
//    try {
//      response = userProcessor.getResponse(userId);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    routingContext.response().putHeader("Context-Type", "application/json").setStatusCode(200)
//        .end(response);
//  }

  public void addUser(RoutingContext routingContext) {
    try {
      String payload = routingContext.getBodyAsString();
      User user = JsonUtil.createObjectFromString(payload, User.class);
      userProcessor.addUser(user);
      ResponseHandler.sendSuccessResponse(routingContext, user, HttpStatus.SC_CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      ResponseHandler.sendErrorResponse(routingContext, e);
    }
  }

  public void getUserById(RoutingContext routingContext) {
    try {
      long userId = Long.parseLong(routingContext.request().getParam("userId"));
      JSONObject user = userProcessor.getUserById(userId);
      ResponseHandler.sendSuccessResponse(routingContext, user, HttpStatus.SC_OK);

    } catch (Exception e) {
      ResponseHandler.sendErrorResponse(routingContext, e);
    }
  }

}