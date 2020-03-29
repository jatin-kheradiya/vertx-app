package com.jatinkheradiya.app.processor;

import org.json.simple.JSONArray;

import com.jatinkheradiya.app.entities.ServiceRequest;
import com.jatinkheradiya.app.repo.impl.ServiceRequestRepoImpl;

public class ServiceRequestProcessor {
	
	private ServiceRequestRepoImpl serviceRequestRepoImpl = new ServiceRequestRepoImpl();
	
	public String getResponse(final String userId) throws Exception {

		String responseStr = "Not found";
		JSONArray response = serviceRequestRepoImpl.getServiceRequests(userId);
		if(response != null) {
			responseStr = response.toJSONString();
		}
		return responseStr;
	}

  public void storeServiceRequest(ServiceRequest serviceRequest) {
		serviceRequestRepoImpl.storeServiceRequest(serviceRequest);
  }
}
