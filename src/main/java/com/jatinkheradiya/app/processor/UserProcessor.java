package com.jatinkheradiya.app.processor;

import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.User;
import com.jatinkheradiya.app.exceptions.VertxAppException;
import com.jatinkheradiya.app.repo.impl.UserRepoImpl;

public class UserProcessor {
	
	private UserRepoImpl userRepoImpl = new UserRepoImpl();
	
	public String getResponse(final String userId) throws VertxAppException {

		String responseStr = "Not found";
		JSONObject response = userRepoImpl.getUser(userId);
		if(response != null) {
			responseStr = response.toJSONString();
		}
		return responseStr;
	}

  public void addUser(User user) throws VertxAppException {
		userRepoImpl.addUser(user);
  }

  public JSONObject getUserById(String userId) throws VertxAppException {
		return userRepoImpl.getUser(userId);
	}
}
