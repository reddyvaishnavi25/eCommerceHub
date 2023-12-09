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
<table style="border:1px solid black;border-collapse:collapse;">
            <tr>
                <th style="border:1px solid black;"><b>users</b></th>
            </tr>
            <%
            ArrayList<String> favItems = (ArrayList<String>) request.getAttribute("data");
            if (favItems != null && favItems.isEmpty()) {
                for (String fi : favItems) {
            %>
            <tr>
                <td><%= fi %></td>
                <td><form action="addToFavListItems" method="post">
                     <input type="hidden" name="fav-item-delete" value="<%= fi %>">
                     <button type="submit" name="action" value="delfavItem">Delete</button>
                     </form>
                </td>
                <td><form action="addToFavListItems" method="post">
                     <input type="hidden" name="fav-item-update" value="<%= fi %>">
                     <button type="submit" name="action" value="updatefavItem">Update</button>
                     </form>
                </td>
            </tr>
            <%
                }
            }%>
        </table>
</body>
</html>