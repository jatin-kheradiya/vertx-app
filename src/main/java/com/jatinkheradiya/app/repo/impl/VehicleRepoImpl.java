package com.jatinkheradiya.app.repo.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.db.DatabaseLayer;
import com.jatinkheradiya.app.db.HibernateUtil;
import com.jatinkheradiya.app.entities.Vehicle;
import com.jatinkheradiya.app.repo.VehicleRepo;

public class VehicleRepoImpl implements VehicleRepo {

  private DatabaseLayer databaseLayer = new DatabaseLayer();

  @Override
  public JSONArray getVehicles(String userId) {
    JSONArray vehicles = databaseLayer.getServiceRequests(userId);
    return vehicles;
  }

  @Override
  public JSONObject addVehicle(Vehicle vehicle) {
//    return databaseLayer.storeVehicle(vehicle);
    Session session = null;
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      session.save(vehicle);
      session.getTransaction().commit();
//      HibernateUtil.shutdown();
    } catch (Exception e) {
      System.out.println("Error in adding vehicle: " + e.getMessage());
      e.printStackTrace();
    } finally {
      if(session != null) {
        session.close();
      }
    }
    return new JSONObject();
//    return null;
  }
}
