package com.jatinkheradiya.app.repo;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.ServiceRequest;

public interface ServiceRequestRepo {

  public JSONArray getServiceRequests(final String userId);

  public JSONObject addServiceRequest(ServiceRequest serviceRequest);

  public JSONArray getServiceRequestsWithFilters(final String userId, Map<String, String> filters);
}
