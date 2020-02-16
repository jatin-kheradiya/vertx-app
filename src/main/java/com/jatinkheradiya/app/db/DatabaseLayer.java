package com.jatinkheradiya.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jatinkheradiya.app.entities.ServiceRequest;
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
    return null;
  }

}
