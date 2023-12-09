package com.comp440.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dispayAllItems
 */
@WebServlet("/dispayAllItems")
public class dispayAllItems extends HttpServlet {
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
			case "fetch-items":fetch_items(request,response,con);
			break;
			case "fetch-fav-items":	fetch_fav_items(request,response,con);
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

	private void fetch_fav_items(HttpServletRequest request, HttpServletResponse response, Connection con) {
		
		
	}

	private void fetch_items(HttpServletRequest request, HttpServletResponse response, Connection con) {
		RequestDispatcher dispatcher =null;
		ArrayList<String> listOfItems=new ArrayList<>();
		
		try {
			PreparedStatement st=con.prepareStatement("SELECT item_title from items");
			
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				listOfItems.add(rs.getString("item_title"));
		     }
			
			request.setAttribute("data",listOfItems);
			dispatcher=request.getRequestDispatcher("listOfItems.jsp");
			dispatcher.forward(request, response);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
