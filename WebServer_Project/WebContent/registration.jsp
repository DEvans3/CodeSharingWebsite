<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	Boolean errorState = (request.getAttribute("errorState") != null);
	String username = "";
	String email = "";
	if (errorState) {
		username = request.getParameter("user");
		email = request.getParameter("email");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link href="Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="navigationBar.jsp"/>
<div class="content-container">
<div class="question-title-container">
	Register
</div>
<form action="NewProfile" method="POST">
  <div class="container">
    <p>Please fill in this form to create an account.</p>
    <hr>
    
    <% 
    	if (errorState) {
    %>
    	<div class="error-message">
    		<%= request.getAttribute("errorMessage") %>
    	</div>
    <%
    	}
    %>

    <label for="email"><b>Email</b></label>
    <div style="position: relative;" id="emailBox">
    	<div class="input-error-message"></div>
    	<input type="text" placeholder="Enter Email" name="email" required value="<%=email %>"/>
    </div>
    
    <label for="username"><b>Username</b></label>
    <div style="position: relative;" id="unameBox">
    	<div class="input-error-message"></div>
    	<input type="text" placeholder="Enter Username" name="user" required value="<%=username %>"/>
	</div>

    <label for="psw"><b>Password</b></label>
    <div style="position: relative;" id="passwordBox">
    	<div class="input-error-message"></div>
    	<input type="password" placeholder="Enter Password" name="psw" required id="passwordBox"/>
	</div>
	
    <label for="psw-repeat"><b>Confirm Password</b></label>
    <div style="position: relative;" id="passwordRepeatBox">
    	<div class="input-error-message"></div>
    	<input type="password" placeholder="Confirm Password" name="psw-repeat" required id="passwordRepeatBox"/>
   	</div>
    <hr>

    <input type="submit" class="custom-button registerbtn" style="width:100%" value="register" id="submitButton"/>
  </div>

  <div class="container signin">
    <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
  </div>
</form>

</div>

<script type="text/javascript" src="registration.js"></script>
</body>
</html>