package com.jatinkheradiya.app.processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.Vehicle;
import com.jatinkheradiya.app.repo.impl.VehicleRepoImpl;

public class VehicleProcessor {
	
	private VehicleRepoImpl vehicleRepoImpl = new VehicleRepoImpl();
	
	public String getResponse(final String userId) throws Exception {

		String responseStr = "Not found";
		JSONArray response = vehicleRepoImpl.getVehiclesbyUserId(userId);
		if(response != null) {
			responseStr = response.toJSONString();
		}
		return responseStr;
	}

  public void addVehicle(Vehicle vehicle) throws Exception {
		vehicleRepoImpl.addVehicle(vehicle);
  }

  public JSONObject getVehicleById(String vehicleId) throws Exception {
		return vehicleRepoImpl.getVehicle(vehicleId);
	}
}
