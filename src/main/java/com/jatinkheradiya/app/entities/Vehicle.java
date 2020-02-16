package com.jatinkheradiya.app.entities;

import java.util.List;

import com.jatinkheradiya.app.enums.FuelType;
import com.jatinkheradiya.app.enums.VehicleType;

public class Vehicle {

  private String id;

  private VehicleType type;

  private String make;

  private String model;

  private String year;

  private String color;

  private FuelType fuelType;

  private String registrationNumber;

  private List<ServiceRequest> serviceRequestList;
}
