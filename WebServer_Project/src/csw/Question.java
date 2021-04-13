package csw;

import csw.Answer;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class Question {
	public String title;
	public String body;
	public String author;
	public int rating;
	public int id;
	public int authorId;
	
	public Question(String title, String body, String author, int rating, int id, int authorId) {
		this.title = title;
		this.body = body;
		this.author = author;
		this.rating = rating;
		this.id = id;
		this.authorId = authorId;
	}
	
	public ArrayList<Answer> getAnswers() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList<Answer> answerList = new ArrayList<Answer>();
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			} catch(Exception e) {}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			st = con.prepareStatement(
				"SELECT answers.id, answers.body, answers.codeBody, answers.author, answers.rating, users.uname FROM answers, users "
				+ "WHERE answers.question = ? AND users.id = answers.author;"
			);
			
			st.setInt(1, this.id);
			
			rs = st.executeQuery();
			while (rs.next()) {
				String body = rs.getString("body");
				String code = rs.getString("codeBody");
				String author = rs.getString("uname");
				int rating = rs.getInt("rating");
				int id = rs.getInt("id");
				int authorId = rs.getInt("author");
				
				body = body.replace("\n", "<br/>");
				
				answerList.add(new Answer(body, code, author, rating, id, authorId));
			}
		}
		catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}
		catch (NumberFormatException exception) {
			
		}
		finally {
			try {rs.close();} catch (Exception e) {};
			try {st.close();} catch (Exception e) {};
			try {con.close();} catch (Exception e) {};
		}
		
		return answerList;
	}
	
	
	public static Question queryQuestion(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			int questionId = Integer.parseInt(request.getParameter("qid"));
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			} catch(Exception e) {}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			st = con.prepareStatement(
				"SELECT questions.id, title, body, rating, author, users.uname FROM questions, users WHERE "
				+ "questions.id = ? AND users.id = questions.author;"
			);
			
			st.setInt(1, questionId);
			
			rs = st.executeQuery();
			if (rs.next()) {
				String title = rs.getString("title");
				String body = rs.getString("body");
				String author = rs.getString("uname");
				int rating = rs.getInt("rating");
				int authorId = rs.getInt("author");
				
				body = body.replace("\n", "<br/>");
				
				return new Question(title, body, author, rating, questionId, authorId);
			}
		}
		catch (SQLException exception) {
			
		}
		catch (NumberFormatException exception) {
			
		}
		finally {
			try {rs.close();} catch (Exception e) {};
			try {st.close();} catch (Exception e) {};
			try {con.close();} catch (Exception e) {};
		}
		
		return null;
	}
}
