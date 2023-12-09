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

<div class="step-six">

<form method="post" action="querying">
<input type="text" name="user_name" id="user_name" placeholder="Enter user name" />				

<button type="submit" name="action" value="button6">Enter</button>						
</form>



        <table style="border:1px solid black;border-collapse:collapse;">
            <tr>
                <th style="border:1px solid black;"><b>items</b></th>
            </tr>
            <%
            ArrayList<String> items = (ArrayList<String>) request.getAttribute("data");
            if (items != null && !items.isEmpty()) {
                for (String i : items) {
            %>
            <tr>
                <td style="border:1px solid black;"><%= i %></td>
            </tr>
            <%
                }
            }%>
        </table>
        
        
</div>


</body>
</html>