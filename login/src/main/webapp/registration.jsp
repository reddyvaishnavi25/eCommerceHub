<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form by Colorlib</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!--
<link rel="stylesheet" href="css/style.css">-->
</head>
<style>
    body {
        background-color: #CBC3E3;
        color: white; /* Set text color to white for better visibility on purple background */
    }

    .signup {
        margin: 50px auto;
        max-width: 400px;
        background: rgba(255, 255, 255, 0.2); /* Semi-transparent white background */
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* Box shadow for a subtle lift effect */
    }

    .form-group {
        margin-bottom: 20px;
    }

    .form-group label {
        font-size: 18px;
    }

    .form-group input {
        width: 80%;
        padding: 5px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .form-submit {
        background-color: #000000; /* Orange submit button color */
        color: white;
        border: none;
        padding: 12px 40px !important;
        font-size: 18px;
        border-radius: 5px;
        cursor: pointer;
        width: 350px !important;
        transition: background-color 0.3s ease;
    }

    .form-submit:hover {
        background-color: #000000; /* Darker orange color on hover */
    }

    .signup-image-link {
        display: block;
        text-align: center;
        margin-top: 20px;
        color: #000000;
        text-decoration: underline;
        font-weight: bold;
    }
</style>

<body>
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
	<div class="main">
	

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title" style="color:black">Sign up</h2>
					
						<form method="post" action="register" class="register-form" id="register-form" onsubmit="return checkPassword()">
							 
							 
                             <c:if test="${not empty requestScope.statusMessage}">
   									 <div style="color: ${requestScope.statusMessageColor};">${requestScope.statusMessage}</div>
   									
							</c:if>
					
							
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" />
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass" placeholder="Password" />
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="password" name="re_pass" id="re_pass"
									placeholder="Repeat your password" />
							</div>
							
						   <p id="message"></p>
						   
							<div class="form-group">
								<label for="fname"><i class="zmdi zmdi-account material-icons-name"></i></label>
								<input type="text" name="firstname" id="firstname"
									placeholder="First Name" />
							</div>
							<div class="form-group">
								<label for="lname"><i class="zmdi zmdi-account material-icons-name"></i></label>
								<input type="text" name="lastname" id="lastname"
									placeholder="Last Name" />
							</div>
							<!--<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label>
							</div>
							-->
							
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
								
								<a href="login.jsp" class="signup-image-link" text-align="center">I am already member</a>
							</div>
							
							<p id="errormsg"></p>
						</form>
						
					</div>
					
		           
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	
  <script>
  
  
  function checkPassword()
    {
		let password=document.getElementById("pass").value;
		let c_password=document.getElementById("re_pass").value;
		console.log(password);
		let message=document.getElementById("message");
		if(password.length!==0)
		{
			if(password===c_password)
			{
				message.textContent="password matched"
				message.style.color= "green";
				return true;
			}
			else{
				message.textContent="password didnt match"
				message.style.color= "red";
				return false;
			}
		}
		return false;
	}

</script>
	
 
</body>

</html>