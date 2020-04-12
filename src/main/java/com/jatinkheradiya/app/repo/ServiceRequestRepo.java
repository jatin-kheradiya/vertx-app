package com.jatinkheradiya.app.repo;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.ServiceRequest;
import com.jatinkheradiya.app.exceptions.VertxAppException;

public interface ServiceRequestRepo {

  JSONArray getServiceRequests(final Long vehicleId) throws VertxAppException;

  JSONObject addServiceRequest(ServiceRequest serviceRequest) throws VertxAppException;

  JSONArray getServiceRequestsWithFilters(final String userId, Map<String, String> filters)
      throws VertxAppException;

  JSONObject storeServiceRequest(ServiceRequest serviceRequest, long vehicleId)
      throws VertxAppException;
}
