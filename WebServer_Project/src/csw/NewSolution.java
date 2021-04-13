package csw;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewSolution
 */
@WebServlet("/NewSolution")
public class NewSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NewSolution() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement st = null;
		
		if (!SessionTracker.checkSession(request.getSession())) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		String code = request.getParameter("code");
		String body = request.getParameter("body");
		int questionId = Integer.parseInt(request.getParameter("qid"));
		int authorId = SessionTracker.getId(request.getSession());
		code = code.replace("\t", "    ");
		
		if (code.length() < 20) {
			processError("Your code must be longer than 20 characters.", request, response);
			return;
		}
		if (code.length() > 1000) {
			processError("Your code must be shorter than 1000 characters.", request, response);
			return;
		}
		if (body.length() < 20) {
			processError("Your explanation must be longer than 20 characters.", request, response);
			return;
		}
		if (body.length() > 1000) {
			processError("Your explanation must be shorter than 1000 characters.", request, response);
			return;
		}
		
		try {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			} catch(Exception e) {}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			st = con.prepareStatement("INSERT INTO answers (question, body, codeBody, author) VALUES (?, ?, ?, ?)");
			
			st.setInt(1, questionId);
			st.setString(2, body);
			st.setString(3, code);
			st.setInt(4, authorId);
			
			st.executeUpdate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("questionView.jsp");
			dispatcher.forward(request, response);
		}
		catch(SQLException asdf)
		{
			System.out.println(asdf);
		}
		finally {
			try {st.close();} catch(Exception e) {}
			try {con.close();} catch(Exception e) {}
		}
	}
	
	protected static void processError(String message, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("newSolution.jsp");
		request.setAttribute("errorState", "true");
		request.setAttribute("errorMessage", message);
		dispatcher.forward(request, response);
	}

}
