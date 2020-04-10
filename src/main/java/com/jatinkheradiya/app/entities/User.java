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

@Entity
//@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL)
@org.hibernate.annotations.Immutable()
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames =  "id")
})
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private String id;

  @Column(name = "name", nullable = false, length = 40)
  private String name;

  @Column(name = "mobile", nullable = false, length = 20)
  private String mobile;

  @Column(name = "email", length = 50)
  private String email;

  @Column(name = "password", length = 50)
  private String password;

  @Column(name = "date_of_birth", length = 20)
  private String dateOfBirth;

  @Column(name = "referral_code", length = 20)
  private String referralCode;

  @Column(name = "gender", length = 10)
  private String gender;

  @Column(name = "login_time", length = 20)
  private String loginTime;

//  private List<Vehicle> vehicles;

  @Column(name = "vehicles")
  private String vehicles;


  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public String getReferralCode() {
    return referralCode;
  }

  public String getGender() {
    return gender;
  }

  public String getLoginTime() {
    return loginTime;
  }

  public String getVehicles() {
    return vehicles;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setReferralCode(String referralCode) {
    this.referralCode = referralCode;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setLoginTime(String loginTime) {
    this.loginTime = loginTime;
  }

  public void setVehicles(String vehicles) {
    this.vehicles = vehicles;
  }
}
