package com.comp440.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addToFavListItems
 */
@WebServlet("/addToFavListItems")
public class addToFavListItems extends HttpServlet {
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
			case "addfavItem":addToFavItem(request,response,con);
			break;
			case "delfavItem":deleteFromFavItem(request,response,con);
			break;
			case "updatefavItem":updateFavItem(request,response,con);
			break;
			}
		  }
			catch(Exception e)
			{
				 e.printStackTrace();
				
		         dispatcher = request.getRequestDispatcher("favlist.jsp");
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
	private void updateFavItem(HttpServletRequest request, HttpServletResponse response, Connection con) {
		HttpSession session=request.getSession();
		String username = (String) session.getAttribute("user_name");
		String fPerson=request.getParameter("fav-person-update");
		RequestDispatcher dispatcher =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");
			PreparedStatement st=con.prepareStatement("INSERT INTO favorite_users VALUES(?,?)");
			st.setString(1, username);
			st.setString(2,fPerson);
			st.executeUpdate();
			dispatcher=request.getRequestDispatcher("listoffavUsers.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void deleteFromFavItem(HttpServletRequest request, HttpServletResponse response, Connection con) {
		HttpSession session=request.getSession();
		String username = (String) session.getAttribute("user_name");
		String fPerson=request.getParameter("fav-person-delete");
		RequestDispatcher dispatcher =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");
			PreparedStatement st=con.prepareStatement("DELETE FROM favorite_users\r\n"
					+ "WHERE username=? and favorite_username=?;");
			st.setString(1, username);
			st.setString(2,fPerson);
			st.executeUpdate();
			dispatcher=request.getRequestDispatcher("listoffavUsers.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void addToFavItem(HttpServletRequest request, HttpServletResponse response, Connection con) {
		HttpSession session=request.getSession();
		String username = (String) session.getAttribute("user_name");
		String fitem=request.getParameter("item");
		RequestDispatcher dispatcher =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");
			PreparedStatement st=con.prepareStatement("INSERT INTO favorite_users VALUES(?,?)");
			st.setString(1, username);
			st.setString(2,fitem);
			st.executeUpdate();
			dispatcher=request.getRequestDispatcher("listOfItems.jsp");
			dispatcher.forward(request, response);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
