package com.comp440.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		session.setAttribute("user_name", username);
		RequestDispatcher dispatcher =null;
		Connection con=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");
			PreparedStatement st=con.prepareStatement("select * from user where username=? and password=?");
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs=st.executeQuery();
			if(rs.next())
			{
				session.setAttribute("name",rs.getString("username"));
				request.setAttribute("status","success");
				dispatcher=request.getRequestDispatcher("index.jsp");
			}
			else
			{
				request.setAttribute("status","failed");
				dispatcher=request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
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

}
