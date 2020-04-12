package com.jatinkheradiya.app.repo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jatinkheradiya.app.db.DatabaseLayer;
import com.jatinkheradiya.app.entities.ServiceRequest;
import com.jatinkheradiya.app.exceptions.VertxAppException;
import com.jatinkheradiya.app.repo.ServiceRequestRepo;
import com.jatinkheradiya.app.utils.JsonUtil;

public class ServiceRequestRepoImpl implements ServiceRequestRepo {

  private Logger log = LoggerFactory.getLogger(ServiceRequest.class);

  private DatabaseLayer databaseLayer = new DatabaseLayer();

  @Override
  public JSONArray getServiceRequests(Long vehicleId) throws VertxAppException {
    try {
      //    JSONArray serviceRequests = databaseLayer.getServiceRequests(userId);
      Map<String, Object> andConditions = new HashMap<>();
      Map<String, Object> orConditions = new HashMap<>();
      andConditions.put("vehicleId", vehicleId);
      List<ServiceRequest> serviceRequests =
          databaseLayer.getObjectsByParam(andConditions, orConditions, ServiceRequest.class);

      String servicesStr = JsonUtil.createJsonFromObject(serviceRequests);
      JSONArray services = JsonUtil.createObjectFromString(servicesStr, JSONArray.class);
      return services;
    } catch (Exception e) {
      e.printStackTrace();
      throw new VertxAppException(
          "Error in getting the service requests for vehicle id: " + vehicleId, e);
    }
  }

  @Override
  public JSONObject addServiceRequest(ServiceRequest serviceRequest) {
    return null;
  }

  @Override
  public JSONArray getServiceRequestsWithFilters(String userId, Map<String, String> filters) {
    return null;
  }

  @Override
  public JSONObject storeServiceRequest(ServiceRequest serviceRequest, long vehicleId)
      throws VertxAppException {
    JSONObject serviceRequestJson;
    try {
      // Tie the user to the vehicle
      //      VehicleRepoImpl userRepo = new VehicleRepoImpl();
      //      JSONObject vehicleJson = userRepo.getVehicle(vehicleId);
      //      Vehicle vehicle = JsonUtil.createObjectFromString(vehicleJson.toJSONString(), Vehicle.class);
      serviceRequest.setVehicleId(vehicleId);
      //      serviceRequest.setStartTime(ZonedDateTime.now());
      ServiceRequest serviceRequest1 = (ServiceRequest) databaseLayer.saveObject(serviceRequest);

      //      // Tie the vehicle to the user
      //      Vehicle vehicle1 = new Vehicle();
      //      List<Long> serviceRequestList = new ArrayList<>();
      //      serviceRequestList.add(serviceRequest1.getId());
      //      vehicle1.setServiceRequestList(serviceRequestList);

      String serviceRequestJsonString = JsonUtil.createJsonFromObject(serviceRequest1);
      serviceRequestJson =
          JsonUtil.createObjectFromString(serviceRequestJsonString, JSONObject.class);
    } catch (Exception e) {
      log.error("Error in adding service request: {}", e.getMessage());
      throw new VertxAppException("Error in adding the service request", e);
    }
    return serviceRequestJson;
  }
}
