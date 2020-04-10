package com.jatinkheradiya.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.ServiceRequest;
import com.jatinkheradiya.app.exceptions.VertxAppException;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseLayer {

  private static final String URI = "jdbc:mysql://localhost:3306";

  private static final String USERNAME = "jatin";

  private static final String PASSWORD = "Jatin@123";

  //  MysqlDataSource mysqlDataSource = new MysqlDataSource();

  private Connection connection;

  public DatabaseLayer() {
    try {
      setConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  private void setConnection() throws SQLException {
    //    mysqlDataSource.setUser(USERNAME);
    //    mysqlDataSource.setPassword(PASSWORD);
    //    //    mysqlDataSource.setServerName("myDBHost.example.org");
    //    mysqlDataSource.setServerName("localhost/test1");
  }

  public JSONArray getServiceRequests(final String userId) {
    List<ServiceRequest> serviceRequests = new ArrayList<>();
    JSONArray jsonArray = new JSONArray();
    //    ResultSet resultSet = null;
    //    String sqlQuery = "select * from ServiceRequests where user_id = " + userId;
    String sqlQuery = "select * from test1.ServiceRequest;";

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection connection =
          DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", USERNAME, PASSWORD);
      //        Connection connection = mysqlDataSource.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sqlQuery);
      //      resultSet = connection.createStatement().executeQuery(sqlQuery);
      ResultSetMetaData rsmd = resultSet.getMetaData();
      while (resultSet.next()) {
        int numColumns = rsmd.getColumnCount();
        JSONObject obj = new JSONObject();
        for (int i = 1; i <= numColumns; i++) {
          String column_name = rsmd.getColumnLabel(i);
          obj.put(column_name, resultSet.getObject(column_name));
        }
        //			  jsonArray.put(obj);
        jsonArray.add(obj);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("Class not found" + e);
    }
    return jsonArray;
  }

  public ServiceRequest storeServiceRequest(ServiceRequest serviceRequest) {

    long id = RandomUtils.nextLong();
    serviceRequest.setId("aa");
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-1");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    entityTransaction.begin();

    entityManager.persist(serviceRequest);
    entityManager.getTransaction().commit();
    entityManager.clear();
    entityManagerFactory.close();

    return null;
  }

  public JSONArray getCarManufacturers() {
    JSONArray jsonArray = new JSONArray();

    String sqlQuery = "select * from test1.car_manufacturers;";

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection connection =
          DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", USERNAME, PASSWORD);
      //        Connection connection = mysqlDataSource.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sqlQuery);
      //      resultSet = connection.createStatement().executeQuery(sqlQuery);
      ResultSetMetaData rsmd = resultSet.getMetaData();
      while (resultSet.next()) {
        int numColumns = rsmd.getColumnCount();
        JSONObject obj = new JSONObject();
        for (int i = 1; i <= numColumns; i++) {
          String column_name = rsmd.getColumnLabel(i);
          obj.put(column_name, resultSet.getObject(column_name));
        }
        //			  jsonArray.put(obj);
        jsonArray.add(obj);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("Class not found" + e);
    }
    return jsonArray;
  }

  public JSONArray getCarMakeModelMap() {

    JSONArray jsonArray = new JSONArray();
    String sqlQuery =
        "select make, group_concat(model separator ',') as models from car_make_model_map group by make;";

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection connection =
          DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", USERNAME, PASSWORD);
      //        Connection connection = mysqlDataSource.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sqlQuery);
      //      resultSet = connection.createStatement().executeQuery(sqlQuery);
      ResultSetMetaData rsmd = resultSet.getMetaData();
      while (resultSet.next()) {
        int numColumns = rsmd.getColumnCount();
        JSONObject obj = new JSONObject();
        for (int i = 1; i <= numColumns; i++) {
          String column_name = rsmd.getColumnLabel(i);
          obj.put(column_name, resultSet.getObject(column_name));
        }
        //			  jsonArray.put(obj);
        jsonArray.add(obj);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("Class not found" + e);
    }
    return jsonArray;
  }

  public Object saveObject(Object object) throws VertxAppException {
    Session session = null;
    Object dbObject;
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      dbObject = session.save(object);
      return object;
    } catch (Exception e) {
      System.out.println("Error in adding object: " + e.getMessage());
      //      e.printStackTrace();
      throw new VertxAppException("Error in saving the object in DB", e);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
//    return object;
  }

  public <T> T getObjectById(Class<T> clazzValue, long id) throws VertxAppException {
    Session session = null;
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      return session.get(clazzValue, id);
    } catch (Exception e) {
      System.out.println("Error in adding object: " + e.getMessage());
      //      e.printStackTrace();
      throw new VertxAppException("Error in saving the object in DB", e);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
//    return dbObject;
  }
}