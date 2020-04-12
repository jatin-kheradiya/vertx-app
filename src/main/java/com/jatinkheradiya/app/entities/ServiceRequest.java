package com.jatinkheradiya.app.entities;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "service_request")
public class ServiceRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private long id;

//  @JoinColumn(name = "vehicle_id", nullable = false)
//  @OnDelete(action = OnDeleteAction.CASCADE)
//  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonProperty("vehicle_id")
  @Column(name = "vehicle_id")
  private Long vehicleId;

  @JsonProperty("start_time")
  @Column(name = "start_time")
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Kolkata")
  //  @JsonFormat(shape=JsonFormat.Shape.NATURAL, pattern="s")
  private Timestamp startTime;

  @JsonProperty("end_time")
  @Column(name = "end_time")
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Kolkata")
  private Timestamp endTime;

  @Column(name = "status", nullable = false, length = 10)
  private String status;

  @JsonProperty("service_provider_name")
  @Column(name = "service_provider_name", length = 100)
  private String serviceProvider;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getServiceProvider() {
    return serviceProvider;
  }

  public void setServiceProvider(String serviceProvider) {
    this.serviceProvider = serviceProvider;
  }
}
