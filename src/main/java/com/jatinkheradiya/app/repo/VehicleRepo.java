package com.jatinkheradiya.app.repo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.Vehicle;
import com.jatinkheradiya.app.exceptions.VertxAppException;

public interface VehicleRepo {

  JSONArray getVehiclesbyUserId(final String userId) throws VertxAppException;

  JSONObject addVehicle(Vehicle vehicle) throws VertxAppException;

  JSONObject getVehicle(final String vehicleId) throws VertxAppException;
}
