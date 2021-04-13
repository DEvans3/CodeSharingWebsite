<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="csw.SessionTracker"%>
<% if (!SessionTracker.checkSession(session)) { %><jsp:forward page="login.jsp" /><% } %>
<%
String errorMessage = (String)request.getAttribute("errorMessage");
Boolean hasError = (errorMessage != null);

String title = "";
String body = "";
if (hasError) {
	title = request.getParameter("sub");
	body = request.getParameter("question");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post a New Question</title>
<link href="Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="navigationBar.jsp"/>
<div class="content-container">
<div class="question-title-container">
	Post a New Question
</div>

<form action="NewQuestion" method="post" id="newQuestionForm">
  <div class="container">
  
  <% if (hasError) { %>
  	<div class="error-message">
		<%=request.getAttribute("errorMessage") %>
	</div>
  <% } %>
  
    <label for="sub"><b>Subject</b></label>
    <input type="text" placeholder="Enter Subject" name="sub" required value="<%= title %>" />
    
    <label for="ques"><b>Question</b></label>
      <div class = "container">
	    <textarea id="question" name="question" placeholder="Enter Question" form="newQuestionForm"><%= body %></textarea>
  	  </div>
    <button type="submit" class="custom-button" >Submit</button>
  </div>
      
</form>
</div>

</body>
</html>