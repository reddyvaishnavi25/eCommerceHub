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
<div class="step-five">

<form method="post" action="querying">
<input type="text" name="category1" id="category1" placeholder="Enter Category 1 name" />
<input type="text" name="category2" id="category2" placeholder="Enter Category 2 name" />						

<button type="submit" name="action" value="button5">Find</button>						
</form>



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
                <td style="border:1px solid black;"><%= u %></td>
            </tr>
            <%
                }
            }%>
        </table>
        
        
</div>
</body>
</html>