package csw;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class NewQuestions
 */
@WebServlet("/NewQuestion")
public class NewQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NewQuestion() {
    	super();
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
		Statement st2 = null;
		ResultSet rs = null;
		
		String title = request.getParameter("sub");
		String body = request.getParameter("question");
		int userId = 0;
		
		if (SessionTracker.checkSession(request.getSession())) {
			userId = SessionTracker.getId(request.getSession());
		}
		else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		if (title.length() < 10 || title.length() > 100 )
		{
			processError("Your title must be greater than 10 characters but no more than 100 characters long.", request, response);
			return;
		}
		if (body.length() < 20 || body.length() > 1000)
		{
			processError("Your question must be between 20 and 1000 characters.", request, response);
			return;
		}
		
		try {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			}
			catch(Exception asdf) {
				System.out.println(asdf);
			}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			st = con.prepareStatement("INSERT INTO questions (title, body, author) VALUES (?, ?, ?)");
			
			st.setString(1, title);
			st.setString(2, body);
			st.setInt(3, userId);
			
			st.executeUpdate();
			
			st2 = con.createStatement();
			rs = st2.executeQuery("SELECT LAST_INSERT_ID();");
			
			rs.next();
			int questionId = rs.getInt(1);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("questionView.jsp?qid=".concat(Integer.toString(questionId)));
			dispatcher.forward(request,  response);
		}
		catch (SQLException e) {
			processError("An error occurred.", request, response);
		}
		finally {
			try {st.close();} catch(Exception e) {}
			try {con.close();} catch(Exception e) {}
		}
	}
		
	protected static void processError(String message, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("newQuestion.jsp");
		request.setAttribute("errorState", "true");
		request.setAttribute("errorMessage", message);
		dispatcher.forward(request, response);
	}
		
}
