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
<div class="step-eight">

<form method="post" action="querying">
<select name="userX" id="userX">
            <option value="">Select your option</option>
            <option value="Alex">Alex</option>
            <option value="Varsha">Varsha</option>
            <option value="John">John</option>
            <option value="Vaishnavi">Vaishnavi</option>
            <option value="Flor">Flor</option>
            <option value="Emily">Emily</option>
            <option value="priya">priya</option>
            <option value="isha">isha</option>
        </select>
<select name="userY" id="userY">
            <option value="">Select your option</option>
            <option value="Alex">Alex</option>
            <option value="Varsha">Varsha</option>
            <option value="John">John</option>
            <option value="Vaishnavi">Vaishnavi</option>
            <option value="Flor">Flor</option>
            <option value="Emily">Emily</option>
            <option value="isha">isha</option>
        </select>        

<button type="submit" name="action" value="button8">Find</button>						
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