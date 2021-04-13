<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="csw.QuestionList,java.util.ArrayList,csw.QuestionPreview"
%>

<%
ArrayList<QuestionPreview> questions = QuestionList.getQuestionsFromDatabase();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Questions</title>
	<link href="Style.css" rel="stylesheet" type="text/css">
</head>

<body>
<jsp:include page="navigationBar.jsp"/>
<div class="content-container">
	<div class="title-container">
		Recent Questions
	</div>
	
	<table>
		<thead>
			<tr>
				<th>Author</th>
				<th>Question</th>
				<th>Answers</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 0; i < questions.size(); i++) {
				QuestionPreview question = questions.get(i); %>
				
				<tr>
					<td><%=question.author %></td>
					<td><a href="questionView.jsp?qid=<%=question.id%>"><%=question.title %></a></td>
					<td><%=question.answerCount %></td>
				</tr>
			
			<% } %>
		</tbody>
	</table>
	<div style="height: 10px;"></div>
</div>
</body>

</html>