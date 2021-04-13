<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import="csw.SessionTracker"%>
<%
	Boolean validSession = SessionTracker.checkSession(session);
	String username = (String)session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<div class="navigation-bar">
	<ul>
	  	<li><a href="questionList.jsp" class="nav-bar-item">Home</a></li>
	  	<% if (!validSession) {%>
	  		<li style="float:right"><a href="login.jsp" class="nav-bar-item">Login/Signup</a></li>
	  <% } else {%>
	  	<li><a href="newQuestion.jsp" class="nav-bar-item">Post a question</a></li>
	  	<li style="float:right"> <a href="LogOff" class="nav-bar-item">Sign Out</a></li>
	  	<li style="float:right" class="nav-bar-item"> You are signed in as: <%=username%></li>
	  <% } %>
	</ul>
</div>
</html>