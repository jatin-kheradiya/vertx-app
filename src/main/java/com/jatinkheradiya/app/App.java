package com.jatinkheradiya.app;
import io.vertx.core.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
         System.out.println( "Started the Application!!" );
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyFirstVerticle());
    }
}
