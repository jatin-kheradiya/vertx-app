package com.jatinkheradiya.app.repo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.Vehicle;

public interface VehicleRepo {

  public JSONArray getVehicles(final String userId);

  public JSONObject addVehicle(Vehicle vehicle);
}
