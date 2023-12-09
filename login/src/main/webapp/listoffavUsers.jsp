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
<table style="border:1px solid black;border-collapse:collapse;">
            <tr>
                <th style="border:1px solid black;"><b>users</b></th>
            </tr>
            <%
            ArrayList<String> user = (ArrayList<String>) request.getAttribute("data");
            if (user != null && !user.isEmpty()) {
                for (String u : user) {
            %>
            <tr>
                <td><%= u %></td>
                <td><form action="AddToFavoriteServlet" method="post">
                     <input type="hidden" name="fav-person-delete" value="<%= u %>">
                     <button type="submit" name="action" value="delfav">Delete</button>
                     </form>
                </td>
                <td><form action="AddToFavoriteServlet" method="post">
                     <input type="hidden" name="fav-person-update" value="<%= u %>">
                     <button type="submit" name="action" value="updatefav">Update</button>
                     </form>
                </td>
            </tr>
            <%
                }
            }%>
        </table>
</div>
</body>
</html>