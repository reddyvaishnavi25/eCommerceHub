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
<div class="step-nine">
        <h3> users such that each item they posted so far never received any "poor" reviews</h3>
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