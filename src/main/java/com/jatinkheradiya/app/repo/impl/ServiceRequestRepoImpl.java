package com.jatinkheradiya.app.repo.impl;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.db.DatabaseLayer;
import com.jatinkheradiya.app.entities.ServiceRequest;
import com.jatinkheradiya.app.repo.ServiceRequestRepo;

public class ServiceRequestRepoImpl implements ServiceRequestRepo {

  private DatabaseLayer databaseLayer = new DatabaseLayer();

  @Override
  public JSONArray getServiceRequests(String userId) {
    JSONArray serviceRequests = databaseLayer.getServiceRequests(userId);
    return serviceRequests;
  }

  @Override
  public JSONObject addServiceRequest(ServiceRequest serviceRequest) {
    return null;
  }

  @Override
  public JSONArray getServiceRequestsWithFilters(String userId, Map<String, String> filters) {
    return null;
  }
}
