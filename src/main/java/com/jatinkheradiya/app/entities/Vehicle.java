package com.jatinkheradiya.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OptimisticLockType;

import com.jatinkheradiya.app.enums.FuelType;
import com.jatinkheradiya.app.enums.VehicleType;

@Entity
//@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL)
@org.hibernate.annotations.Immutable()
@Table(name = "vehicle", uniqueConstraints = {
    @UniqueConstraint(columnNames =  "id")
})
public class Vehicle implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "type", nullable = false, length = 10)
  private String type;

  @Column(name = "make", nullable = false, length = 50)
  private String make;

  @Column(name = "model", nullable = false, length = 50)
  private String model;

  @Column(name = "year", nullable = false, length = 10)
  private String year;

  @Column(name = "color", nullable = false, length = 20)
  private String color;

  @Column(name = "fuel_type", nullable = false, length = 10)
  private String fuelType;

  @Column(name = "registraion_number", nullable = false, length = 12)
  private String registrationNumber;

  @Column(name = "service_request_ids", nullable = true)
  private String serviceRequestList;
//  private List<ServiceRequest> serviceRequestList;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getFuelType() {
    return fuelType;
  }

  public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String getServiceRequestList() {
    return serviceRequestList;
  }

  public void setServiceRequestList(String serviceRequestList) {
    this.serviceRequestList = serviceRequestList;
  }
}
