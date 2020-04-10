package com.jatinkheradiya.app.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jatinkheradiya.app.enums.FuelType;
import com.jatinkheradiya.app.enums.VehicleType;

@Entity
@org.hibernate.annotations.Immutable()
@Table(name = "vehicle", uniqueConstraints = {
    @UniqueConstraint(columnNames =  "id")
})
public class Vehicle implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private long id;

  @Column(name = "type", nullable = false, length = 10)
  @Enumerated(EnumType.STRING)
  private VehicleType type;

  @Column(name = "make", nullable = false, length = 50)
  private String make;

  @Column(name = "model", nullable = false, length = 50)
  private String model;

  @Column(name = "year", nullable = false, length = 10)
  private int year;

  @Column(name = "color", nullable = false, length = 20)
  private String color;

  @JsonProperty("fuel_type")
  @Column(name = "fuel_type", nullable = false, length = 10)
  @Enumerated(EnumType.STRING)
  private FuelType fuelType;

  @JsonProperty("registration_number")
  @Column(name = "registration_number", nullable = false, length = 12)
  private String registrationNumber;

  //  @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private User user;

////  @Column(name = "service_request_ids")
////  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
//  @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
////  @JoinColumn(name = "vehicle_id")
//  private List<ServiceRequest> serviceRequestList;

//  public List<ServiceRequest> getServiceRequestList() {
//    return serviceRequestList;
//  }
//
//  public void setServiceRequestList(List<ServiceRequest> serviceRequestList) {
//    this.serviceRequestList = serviceRequestList;
//  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public VehicleType getType() {
    return type;
  }

  public void setType(VehicleType type) {
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

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public FuelType getFuelType() {
    return fuelType;
  }

  public void setFuelType(FuelType fuelType) {
    this.fuelType = fuelType;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  @JsonIgnore
  public User getUser() {
    return user;
  }

  @JsonIgnore
  public void setUser(User user) {
    this.user = user;
  }

  //getter method to retrieve the UserId
  public Long getUser_id(){
    return user.getId();
  }

}
