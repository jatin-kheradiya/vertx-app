package com.jatinkheradiya.app.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionFactory {

  /** The instance. */
  private static DBConnectionFactory INSTANCE;

  /** The is db set. */
  private static boolean isDbSet = false;

  private static final String URI = "jdbc:mysql://localhost:3306";

  private static final String USERNAME = "jatin";

  private static final String PASSWORD = "Jatin@123";

  /**
   * This method is needed for initial DB bootstrap. Singleton behaviour is preserved using isDbSet
   * field.
   *
   * @throws Exception if some error exists while setting up error.
   */
  public void bootstrapDb() throws Exception {
    if (isDbSet == false) {
      setUpDb();
      isDbSet = true;
    }
  }

  /**
   * This method is used to get singleton instance.
   *
   * @return singleton instance
   */
  public static DBConnectionFactory getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new DBConnectionFactory();
    }
    return INSTANCE;
  }

  /**
   * Sets the up db.
   *
   * @throws Exception while executing or setting the DB
   */
  private void setUpDb() throws Exception {
    try {
//      MongoClient mongoClient = new MongoClientCreator().generateMongoClient();
//      LOG.info("Mongo client created: {}", mongoClient);
      //      createDbDatastore(mongoClient);

      Connection connection =
          DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", USERNAME, PASSWORD);
    } catch (Exception e) {
//      Log.error("Error occured while bootstraping DB: " + e);
      throw e;
    }
  }

}
