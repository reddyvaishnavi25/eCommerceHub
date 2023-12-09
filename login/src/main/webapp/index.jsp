<%
String userName = (String) session.getAttribute("name");
if(session.getAttribute("name")==null){
response.sendRedirect("login.jsp");
}
%>
<%@page import="java.util.ArrayList"%> 

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Landing Page</title>
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet" />
<style>
    #header {
        background-color: #CBC3E3;
        color: white;
        padding: 35px;
        text-align: right;
    }
    .addItemForm {
            display: none;
     }
     #add-button{
     background: #CBC3E3;
     color:white;
     padding:8px;
     }
      .popup{
        flex-direction: column;
        background:#CBC3E3;
        border: 2px solid black;
        width:30%;
        height:60%;
        display:none;
        justify-content:center;
        align-items:center;
        text-align:center;
        position:absolute;
        
    }
    .popup-group {
        display: block;
        margin-bottom: 20px;
    }
    .popup-group input {
        width: 80%;
        padding: 10px;
        margin: 0 auto;
        display: block;
    }
    .initialize{
   			position: absolute;
   			top: 100px;
		    right: 100px;
		    width: 300px; /* Set a specific width if necessary */
		    height: 300px; /* Set a specific height if necessary */
		    background-color: light gray;
    }
    .searching{
        	position: absolute;
   			top: 100px;
		    right: 100px;
		    width: 800px; /* Set a specific width if necessary */
		    height: 300px; /* Set a specific height if necessary */
		    background-color: light gray;
    }
    #to-add{
    		position: absolute;
   			top: 100px;
		    right: 100px;
		    width: 1300px; /* Set a specific width if necessary */
		    height: 300px; /* Set a specific height if necessary */
		    background-color: light gray;
    }
.phase3 {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  position: fixed;
  top: 300px;
  left: 100px;
  padding: 20px;
  border: 2px solid black;
  width: 30%;
}

.phase3 form button {
  display: block;
  margin-bottom: 10px;
}

.favorite-list{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  position: fixed;
  top: 300px;
  left: 700px;
  padding: 20px;
  
}

    </style>
</head>
<body id="page-top">
     <div id="header">
      Welcome, <%= userName %>
     </div>
     
     
     <div id="to-add">
     <h3>click the button to add item</h3>
     <a href="#" class="add-button" id="add-button">Add item </a>
     </div>
     
      <div class="popup">
     <form method="post" action="additem" class="add-form" id="add-form" onsubmit="return checkAdd();">
      
     
        <div class="popup-group">
			<input type="text" name="itemname" id="itemname" placeholder="Item Name" />
	    </div>
	    <div class="popup-group">
	        <input type="text" name="description" id="description" placeholder="describe" />
	    </div>
	    <div class="popup-group">
			<input type="text" name="price" id="price" placeholder="Price" />
	    </div>
	    <div class="popup-group">
			<input type="text" name="category" id="category" placeholder="Category" />
	    </div>
	    <div class="popup-group">
			<input type="submit" name="enteritem" id="enter-item" class="enter-item" value="Add" />
	    </div>
	 
	 </form> 
	  </div>
	 <p id="msg"></p>
	 
	 
	 <div class="searching">
	  <form method="post" action="search" class="search-form" id="search-form" >
        <h3>Search the item by category name</h3>
        <div class="s-group">
			<input type="text" name="search_itemname" id="search_itemname" placeholder="Enter the Category Name" />
			<input type="submit" style="background-color:#CBC3E3" name="entersearch" id="entersearch" class="entersearch" value="Search" />
	    </div>
	 </form>
	 </div>
	 
	 
	 <div class="initialize">
	 <h3>Click to initialize database</h3>
	 <form action="initializedb" method="post">
     <input type="submit" value="Initialize Database" style=" padding: 10px 20px; background-color:#CBC3E3">
     </form>
	 </div>
	

<div class="phase3">
<h3>List of operations</h3>
<form method="post" action="querying">
  <button type="submit" name="action" value="button1">List the most expensive items in each category. </button>
  <button type="submit" name="action" value="button2">List users who posted most number of items on a specific date</button>
  <button type="submit" name="action" value="button3">Display all the users who never posted a "poor" review. </button>
  <button type="submit" name="action" value="button4">Display all the users who posted some reviews, but each of them is "poor". </button>
  
  <a href="stepfive.jsp">Display users who posted items belong to category one and two on the same day</a>
  <br>
  <a href="stepsix.jsp">List all the items posted by user X, such that all the comments are "Excellent" or "good"</a>
  <button type="submit" name="action" value="button7">Display all the users who never posted any "excellent" items</button>
  <a href="stepeigth.jsp">List the other users who are favorited by both users X, and Y.</a>
  <button type="submit" name="action" value="button9">Display those users such that each item they posted so far never received any "poor" reviews</button>
  <button type="submit" name="action" value="button10">List a user pair (A, B) such that they always gave each other "excellent" reviews for every single 
item they posted. </button>
</form>
</div>

<div class="favorite-list">
<form method="post" action="displaylist" class="displaylist" id="displaylist" >
<button type="submit" name="action" value="fetch_users">Get list of user</button>
</form>
</div>


     
<script>
     
      
  


document.getElementById("add-button").addEventListener("click",function(){
document.querySelector(".popup").style.display="flex";
})

document.getElementById("enter-item").addEventListener("click",function(){
document.querySelector(".popup").style.display="none";
})


function checkAdd() {
  
    
    var status = '<%= (request.getAttribute("status") !=null) ? request.getAttribute("status") : "" %>';
    var statusElement = document.getElementById("msg");
    console.log(status);
    if (status === "failed") {
        statusElement.textContent = "Cannot insert more than three on a day"; // Set the message for failed login
        statusElement.style.color = "red";
        return false;
    }
}


</script>

</body>
</html>

