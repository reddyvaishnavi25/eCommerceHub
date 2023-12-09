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

import com.comp440.model.Item;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Item i;
		
		HttpSession session=request.getSession();
		String username = (String) session.getAttribute("user_name");
		String searchedItem=request.getParameter("search_itemname");
		session.setAttribute("user_name",username);
		RequestDispatcher dispatcher =null;
		ArrayList<Item> arrlist=new ArrayList<>();
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/comp440?useSSL=false","root","root");

			PreparedStatement st=con.prepareStatement("select category_id from category where category_name=?");
			st.setString(1,searchedItem);
			ResultSet rs = st.executeQuery();
			int c_id = -1;
			if (rs.next()) {
				c_id = rs.getInt("category_id");
			}
			if (c_id != -1) {
				st = con.prepareStatement("SELECT item_id,item_title FROM items WHERE item_id IN (SELECT item_id FROM has_category WHERE category_id=?)");
		        st.setInt(1, c_id);
		        rs = st.executeQuery();
		   }
			while(rs.next()) {
				   i=new Item(rs.getInt("item_id"),rs.getString("item_title"));
				   arrlist.add(i);
				 	   
		     }
			request.setAttribute("data",arrlist);
			dispatcher=request.getRequestDispatcher("listitems.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				//rs.close();
				//statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
