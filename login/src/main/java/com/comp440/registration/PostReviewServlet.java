package com.comp440.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PostReviewServlet
 */
@WebServlet("/postreview")
public class PostReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rating=request.getParameter("rating");
		String review=request.getParameter("review");
		String item_id =request.getParameter("itemId");
		
		int i_id=Integer.parseInt(item_id);
		HttpSession session=request.getSession();
		String username = (String) session.getAttribute("user_name");
	    Connection con=null;
	    RequestDispatcher dispatcher=null;
		try {
			LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formattedDate = currentDate.format(formatter);
	        
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");
			CallableStatement cs = con.prepareCall("{CALL InsertReview(?, ?, ?, ?, ?)}");
		    cs.setString(1, username);
		    cs.setInt(2, i_id);
		    cs.setString(3, rating);
		    cs.setString(4, review);
		    cs.setString(5, formattedDate);
		   
		    
		    boolean hasResults =cs.execute();
		  
		    if (hasResults) {
	            ResultSet rs = cs.getResultSet();
	           if (rs.next()) {
	                String outputMessage = rs.getString(1); 
		    
		       if ("Review successfully added.".equals(outputMessage)) {
		    	request.setAttribute("status", "success");
		    	dispatcher = request.getRequestDispatcher("listitems.jsp"); // or any other error page
		        dispatcher.forward(request, response);
		       } else {
		        request.setAttribute("status", outputMessage);
		        dispatcher = request.getRequestDispatcher("listitems.jsp"); // or any other error page
		        dispatcher.forward(request, response);
		       }
	           }
	            rs.close();
		    }
		    
			
		
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 dispatcher = request.getRequestDispatcher("listitems.jsp");
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

}
