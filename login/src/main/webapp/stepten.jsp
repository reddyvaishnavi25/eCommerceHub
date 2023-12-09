<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.comp440.model.UserPair"%> 
    <%@page import="java.util.ArrayList"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="step-ten">
        <h3>List a user pair (A, B) such that they always gave each other "excellent" reviews for every single item they posted.  </h3>
        <table style="border:1px solid black;border-collapse:collapse;">
            <tr>
                <th style="border:1px solid black;"><b>userA</b></th>
                <th style="border:1px solid black;"><b>userB</b></th>
            </tr>
            <%
            ArrayList<UserPair> users = (ArrayList<UserPair>) request.getAttribute("data");
            if (users != null && !users.isEmpty()) {
                for (UserPair u : users) {
            %>
            <tr>
                <td style="border:1px solid black;"><%= u.getUserA() %></td>
                <td style="border:1px solid black;"><%= u.getUserB() %></td>
            </tr>
            <%
                }
            }%>
        </table>
        
        
</div>


</body>
</html>