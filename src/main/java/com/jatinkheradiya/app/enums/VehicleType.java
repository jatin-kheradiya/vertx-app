package com.jatinkheradiya.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

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

  @JsonCreator
  public static VehicleType fromValue(String v) {
    for (VehicleType myEnum : values()) {
      if (myEnum.value.equalsIgnoreCase(v)) {
        return myEnum;
      }
    }
    throw new IllegalArgumentException("invalid string value passed: " + v);
  }
}
