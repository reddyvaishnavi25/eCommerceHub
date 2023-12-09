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
  <h3>List of all the items</h3>
        <table style="border:1px solid black;border-collapse:collapse;">
            <tr>
                <th style="border:1px solid black;"><b>users</b></th>
            </tr>
            <%
            ArrayList<String> items = (ArrayList<String>) request.getAttribute("data");
            if (items != null && !items.isEmpty()) {
                for (String i : items) {
            %>
            <tr>
                <td><%= i %></td>
                <td><form action="addToFavListItems" method="post">
                     <input type="hidden" name="item" value="<%= i %>">
                     <button type="submit" name="action" value="addfavItems">Add to favorite</button>
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
<h3>List of Favorite Items</h3>
<form action="dispayAllItems" method="post">
<button type="submit" name="action" value="fetch-fav-items">Get the List of Favorite users</button>
</form>

</div>
</body>
</html>