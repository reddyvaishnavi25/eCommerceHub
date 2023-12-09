package com.comp440.registration;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("pass");
		String c_password=request.getParameter("re_pass");
		String firstName=request.getParameter("firstname");
		String lastName=request.getParameter("lastname");
		RequestDispatcher dispatcher=null;
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");
			PreparedStatement st=con.prepareStatement("insert into user(username,password,FirstName,LastName,email) values (?,?,?,?,?)");
			st.setString(1, username);
			st.setString(2, password);
			st.setString(3, firstName);
			st.setString(4, lastName);
			st.setString(5, email);
			int rowsCount= st.executeUpdate();
			dispatcher=request.getRequestDispatcher("registration.jsp");
			if(rowsCount>0)
			{
				request.setAttribute("status", "success"); 	
				
			}
			else
			{
				request.setAttribute("status", "failed"); 	
				request.setAttribute("statusMessage","acc failed");
			}
			dispatcher.forward(request, response);
			
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 request.setAttribute("status", "failed");
	         dispatcher = request.getRequestDispatcher("registration.jsp");
	         dispatcher.forward(request, response);
	         request.setAttribute("statusMessage","acc failed");
			
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
