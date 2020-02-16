package com.jatinkheradiya.app.enums;

public enum VehicleType {

  CAR("CAR"),

  BIKE("BIKE");

  private VehicleType(String value) {
    this.value = value;
  }

  private String value;

  public String getValue() {
    return value;
  }

}
