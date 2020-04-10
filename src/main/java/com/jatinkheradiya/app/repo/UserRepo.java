package com.jatinkheradiya.app.repo;

import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.User;
import com.jatinkheradiya.app.exceptions.VertxAppException;

public interface UserRepo {

  JSONObject addUser(User user) throws VertxAppException;

  JSONObject getUser(final long userId) throws VertxAppException;
}
