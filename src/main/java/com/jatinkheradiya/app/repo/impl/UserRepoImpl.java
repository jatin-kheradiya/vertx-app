package com.jatinkheradiya.app.repo.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.db.DatabaseLayer;
import com.jatinkheradiya.app.db.HibernateUtil;
import com.jatinkheradiya.app.entities.User;
import com.jatinkheradiya.app.entities.Vehicle;
import com.jatinkheradiya.app.exceptions.VertxAppException;
import com.jatinkheradiya.app.repo.UserRepo;
import com.jatinkheradiya.app.utils.JsonUtil;

public class UserRepoImpl implements UserRepo {

  private DatabaseLayer databaseLayer = new DatabaseLayer();

  @Override
  public JSONObject addUser(User user) throws VertxAppException {
    Session session = null;
    JSONObject userJson;
    try {
//      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//      session = sessionFactory.openSession();
//      session.beginTransaction();
//      session.save(user);
//      session.getTransaction().commit();
      User userSaved = (User) databaseLayer.saveObject(user);
      String userJsonString = JsonUtil.createJsonFromObject(user);
      userJson = JsonUtil.createObjectFromString(userJsonString, JSONObject.class);
      //      HibernateUtil.shutdown();
    } catch (Exception e) {
      System.out.println("Error in adding user: " + e.getMessage());
//      e.printStackTrace();
      throw new VertxAppException("Error in adding the user", e);
    } finally {
//      if (session != null) {
//        session.close();
//      }
    }
//    return new JSONObject();
    //    return null;
    return userJson;
  }

  @Override
  public JSONObject getUser(String userId) throws VertxAppException {
    Session session = null;
    Vehicle vehicle;
    JSONObject userJson = new JSONObject();
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      vehicle = (Vehicle) session.createQuery("from User where id = " + userId)
          .getSingleResult();
      session.getTransaction().commit();
      //      HibernateUtil.shutdown();

      userJson =
          JsonUtil.createObjectFromString(JsonUtil.createJsonFromObject(vehicle), JSONObject.class);
    } catch (Exception e) {
      System.out.println("Error in getting vehicle by id for id: " + userId + "\n" + e.getMessage());
      e.printStackTrace();
      throw e;
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return userJson;
  }
}
