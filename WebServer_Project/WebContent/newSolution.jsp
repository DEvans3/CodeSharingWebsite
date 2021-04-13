<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="csw.Question,csw.SessionTracker"%>
<% if (!SessionTracker.checkSession(session)) { %><jsp:forward page="login.jsp" /><% } %>

<%
Question question = Question.queryQuestion(request);
Boolean validQuestion = (question != null);
String errorMessage = (String)request.getAttribute("errorMessage");
Boolean hasError = (errorMessage != null);

String body = "";
String code = "";
if (hasError) {
	body = request.getParameter("body");
	code = request.getParameter("code");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post a Solution</title>
<link href="Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="navigationBar.jsp"/>
<div class="content-container">
	<div>
		<div class="question-title-container">
			<%= question.title %>
		</div>
		<div class="question-body">
			<%= question.body %>
		</div>
	</div>
	
	<hr>
	
	<div style="padding: 12px;">
	
	<% if (hasError) { %>
		<div class="error-message">
			<%=request.getAttribute("errorMessage") %>
		</div>
	<% } %>
	
		<div class="answer-title">Write a Solution</div>
		<form id="answerForm" action="NewSolution" method="POST">
			<textarea class="answer-input" placeholder="Write Explanation Here" name="body" form="answerForm"><%= body %></textarea>
			<textarea class="code-input" placeholder="Enter Code Here" name="code" form="answerForm"><%= code %></textarea>
			<input type="submit" class="custom-button" />
			<input type="hidden" value="<%= question.id %>" name="qid"/>
		</form>
	</div>
</div>

</body>
</html>