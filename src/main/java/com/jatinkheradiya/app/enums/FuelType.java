package com.jatinkheradiya.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum FuelType {

  PETROL("PETROL"),

  DIESEL("DIESEL"),

  CNG("CNG");

  private FuelType(String value) {
    this.value = value;
  }

  private String value;

  public String getValue() {
    return value;
  }

  @JsonCreator
  public static FuelType fromValue(String v) {
    for (FuelType myEnum : values()) {
      if (myEnum.value.equalsIgnoreCase(v)) {
        return myEnum;
      }
    }
    throw new IllegalArgumentException("invalid string value passed: " + v);
  }

}
