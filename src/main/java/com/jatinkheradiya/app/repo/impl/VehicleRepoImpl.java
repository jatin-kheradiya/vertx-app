package com.jatinkheradiya.app.repo.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jatinkheradiya.app.db.DatabaseLayer;
import com.jatinkheradiya.app.entities.Vehicle;
import com.jatinkheradiya.app.exceptions.VertxAppException;
import com.jatinkheradiya.app.repo.VehicleRepo;
import com.jatinkheradiya.app.utils.JsonUtil;

public class VehicleRepoImpl implements VehicleRepo {

  private Logger log = LoggerFactory.getLogger(VehicleRepoImpl.class);

  private DatabaseLayer databaseLayer = new DatabaseLayer();

  @Override
  public JSONArray getVehiclesbyUserId(long userId) throws VertxAppException {
    JSONArray vehicles = databaseLayer.getServiceRequests("");
    return vehicles;
  }

  @Override
  public JSONObject addVehicle(Vehicle vehicle) throws VertxAppException {
    JSONObject vehicleJson = new JSONObject();
    try {
      Vehicle vehicle1 = (Vehicle) databaseLayer.saveObject(vehicle);
      String vehicleJsonString = JsonUtil.createJsonFromObject(vehicle1);
      vehicleJson = JsonUtil.createObjectFromString(vehicleJsonString, JSONObject.class);
    } catch (Exception e) {
      log.error("Error in adding vehicle: {}", e.getMessage());
      throw new VertxAppException("Error in adding the vehicle", e);
    }
    return vehicleJson;
  }

  @Override
  public JSONObject getVehicle(long vehicleId) throws VertxAppException {
    JSONObject vehicleJson;
    try {
      Vehicle vehicle = databaseLayer.getObjectById(Vehicle.class, vehicleId);
      vehicleJson =
          JsonUtil.createObjectFromString(JsonUtil.createJsonFromObject(vehicle), JSONObject.class);
    } catch (Exception e) {
      log.error("Error in getting vehicle by id for id: {}, {}", vehicleId, e.getMessage());
      e.printStackTrace();
      throw e;
    }
    return vehicleJson;
  }
}
