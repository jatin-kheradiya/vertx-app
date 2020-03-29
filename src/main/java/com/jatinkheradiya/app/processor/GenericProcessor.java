package com.jatinkheradiya.app.processor;

import org.json.simple.JSONArray;

import com.jatinkheradiya.app.repo.impl.GenericRepoImpl;

public class GenericProcessor {

	private GenericRepoImpl genericRepoImpl = new GenericRepoImpl();

	public String getCarManufacturers() throws Exception {

		String responseStr = "NA";
		JSONArray response = genericRepoImpl.getCarManufacturers();
		if(response != null) {
			responseStr = response.toJSONString();
		}
		return responseStr;
	}

	public String getCarModels() {
		String responseStr = "NA";
		JSONArray response = genericRepoImpl.getCarMakeModelMap();
		if(response != null) {
			responseStr = response.toJSONString();
		}
		return responseStr;
	}

	//  public void storeServiceRequest(ServiceRequest serviceRequest) {
//		serviceRequestRepoImpl.storeServiceRequest(serviceRequest);
//  }
}
