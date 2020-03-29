package com.jatinkheradiya.app.processor;

import org.json.simple.JSONArray;

import com.jatinkheradiya.app.entities.Vehicle;
import com.jatinkheradiya.app.repo.impl.VehicleRepoImpl;

public class VehicleProcessor {
	
	private VehicleRepoImpl vehicleRepoImpl = new VehicleRepoImpl();
	
	public String getResponse(final String userId) {

		String responseStr = "Not found";
		JSONArray response = vehicleRepoImpl.getVehicles(userId);
		if(response != null) {
			responseStr = response.toJSONString();
		}
		return responseStr;
	}

  public void addVehicle(Vehicle vehicle) {
		vehicleRepoImpl.addVehicle(vehicle);
  }
}
