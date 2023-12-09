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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/additem")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session=request.getSession();
		String username = (String) session.getAttribute("user_name");
		
		String title=request.getParameter("itemname");
		String description=request.getParameter("description");
		String price=request.getParameter("price");
		String p=request.getParameter("category");
		String[] category=p.split(",");
		RequestDispatcher dispatcher=null;
		Connection con=null;
		PrintWriter out=response.getWriter();
		int count=0;
		
		try {
			LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formattedDate = currentDate.format(formatter);
	        
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");
			PreparedStatement st=con.prepareStatement("select * from items where username=? and date_posted=?");
			st.setString(1, username);
			st.setString(2, formattedDate);
			ResultSet r=st.executeQuery();
			while(r.next())
			{
				count++;
			}
			if(count<3) {
			st=con.prepareStatement("insert into items(item_title,item_description,date_posted,price,username) values (?,?,?,?,?)");
			st.setString(1, title);
			st.setString(2, description);
			st.setString(3, formattedDate);//date
			st.setString(4, price);
			st.setString(5,username);
			int rowsCount= st.executeUpdate();
			
			st = con.prepareStatement("SELECT item_id FROM items WHERE item_title = ?");
		    st.setString(1, title);
			ResultSet rs = st.executeQuery();
			int itemId = -1;
			if (rs.next()) {
				itemId = rs.getInt("item_id");
			}
			
			
			for(int i=0;i<category.length;i++)
			{
				
				//System.out.println(category[i]);
				//insert category
				st=con.prepareStatement("insert ignore into category (category_name) values (?)");
				st.setString(1, category[i]);
				st.executeUpdate();
				
				
				//get category_id
				st = con.prepareStatement("SELECT category_id FROM category WHERE category_name = ?");
			    st.setString(1, category[i]);
				ResultSet resultSet = st.executeQuery();
				int categoryId = -1;
				if (resultSet.next()) {
				   categoryId = resultSet.getInt("category_id");
				}
				 
			    // Insert into has_category table
				 if (categoryId != -1) {
				        st = con.prepareStatement("INSERT INTO has_category (item_id, category_id) VALUES (?, ?)");
				        st.setInt(1, itemId); // Assuming you have item_id obtained from items table
				        st.setInt(2, categoryId);
				        st.executeUpdate();
				}
				
			}
			}
			else
			{
				request.setAttribute("status","failed");
			}
			
			
			dispatcher=request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			
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

}
