package com.jatinkheradiya.app.repo.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.db.DatabaseLayer;
import com.jatinkheradiya.app.db.HibernateUtil;
import com.jatinkheradiya.app.entities.Vehicle;
import com.jatinkheradiya.app.exceptions.VertxAppException;
import com.jatinkheradiya.app.repo.VehicleRepo;
import com.jatinkheradiya.app.utils.JsonUtil;

import io.vertx.core.json.JsonObject;

public class VehicleRepoImpl implements VehicleRepo {

  private DatabaseLayer databaseLayer = new DatabaseLayer();

  @Override
  public JSONArray getVehiclesbyUserId(String userId) throws VertxAppException {
    JSONArray vehicles = databaseLayer.getServiceRequests(userId);
    return vehicles;
  }

  @Override
  public JSONObject addVehicle(Vehicle vehicle) throws VertxAppException {
    //    return databaseLayer.storeVehicle(vehicle);
    Session session = null;
    JSONObject vehicleJson = new JSONObject();
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      session.save(vehicle);
      session.getTransaction().commit();
      String vehicleJsonString = JsonUtil.createJsonFromObject(vehicle);
      vehicleJson = JsonUtil.createObjectFromString(vehicleJsonString, JSONObject.class);
      //      HibernateUtil.shutdown();
    } catch (Exception e) {
      System.out.println("Error in adding vehicle: " + e.getMessage());
//      e.printStackTrace();
      throw new VertxAppException("Error in adding the vehicle", e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
//    return new JSONObject();
    //    return null;
    return vehicleJson;
  }

  @Override
  public JSONObject getVehicle(String vehicleId) throws VertxAppException {
    Session session = null;
    Vehicle vehicle;
    JSONObject vehicleJson = new JSONObject();
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      vehicle = (Vehicle) session.createQuery("from Vehicle where id = " + vehicleId)
          .getSingleResult();
      session.getTransaction().commit();
      //      HibernateUtil.shutdown();

      vehicleJson =
          JsonUtil.createObjectFromString(JsonUtil.createJsonFromObject(vehicle), JSONObject.class);
    } catch (Exception e) {
      System.out.println("Error in getting vehicle by id for id: " + vehicleId + "\n" + e.getMessage());
      e.printStackTrace();
      throw e;
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return vehicleJson;
  }
}
