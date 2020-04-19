package com.jatinkheradiya.app.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jatinkheradiya.app.exceptions.VertxAppException;


public class DatabaseLayer {

  private Logger log = LoggerFactory.getLogger(DatabaseLayer.class);

  public DatabaseLayer() {}


  //  public JSONArray getServiceRequests(final String userId) {
  //    List<ServiceRequest> serviceRequests = new ArrayList<>();
  //    JSONArray jsonArray = new JSONArray();
  //    //    ResultSet resultSet = null;
  //    //    String sqlQuery = "select * from ServiceRequests where user_id = " + userId;
  //    String sqlQuery = "select * from test1.ServiceRequest;";
  //
  //    try {
  //      Class.forName("com.mysql.jdbc.Driver");
  //      Connection connection =
  //          DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", USERNAME, PASSWORD);
  //      //        Connection connection = mysqlDataSource.getConnection();
  //      Statement statement = connection.createStatement();
  //      ResultSet resultSet = statement.executeQuery(sqlQuery);
  //      //      resultSet = connection.createStatement().executeQuery(sqlQuery);
  //      ResultSetMetaData rsmd = resultSet.getMetaData();
  //      while (resultSet.next()) {
  //        int numColumns = rsmd.getColumnCount();
  //        JSONObject obj = new JSONObject();
  //        for (int i = 1; i <= numColumns; i++) {
  //          String column_name = rsmd.getColumnLabel(i);
  //          obj.put(column_name, resultSet.getObject(column_name));
  //        }
  //        //			  jsonArray.put(obj);
  //        jsonArray.add(obj);
  //      }
  //
  //    } catch (SQLException e) {
  //      e.printStackTrace();
  //    } catch (ClassNotFoundException e) {
  //      e.printStackTrace();
  //      log.error("Class not found" + e);
  //    }
  //    return jsonArray;
  //  }

  //  public ServiceRequest storeServiceRequest(ServiceRequest serviceRequest) {
  //
  //    long id = RandomUtils.nextLong();
  //    serviceRequest.setId(id);
  //    EntityManagerFactory entityManagerFactory =
  //        Persistence.createEntityManagerFactory("persistence-1");
  //    EntityManager entityManager = entityManagerFactory.createEntityManager();
  //    EntityTransaction entityTransaction = entityManager.getTransaction();
  //
  //    entityTransaction.begin();
  //
  //    entityManager.persist(serviceRequest);
  //    entityManager.getTransaction().commit();
  //    entityManager.clear();
  //    entityManagerFactory.close();
  //
  //    return null;
  //  }

  public List getCarManufacturers() throws VertxAppException {
    Session session = null;
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      return session.createSQLQuery("select make from vertx_app.car_manufacturers").list();
    } catch (Exception e) {
      String errorMessage = "Error in getting entries from DB for car manufacturers";
      log.error(errorMessage, e.getMessage());
      throw new VertxAppException(errorMessage, e);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  public List getCarMakeModelMap() throws VertxAppException {
    Session session = null;
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      return session.createSQLQuery("select make, cast(model as text) from vertx_app.car_make_model_map order by make;")
          .list();
    } catch (Exception e) {
      String errorMessage = "Error in getting entries from DB for car make model map";
      log.error(errorMessage, e.getMessage());
      throw new VertxAppException(errorMessage, e);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  public Object saveObject(Object object) throws VertxAppException {
    Session session = null;
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      Object dbObject = session.save(object);
      return object;
    } catch (Exception e) {
      log.error("Error in adding object: {}", e.getMessage());
      //      e.printStackTrace();
      throw new VertxAppException("Error in saving the object in DB", e);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  public <T> T getObjectById(Class<T> clazzValue, long id) throws VertxAppException {
    Session session = null;
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();
      return session.get(clazzValue, id);
    } catch (Exception e) {
      log.error("Error in adding object: " + e.getMessage());
      //      e.printStackTrace();
      throw new VertxAppException("Error in saving the object in DB", e);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  public <T> List<T> getObjectsByParam(Map<String, Object> andConditions,
      Map<String, Object> orConditions, Class<T> clazzValue) throws VertxAppException {
    Session session = null;
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
      session.beginTransaction();

      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazzValue);
      Root<T> rootObject = criteriaQuery.from(clazzValue);

      Conjunction conjunctionAnd = Restrictions.conjunction();
      updateAndConditions(conjunctionAnd, andConditions);
      Criterion and = Restrictions.allEq(andConditions);

      Criteria criteria = session.createCriteria(clazzValue);
      //      criteria.add(conjunctionAnd);

      criteria.add(and);

      return criteria.list();
    } catch (Exception e) {
      String errorMessage = "Error in getting entities from DB for parameters";
      log.error(errorMessage, e.getMessage());
      throw new VertxAppException(errorMessage, e);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  private void updateAndConditions(Conjunction conjunctionAnd, Map<String, Object> andConditions) {
    if (andConditions != null) {
      for (Map.Entry<String, Object> entry : andConditions.entrySet()) {
        String key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof Boolean) {
          conjunctionAnd.add(Restrictions.eq(key, Boolean.parseBoolean(value.toString())));
        } else if (value instanceof Integer) {
          conjunctionAnd.add(Restrictions.eq(key, Integer.parseInt(value.toString())));
        } else if (value instanceof Float) {
          conjunctionAnd.add(Restrictions.eq(key, Float.parseFloat(value.toString())));
        } else if (value instanceof List<?>) {
          String[] parts = key.trim().split(" ");
          if (Arrays.stream(parts).anyMatch("not"::equals)) {
            conjunctionAnd.add(Restrictions.not(Restrictions.in(parts[0], value)));
          } else {
            conjunctionAnd.add(Restrictions.in(key, value));
          }
        } else {
          conjunctionAnd.add(Restrictions.eq(key, value.toString()));
        }
      }
    }
  }

}