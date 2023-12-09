package com.comp440.registration;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitializeDatabaseServlet
 */
@WebServlet("/initializedb")
public class InitializeDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
	    RequestDispatcher dispatcher=null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");
			
			Statement statement = con.createStatement();
	            
	            statement.executeUpdate("DROP TABLE IF EXISTS reviews;");
	            statement.executeUpdate("DROP TABLE IF EXISTS has_category;");
	            statement.executeUpdate("DROP TABLE IF EXISTS items;");
	            statement.executeUpdate("DROP TABLE IF EXISTS category;");
	            statement.executeUpdate("DROP TABLE IF EXISTS user;");
	            
	            
	            String createUserTableSQL = "CREATE TABLE user ("
	                    + "username varchar(255) NOT NULL, "
	                    + "password varchar(255), "
	                    + "FirstName varchar(255), "
	                    + "LastName varchar(255), "
	                    + "email varchar(255), "
	                    + "PRIMARY KEY (username), "
	                    + "UNIQUE (email)"
	                    + ")";
	            statement.executeUpdate(createUserTableSQL);

	            // Create items table with foreign key reference to user
	            String createItemsTableSQL = "CREATE TABLE items ("
	                    + "item_id int NOT NULL AUTO_INCREMENT, "
	                    + "item_title varchar(255), "
	                    + "item_description text, "
	                    + "date_posted date, "
	                    + "price decimal(10,2), "
	                    + "username varchar(255), "
	                    + "PRIMARY KEY (item_id), "
	                    + "FOREIGN KEY (username) REFERENCES user(username)"
	                    + ")";
	            statement.executeUpdate(createItemsTableSQL);

	            // Create category table
	            String createCategoryTableSQL = "CREATE TABLE category ("
	                    + "category_id int NOT NULL AUTO_INCREMENT, "
	                    + "category_name varchar(255) UNIQUE, "
	                    + "PRIMARY KEY (category_id)"
	                    + ")";
	            statement.executeUpdate(createCategoryTableSQL);

	            // Create has_category table with foreign key references to items and category
	            String createHasCategoryTableSQL = "CREATE TABLE has_category ("
	                    + "item_id int, "
	                    + "category_id int, "
	                    + "PRIMARY KEY (item_id, category_id), "
	                    + "FOREIGN KEY (item_id) REFERENCES items(item_id), "
	                    + "FOREIGN KEY (category_id) REFERENCES category(category_id)"
	                    + ")";
	            statement.executeUpdate(createHasCategoryTableSQL);

	            // Create reviews table with foreign key references to user and items
	            String createReviewsTableSQL = "CREATE TABLE reviews ("
	                    + "ReviewId int NOT NULL AUTO_INCREMENT, "
	                    + "UserName varchar(255), "
	                    + "ItemId int, "
	                    + "Score enum('Excellent','Good','Fair','Poor'), "
	                    + "Remark text, "
	                    + "Date date, "
	                    + "PRIMARY KEY (ReviewId), "
	                    + "FOREIGN KEY (UserName) REFERENCES user(username), "
	                    + "FOREIGN KEY (ItemId) REFERENCES items(item_id)"
	                    + ")";
	            statement.executeUpdate(createReviewsTableSQL);
	           
	           
	            populateUserTable(con);
	            populateItemsTable(con);
	            populateCategoryTable(con);
	            populateHasCategoryTable(con);
                populateReviewsTable(con);
	            
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 dispatcher = request.getRequestDispatcher("index.jsp");
	         dispatcher.forward(request, response);
	     	
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}


	private void populateReviewsTable(Connection con) {
		try {
			  String insertUserSQL = "INSERT INTO reviews (UserName, ItemId,Score,Remark, Date) VALUES (?,?,?,?,?)";
		        PreparedStatement preparedStatement = con.prepareStatement(insertUserSQL);
		        
		       
		        preparedStatement.setString(1, "varsha");
		        preparedStatement.setString(2, "1");
		        preparedStatement.setString(3, "Good");
		        preparedStatement.setString(4, "my mom gifted me this");
		        preparedStatement.setString(5, "2023-11-04");
		        preparedStatement.executeUpdate();
		        
		        
		        preparedStatement.setString(1, "priya");
		        preparedStatement.setString(2, "2");
		        preparedStatement.setString(3, "Fair");
		        preparedStatement.setString(4, "looks so elegant");
		        preparedStatement.setString(5, "2023-11-04");
		        preparedStatement.executeUpdate();
		        
		        preparedStatement.setString(1, "john");
		        preparedStatement.setString(2, "1");
		        preparedStatement.setString(3, "Fair");
		        preparedStatement.setString(4, "very light weight");
		        preparedStatement.setString(5, "2023-11-04");
		        preparedStatement.executeUpdate();
		       
		        
		        preparedStatement.setString(1, "emily");
		        preparedStatement.setString(2, "4");
		        preparedStatement.setString(3, "Excellent");
		        preparedStatement.setString(4, "Very good results");
		        preparedStatement.setString(5, "2023-11-04");
		        preparedStatement.executeUpdate();
		        
		        preparedStatement.setString(1, "alex");
		        preparedStatement.setString(2, "5");
		        preparedStatement.setString(3, "Good");
		        preparedStatement.setString(4, "");
		        preparedStatement.setString(5, "2023-11-04");
		        preparedStatement.executeUpdate();
		                 
		                
		            }
			catch(Exception e)
			{
				e.printStackTrace();  
		        
			}
		
	}


	private void populateHasCategoryTable(Connection con) {
		try {
			  String insertUserSQL = "INSERT INTO has_category (item_id,category_id) VALUES (?,?)";
		        PreparedStatement preparedStatement = con.prepareStatement(insertUserSQL);
		            
		                preparedStatement.setString(1, "1");
		                preparedStatement.setString(2, "1");
		                preparedStatement.executeUpdate();
		              
		                preparedStatement.setString(1, "1");
		                preparedStatement.setString(2, "2");
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "2");
		                preparedStatement.setString(2, "3");
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "2");
		                preparedStatement.setString(2, "4");
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "3");
		                preparedStatement.setString(2, "5");
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "3");
		                preparedStatement.setString(2, "6");
		                preparedStatement.executeUpdate();
		              
		                preparedStatement.setString(1, "4");
		                preparedStatement.setString(2, "7");
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "4");
		                preparedStatement.setString(2, "8");
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "5");
		                preparedStatement.setString(2, "1");
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "5");
		                preparedStatement.setString(2, "2");
		                preparedStatement.executeUpdate();
		                
		                
		                
		            }
			catch(Exception e)
			{
				e.printStackTrace();  
		        
			}
		
	}


	private void populateCategoryTable(Connection con) {
		try {
			  String insertUserSQL = "INSERT INTO category (category_name) VALUES (?)";
		        PreparedStatement preparedStatement = con.prepareStatement(insertUserSQL);
		            
		                preparedStatement.setString(1, "apple");
		                preparedStatement.executeUpdate();
		                preparedStatement.setString(1, "electronics");
		                preparedStatement.executeUpdate();
		                preparedStatement.setString(1, "mk");
		                preparedStatement.executeUpdate();
		                preparedStatement.setString(1, "fashion");
		                preparedStatement.executeUpdate();
		                preparedStatement.setString(1, "raybon");
		                preparedStatement.executeUpdate();
		                preparedStatement.setString(1, "eyes");
		                preparedStatement.executeUpdate();
		                preparedStatement.setString(1, "skincare");
		                preparedStatement.executeUpdate();
		                preparedStatement.setString(1, "beauty");
		                preparedStatement.executeUpdate();
		               
		                
		                
		            }
			catch(Exception e)
			{
				e.printStackTrace();  
		        
			}
			
	}


	private void populateItemsTable(Connection con) {
		try {
			  String insertUserSQL = "INSERT INTO items (item_title, item_description, date_posted, price, username) VALUES (?, ?, ?, ?, ?)";
		        PreparedStatement preparedStatement = con.prepareStatement(insertUserSQL);
		            
		                preparedStatement.setString(1, "iphone");
		                preparedStatement.setString(2, "a new series phone" );
		                preparedStatement.setString(3, "2023-10-26");
		                preparedStatement.setString(4, "1000");
		                preparedStatement.setString(5, "alex" );
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "handbag");
		                preparedStatement.setString(2, "sale flat 50 percent" );
		                preparedStatement.setString(3, "2023-10-27");
		                preparedStatement.setString(4, "500");
		                preparedStatement.setString(5, "emily" );
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "shades");
		                preparedStatement.setString(2, "shades from raybon" );
		                preparedStatement.setString(3, "2023-10-28");
		                preparedStatement.setString(4, "200");
		                preparedStatement.setString(5, "john" );
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "axis-y serum");
		                preparedStatement.setString(2, "its a discoloration serum" );
		                preparedStatement.setString(3, "2023-11-04");
		                preparedStatement.setString(4, "30");
		                preparedStatement.setString(5, "priya" );
		                preparedStatement.executeUpdate();
		                
		                preparedStatement.setString(1, "macbook");
		                preparedStatement.setString(2, "i has m2 chip" );
		                preparedStatement.setString(3, "2023-11-04");
		                preparedStatement.setString(4, "1500");
		                preparedStatement.setString(5, "varsha" );
		                preparedStatement.executeUpdate();
		            }
			catch(Exception e)
			{
				e.printStackTrace();  
		        
			}
			
		}
		
	

	private void populateUserTable(Connection con) {
		
		try {
		  String insertUserSQL = "INSERT INTO user (username, password, FirstName, LastName, email) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = con.prepareStatement(insertUserSQL);
	            
	                preparedStatement.setString(1, "john");
	                preparedStatement.setString(2, "password1" );
	                preparedStatement.setString(3, "john");
	                preparedStatement.setString(4, "doe");
	                preparedStatement.setString(5, "john.doe@gmail.com" );
	                preparedStatement.executeUpdate();
	                
	                preparedStatement.setString(1, "emily");
	                preparedStatement.setString(2, "password2" );
	                preparedStatement.setString(3, "emily");
	                preparedStatement.setString(4, "brown");
	                preparedStatement.setString(5, "emily.b@yahoo.com" );
	                preparedStatement.executeUpdate();
	                
	                preparedStatement.setString(1, "alex");
	                preparedStatement.setString(2, "password3" );
	                preparedStatement.setString(3, "alex");
	                preparedStatement.setString(4, "wilson");
	                preparedStatement.setString(5, "wilson.a@hotmail.com" );
	                preparedStatement.executeUpdate();
	                
	                preparedStatement.setString(1, "priya");
	                preparedStatement.setString(2, "password4" );
	                preparedStatement.setString(3, "priya");
	                preparedStatement.setString(4, "rao");
	                preparedStatement.setString(5, "raopriya@gmail.com" );
	                preparedStatement.executeUpdate();
	                
	                preparedStatement.setString(1, "varsha");
	                preparedStatement.setString(2, "password5" );
	                preparedStatement.setString(3, "varsha");
	                preparedStatement.setString(4, "reddy");
	                preparedStatement.setString(5, "varsha5@gmail.com" );
	                preparedStatement.executeUpdate();
	            }
		catch(Exception e)
		{
			e.printStackTrace();  
	        
		}
		
	}
	        
		
	}
	
	


