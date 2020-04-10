package com.jatinkheradiya.app.repo.impl;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jatinkheradiya.app.db.DatabaseLayer;
import com.jatinkheradiya.app.entities.User;
import com.jatinkheradiya.app.exceptions.VertxAppException;
import com.jatinkheradiya.app.repo.UserRepo;
import com.jatinkheradiya.app.utils.JsonUtil;

public class UserRepoImpl implements UserRepo {

  Logger log = LoggerFactory.getLogger(UserRepoImpl.class);

  private DatabaseLayer databaseLayer = new DatabaseLayer();

  @Override
  public JSONObject addUser(User user) throws VertxAppException {
    JSONObject userJson;
    try {
      User userSaved = (User) databaseLayer.saveObject(user);
      String userJsonString = JsonUtil.createJsonFromObject(userSaved);
      userJson = JsonUtil.createObjectFromString(userJsonString, JSONObject.class);
    } catch (Exception e) {
      log.error("Error in adding user: {}", e.getMessage());
      throw new VertxAppException("Error in adding the user", e);
    }
    return userJson;
  }

  @Override
  public JSONObject getUser(long userId) throws VertxAppException {
    JSONObject userJson;
    try {
      User user = databaseLayer.getObjectById(User.class, userId);
      userJson =
          JsonUtil.createObjectFromString(JsonUtil.createJsonFromObject(user), JSONObject.class);
    } catch (Exception e) {
      log.error("Error in getting user for id: {}, {}", userId, e.getMessage());
      throw new VertxAppException("Error in getting the user for id: "+ userId, e);
    }
    return userJson;
  }
}
