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
<link rel="stylesheet" href="css/style.css"> -->
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
 <style>
        body {
            background-color: #CBC3E3;
            color: white;
        }

        .sign-in {
            margin: 50px auto;
            max-width: 400px;
            background: rgba(255, 255, 255, 0.2);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
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
            background-color: #000000;
            color: white;
            border: none;
            padding: 12px 40px !important;
            font-size: 18px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        .form-submit:hover {
            background-color: #000000;
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

		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">

					<div class="signin-form">
						<h2 class="form-title" style="color:black">Sign In</h2>
						<form method="post" action="login" class="register-form" id="login-form" onsubmit="return checkLogin();">
						
							<div class="form-group">
								<label for="username"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="username" id="username"
									placeholder="Enter your username" />
							</div>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password"
									placeholder="Enter your password" />
							</div>
							<!--<div class="form-group">
								<input type="checkbox" name="remember-me" id="remember-me"
									class="agree-term" /> <label for="remember-me"
									class="label-agree-term"><span><span></span></span>Remember
									me</label>
							</div>-->
							<div class="form-group form-button">
			                        <input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
							
						</form>
						<p id="msg"></p>
						 <div class="signin-image">
						  <a href="registration.jsp" class="signup-image-link">Create an account</a>
					     </div>
						   
						   
					</div>
				</div>
			</div>
		</section>

	</div>

	
	<script>
	document.getElementById("signin").addEventListener("submit", function(event) {
    event.preventDefault();
    checkLogin();
    });
	

function checkLogin() {
  
    var username = document.getElementById("username").value;
    var status = '<%= (request.getAttribute("status") !=null) ? request.getAttribute("status") : "" %>';
    var statusElement = document.getElementById("msg");
     console.log("Status received from server: " + status);
    if (status === "failed") {
        statusElement.textContent = "Login failed"; // Set the message for failed login
        statusElement.style.color = "red";
        return false;
    } else {
        statusElement.textContent = "login suceess"; // Clear the status message
        statusElement.style.color = "green";
        return true;
    }
}
</script>
	
   
   
</body>

</html>