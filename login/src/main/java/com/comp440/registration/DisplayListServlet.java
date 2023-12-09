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
import javax.servlet.http.HttpSession;

import com.comp440.model.ItemCategory;

@WebServlet("/displaylist")
public class DisplayListServlet extends HttpServlet {
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
			case "fetch_favs":fetch_favs(request,response,con);
			break;
			case "fetch_users":	fetch_users(request,response,con);
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


	private void fetch_users(HttpServletRequest request, HttpServletResponse response, Connection con) {
		RequestDispatcher dispatcher =null;
		ArrayList<String> listOfUsers=new ArrayList<>();
		try {
			PreparedStatement st=con.prepareStatement("SELECT username from user");
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				listOfUsers.add(rs.getString("username"));
		     }
			
			request.setAttribute("data",listOfUsers);
			dispatcher=request.getRequestDispatcher("favlist.jsp");
			dispatcher.forward(request, response);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	private void fetch_favs(HttpServletRequest request, HttpServletResponse response, Connection con) {
		RequestDispatcher dispatcher =null;
		ArrayList<String> listOfUsers=new ArrayList<>();
		HttpSession session=request.getSession();
		String username = (String) session.getAttribute("user_name");
		try {
			PreparedStatement st=con.prepareStatement("SELECT favorite_username from favorite_users where username=?");
			st.setString(1, username);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				listOfUsers.add(rs.getString("favorite_username"));
		     }
			
			request.setAttribute("data",listOfUsers);
			dispatcher=request.getRequestDispatcher("listoffavUsers.jsp");
			dispatcher.forward(request, response);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
