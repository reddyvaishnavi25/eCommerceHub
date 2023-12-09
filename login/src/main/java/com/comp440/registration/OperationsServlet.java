package com.comp440.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comp440.model.Item;
import com.comp440.model.ItemCategory;
import com.comp440.model.UserPair;

/**
 * Servlet implementation class OperationsServlet
 */
@WebServlet("/querying")
public class OperationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option=request.getParameter("action");
		RequestDispatcher dispatcher=null;
		Connection con=null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");
			
			switch(option)
			{
			case "button1":operationOne(request,response,con);
			break;
			case "button2":	operationTwo(request,response,con);
			break;
			case "button3":operationThree(request,response,con);
			break;
			case "button4":	operationFour(request,response,con);
			break;
			case "button5":operationFive(request,response,con);
			break;
			case "button6":	operationSix(request,response,con);
			break;
			case "button7":operationSeven(request,response,con);
			break;
			case "button8":	operationEight(request,response,con);
			break;
			case "button9":operationNine(request,response,con);
			break;
			case "button10":operationTen(request,response,con);
			break;
			}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			}
	
		
	
	private void operationOne(HttpServletRequest request, HttpServletResponse response,Connection con ) {
		ArrayList<ItemCategory> arrlist=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		
		try {
			PreparedStatement st=con.prepareStatement("SELECT i.item_title, c.category_name "
					+ "FROM items i "
					+ "JOIN has_category hc ON i.item_id = hc.item_id "
					+ "JOIN category c ON hc.category_id = c.category_id "
					+ "WHERE (i.price, hc.category_id) IN ("
					+ "    SELECT MAX(i.price) as max_price, hc.category_id"
					+ "    FROM items i"
					+ "    JOIN has_category hc ON i.item_id = hc.item_id"
					+ "    GROUP BY hc.category_id"
					+ "    HAVING i.price = max_price"
					+ ")");
			ResultSet rs=st.executeQuery();
			ItemCategory ic;
			while(rs.next()) {
				  ic=new ItemCategory(rs.getString("item_title"),rs.getString("category_name"));
			       arrlist.add(ic);
		     }
			
			request.setAttribute("data",arrlist);
			dispatcher=request.getRequestDispatcher("stepone.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}

	private void operationNine(HttpServletRequest request, HttpServletResponse response,Connection con) {
		ArrayList<String> userslist=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		
		try {
			PreparedStatement st=con.prepareStatement("SELECT DISTINCT i.username\r\n"
					+ "FROM Items i\r\n"
					+ "WHERE i.username NOT IN (\r\n"
					+ "    SELECT DISTINCT i.username\r\n"
					+ "    FROM Items i\r\n"
					+ "    JOIN Reviews r ON i.item_id = r.ItemId\r\n"
					+ "    WHERE r.Score = 'Poor'\r\n"
					+ ");");
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				userslist.add(rs.getString("username"));
		     }
			
			request.setAttribute("data",userslist);
			dispatcher=request.getRequestDispatcher("stepnine.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void operationEight(HttpServletRequest request, HttpServletResponse response,Connection con) {
		ArrayList<String> userslist=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		String userX=request.getParameter("userX");
		String userY=request.getParameter("userY");
		try {
			PreparedStatement st=con.prepareStatement("SELECT fu.favorite_username\r\n"
					+ "FROM favorite_users fu\r\n"
					+ "WHERE fu.username = ? AND fu.favorite_username IN (\r\n"
					+ "    SELECT favorite_username\r\n"
					+ "    FROM favorite_users\r\n"
					+ "    WHERE username = ?\r\n"
					+ ");");
			st.setString(1, userX);
			st.setString(2,userY);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				userslist.add(rs.getString("favorite_username"));
		     }
			
			request.setAttribute("data",userslist);
			dispatcher=request.getRequestDispatcher("stepeigth.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void operationSeven(HttpServletRequest request, HttpServletResponse response,Connection con) {
		ArrayList<String> userslist=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		
		try {
			PreparedStatement st=con.prepareStatement("SELECT DISTINCT u.username\r\n"
					+ "FROM user u\r\n"
					+ "LEFT JOIN items i ON u.username = i.username\r\n"
					+ "WHERE u.username NOT IN (\r\n"
					+ "    SELECT DISTINCT u.username\r\n"
					+ "    FROM user u\r\n"
					+ "    JOIN items i ON u.username = i.username\r\n"
					+ "    JOIN reviews r ON i.item_id = r.ItemId\r\n"
					+ "    WHERE r.Score = 'Excellent'\r\n"
					+ "    GROUP BY u.username, i.item_id\r\n"
					+ "    HAVING COUNT(DISTINCT r.ReviewId) >= 3\r\n"
					+ ") OR i.username IS NULL;");
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				userslist.add(rs.getString("username"));
		     }
			
			request.setAttribute("data",userslist);
			dispatcher=request.getRequestDispatcher("stepseven.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void operationSix(HttpServletRequest request, HttpServletResponse response,Connection con) {
		
		ArrayList<String> items=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		HttpSession session=request.getSession();
		
		String u_name=request.getParameter("user_name");
		try {
			PreparedStatement st=con.prepareStatement("SELECT distinct i.item_title\r\n"
					+ "FROM items i\r\n"
					+ "JOIN reviews r ON i.item_id = r.ItemId\r\n"
					+ "WHERE i.username = ? \r\n"
					+ "AND r.Score IN ('Excellent','Good')\r\n"
					+ "AND NOT EXISTS (\r\n"
					+ "    SELECT 1\r\n"
					+ "    FROM reviews\r\n"
					+ "    WHERE ItemId = i.item_id\r\n"
					+ "    AND Score IN ('Fair', 'Poor')\r\n"
					+ ");");
			st.setString(1, u_name);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				  
			       items.add(rs.getString("item_title"));
		     }
			
			request.setAttribute("data",items);
			dispatcher=request.getRequestDispatcher("stepsix.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void operationFive(HttpServletRequest request, HttpServletResponse response,Connection con ) {
		ArrayList<String> users=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		HttpSession session=request.getSession();
		
		String cat1=request.getParameter("category1");
		String cat2=request.getParameter("category2");
		
		try {
			PreparedStatement st=con.prepareStatement("SELECT u.username\r\n"
					+ "FROM user u\r\n"
					+ "JOIN items i1 ON u.username = i1.username\r\n"
					+ "JOIN items i2 ON u.username = i2.username\r\n"
					+ "JOIN has_category hc1 ON i1.item_id = hc1.item_id\r\n"
					+ "JOIN has_category hc2 ON i2.item_id = hc2.item_id\r\n"
					+ "WHERE i1.date_posted = i2.date_posted\r\n"
					+ "  AND hc1.category_id = (SELECT category_id FROM category WHERE category_name = ?)\r\n"
					+ "  AND hc2.category_id = (SELECT category_id FROM category WHERE category_name = ?)\r\n"
					+ "  AND i1.item_id <> i2.item_id;");
			
			st.setString(1, cat1);
			st.setString(2, cat2);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				  
			       users.add(rs.getString("UserName"));
		     }
			
			request.setAttribute("data",users);
			dispatcher=request.getRequestDispatcher("stepfive.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private void operationFour(HttpServletRequest request, HttpServletResponse response,Connection con) {
		ArrayList<String> users=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		
		try {
			PreparedStatement st=con.prepareStatement("select Distinct UserName from reviews "
					+ "where UserName not in( "
					+ "select DISTINCT UserName from reviews where Score = 'Good' or Score='Fair' or Score='Excellent')");
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				  
			       users.add(rs.getString("UserName"));
		     }
			
			request.setAttribute("data",users);
			dispatcher=request.getRequestDispatcher("stepfour.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void operationThree(HttpServletRequest request, HttpServletResponse response,Connection con) {
		ArrayList<String> users=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		
		try {
			PreparedStatement st=con.prepareStatement("select distinct UserName from reviews "
					+ "where UserName not in("
					+ "select DISTINCT UserName from reviews where Score = 'Poor');"
					+ "");
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				  
			       users.add(rs.getString("UserName"));
		     }
			
			request.setAttribute("data",users);
			dispatcher=request.getRequestDispatcher("stepthree.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}

	private void operationTwo(HttpServletRequest request, HttpServletResponse response,Connection con) {
		ArrayList<String> users=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		
		try {
			PreparedStatement st=con.prepareStatement("SELECT username\r\n"
					+ "FROM items\r\n"
					+ "WHERE date_posted = '2023-11-06'\r\n"
					+ "GROUP BY username\r\n"
					+ "HAVING COUNT(*) = (\r\n"
					+ "    SELECT COUNT(*) AS max_items_count\r\n"
					+ "    FROM items\r\n"
					+ "    WHERE date_posted = '2023-11-06'\r\n"
					+ "    GROUP BY username\r\n"
					+ "    ORDER BY max_items_count DESC\r\n"
					+ "    LIMIT 1\r\n"
					+ ");");
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				  
			       users.add(rs.getString("UserName"));
		     }
			
			request.setAttribute("data",users);
			dispatcher=request.getRequestDispatcher("steptwo.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}

	private void operationTen(HttpServletRequest request, HttpServletResponse response,Connection con) {
		ArrayList<UserPair> users=new ArrayList<>();
		RequestDispatcher dispatcher =null;
		
		try {
			PreparedStatement st=con.prepareStatement("SELECT a.username AS UserA, b.username AS UserB\r\n"
					+ "FROM USER a\r\n"
					+ "JOIN USER b ON a.username < b.username  -- Enforce a specific order\r\n"
					+ "WHERE NOT EXISTS (\r\n"
					+ "    -- Check for any non-excellent reviews by A on B's items\r\n"
					+ "    SELECT 1\r\n"
					+ "    FROM Items ia\r\n"
					+ "    JOIN Reviews ra ON ia.item_id = ra.ItemId\r\n"
					+ "    WHERE ia.username = b.username AND ra.UserName = a.username AND ra.Score <> 'Excellent'\r\n"
					+ ")\r\n"
					+ "AND NOT EXISTS (\r\n"
					+ "    -- Check for any non-excellent reviews by B on A's items\r\n"
					+ "    SELECT 1\r\n"
					+ "    FROM Items ib\r\n"
					+ "    JOIN Reviews rb ON ib.item_id = rb.ItemId\r\n"
					+ "    WHERE ib.username = a.username AND rb.UserName = b.username AND rb.Score <> 'Excellent'\r\n"
					+ ")\r\n"
					+ "AND EXISTS (\r\n"
					+ "    -- Check that there is at least one review from A to B\r\n"
					+ "    SELECT 1\r\n"
					+ "    FROM Items ia\r\n"
					+ "    JOIN Reviews ra ON ia.item_id = ra.ItemId\r\n"
					+ "    WHERE ia.username = b.username AND ra.UserName = a.username\r\n"
					+ ")\r\n"
					+ "AND EXISTS (\r\n"
					+ "    -- Check that there is at least one review from B to A\r\n"
					+ "    SELECT 1\r\n"
					+ "    FROM Items ib\r\n"
					+ "    JOIN Reviews rb ON ib.item_id = rb.ItemId\r\n"
					+ "    WHERE ib.username = a.username AND rb.UserName = b.username\r\n"
					+ ");\r\n"
					+ "");
			ResultSet rs=st.executeQuery();
			UserPair up;
			while(rs.next()) {
				  up=new UserPair(rs.getString("UserA"),rs.getString("UserB"));
			       users.add(up);
		     }
			
			request.setAttribute("data",users);
			dispatcher=request.getRequestDispatcher("stepten.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}

}
