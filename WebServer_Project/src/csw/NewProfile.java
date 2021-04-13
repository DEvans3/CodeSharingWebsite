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
 * Servlet implementation class asdf
 */
@WebServlet("/NewProfile")
public class NewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewProfile() {
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
		String username = request.getParameter("user");
		String password = request.getParameter("psw");
		String email = request.getParameter("email");
		Connection con = null;
		PreparedStatement st = null;
		
		if (username.length() < 3 || username.length() > 20) {
			processError("Your username must be at least 3 characters long.", request, response);
			return;
		}
		if (!checkEmail(email)) {
			processError("Please enter a valid email address.", request, response);
			return;
		}
		if (password.length() < 8 || password.length() > 64) {
			processError("Your password must be at least 8 characters long.", request, response);
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
			st = con.prepareStatement("INSERT INTO users (uname, passwd, email) VALUES (?, ?, ?)");
			
			st.setString(1, username);
			st.setString(2, password);
			st.setString(3, email);
			
			st.executeUpdate();
			
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("password", password);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("questionList.jsp");
			dispatcher.forward(request, response);
		}
		catch(SQLException exception) {
			int errorCode = exception.getErrorCode();
			String message = exception.getMessage();
			
			switch(errorCode) {
			// Duplicate Entry
			case 1062:
				String field = message.substring(message.indexOf("' for key '") + 11, message.length() - 1);
				if (field.contentEquals("uname")) {
					processError("The username \"".concat(username).concat("\" is already in use."), request, response);
				}
				if (field.contentEquals("email")) {
					processError("The email address \"".concat(email).concat("\" is already in use."), request, response);
				}
				break;
			default:
				processError(message, request, response);
			}
		}
		finally {
			try {st.close();} catch (SQLException e) {};
			try {con.close();} catch (SQLException e) {};
		}
	}

	
	protected static Boolean checkEmail(String email) {
		int atLocation = email.indexOf('@');
		if (email.length() > 64) return false;
		if (atLocation == -1) return false;
		if (email.indexOf('@', atLocation + 1) != -1) return false;
		if (email.indexOf('.', atLocation + 1) == -1) return false;
		return true;
	}
	
	protected static void processError(String message, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
		request.setAttribute("errorState", "true");
		request.setAttribute("errorMessage", message);
		dispatcher.forward(request, response);
	}
}
