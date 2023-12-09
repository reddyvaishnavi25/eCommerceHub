package com.comp440.registration;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServletContextListner implements ServletContextListener {
	
    public void contextDestroyed(ServletContextEvent event) {
       
    	
    	 try {
             java.sql.DriverManager.deregisterDriver(java.sql.DriverManager.getDriver("jdbc:mysql://localhost:3306/comp440?useSSL=false"));
         } catch (SQLException e) {
             // Handle errors if needed
             e.printStackTrace();
         }

         // Stop the Abandoned Connection Cleanup Thread
      //   com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
     }
    	
    }

    // Implement other methods if needed

