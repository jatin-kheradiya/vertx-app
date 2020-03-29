package com.jatinkheradiya.app.db;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    SessionFactory sessionFactory = null;
    try {
//       sessionFactory = new AnnotationConfiguration().configure(new File("/Users/jatin/Downloads/My_stuff/code/vertx-app/src/main/resources/hibernate.cfg.xml")).buildSessionFactory();
      sessionFactory = new Configuration().configure().buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      // Make sure you log the exception, as it might be swallowed
      System.err.println("Initial SessionFactory creation failed." + e);
//      throw new ExceptionInInitializerError(e);
    }
    return sessionFactory;
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void shutdown() {
    // Close caches and connection pools
    getSessionFactory().close();
  }


}
