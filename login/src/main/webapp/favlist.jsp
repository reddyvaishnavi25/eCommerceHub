<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
        <h3>List of all the user</h3>
        <table style="border:1px solid black;border-collapse:collapse;">
            <tr>
                <th style="border:1px solid black;"><b>users</b></th>
            </tr>
            <%
            ArrayList<String> users = (ArrayList<String>) request.getAttribute("data");
            if (users != null && !users.isEmpty()) {
                for (String u : users) {
            %>
            <tr>
                <td><%= u %></td>
                <td><form action="AddToFavoriteServlet" method="post">
                     <input type="hidden" name="person" value="<%= u %>">
                     <button type="submit" name="action" value="addfav">Add to favorite</button>
                     </form>
                </td>
            </tr>
            <%
                }
            }%>
        </table>
        
        
</div>

<div>
<br>
<br>
<h3>List of Favorite Users</h3>
<form action="displaylist" method="post">
<button type="submit" name="action" value="fetch_favs">Get the List of Favorite users</button>
</form>

</div>


</body>
</html>
