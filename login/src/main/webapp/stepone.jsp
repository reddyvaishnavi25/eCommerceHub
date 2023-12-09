<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.comp440.model.ItemCategory"%> 
<%@page import="java.util.ArrayList"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="step-one">
        <h3>Costly Item in each Category</h3>
        <table style="border:1px solid black;border-collapse:collapse;">
            <tr>
                <th style="border:1px solid black;"><b>Item</b></th>
                <th style="border:1px solid black;"><b>Category</b></th>
            </tr>
            <%
            ArrayList<ItemCategory> items = (ArrayList<ItemCategory>) request.getAttribute("data");
            if (items != null && !items.isEmpty()) {
                for (ItemCategory item : items) {
            %>
            <tr>
                <td style="border:1px solid black;"><%= item.getItemName() %></td>
                <td style="border:1px solid black;"><%= item.getCategoryName() %></td>
                
            </tr>
            <%
                }
            }%>
        </table>
    </div>
</body>
</html>



