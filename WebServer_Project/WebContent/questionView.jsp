<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="csw.Question,csw.SessionTracker,csw.Answer,java.util.ArrayList"%>
<%
	Question question = Question.queryQuestion(request);
	Boolean validQuestion = (question != null);
	Boolean validSession = SessionTracker.checkSession(session);
	
	ArrayList<Answer> answers = validQuestion ? question.getAnswers() : null;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title><%=validQuestion ? question.title : "Question not found" %></title>
	<link href="Style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="copyMessage.js"></script>
</head>
<body>

<jsp:include page="navigationBar.jsp"/>

<div class="content-container">

	<% if (validQuestion) { %>

	<div>
		<div class="question-title-container">
			<%= question.title %>
		</div>
		<div class="question-body">
			<%= question.body %>
		</div>
		<div class="question-footer">
		<% if (validSession) { %>
			<a href="newSolution.jsp?qid=<%= question.id %>"><button class="custom-button">Post a Solution</button></a>
		<% } else { %>
			<a href="login.jsp"><button class="custom-button">Log in to post a solution</button></a>
		<% } %>
		</div>
	</div>
	
	
	<% for (int i = 0; i < answers.size(); i++) { %>
	<% Answer answer = answers.get(i); %>
	
	<hr>
	
	<div>
		<div class="answer-title">Answer by <%=answer.author %></div>
		<div class="answer">
			<%=answer.body %>
			<br><br>
			<div class="code">
			<pre><%=answer.code %></pre>
				<div class="copy-code-button"><a href="javascript:;" onclick="copyMessage(this);">Copy</a></div>
			</div>
		</div>
		<div style="border-top: 2px solid #bbbbbb; margin-left: 12px; margin-right: 12px; padding: 12px; text-align: right;">
			<form action="RateAnswer" method="POST">
			<input type="hidden" name="qid" value="<%= question.id %>" />
			<input type="hidden" name="aid" value="<%= answer.id %>" />
			<input type="submit" name="rating" <% if (!validSession) { %>disabled<% } %> value="Like" />
			<%=answer.rating %>
			<input type="submit" name="rating" <% if (!validSession) { %>disabled<% } %> value="Dislike" />
			</form>
		</div>
	</div>
	
	<% } %> <!-- End of the answer loop -->
	
	<% } %> <!-- End of the question conditional -->
	
</div>


</body>
</html>