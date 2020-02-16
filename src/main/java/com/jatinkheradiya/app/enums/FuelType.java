package com.jatinkheradiya.app.enums;

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

}
