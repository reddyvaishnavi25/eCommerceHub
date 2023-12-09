<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.comp440.model.Item"%> 
<%@page import="java.util.ArrayList"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Results</title>
</head>
<body>

    <div class="list-of-items">
        <h3>List of items</h3>
        <table border="1" width="500" align="left">
            <tr bgcolor="#CBC3E3">
                <th><b>Items</b></th>
                <th><b>Add Rate</b></th>
                <th><b>Add Review</b></th>
            </tr>
            <%
            ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("data");
            if (items != null && !items.isEmpty()) {
                for (Item item : items) {
            %>
            <tr>
                <td><%= item.getItemName() %></td>
                <form method="post" action="postreview" >
                <td>
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>"/> 
                <select name="rating" class="rate-value" id="rate-value">
                			<option value="">Select your option</option>
                            <option value="Excellent">Excellent</option>
                            <option value="Good">Good</option>
                            <option value="Fair">Fair</option>
                            <option value="Poor">Poor</option>
                </select>
                </td>
                <td><input type="text" class="review" id="review" name="review" placeholder="Add review"></td>
                <td><input type="submit" value="Post"/></td>
                </form>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td>No items found</td>
            </tr>
            <%
            }
            %>
        </table>
    </div>


</body>
</html>