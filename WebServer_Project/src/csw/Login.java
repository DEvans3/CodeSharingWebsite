package csw;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("psw");
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			}
			catch(Exception asdf) {
				System.out.println(asdf);
			}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			st = con.prepareStatement("SELECT id FROM users WHERE uname = ? AND BINARY passwd = ?");
			
			st.setString(1, username);
			st.setString(2, password);
			
			rs = st.executeQuery();
			if (!rs.next()) {
				processError("Username or password is incorrect.", request, response);
			}
			else {
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("password", password);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("questionList.jsp");
				dispatcher.forward(request,  response);
			}
			
			
		}
		catch(SQLException exception) {
			processError("An error occurred.", request, response);
		}
		finally {
			try {rs.close();} catch (SQLException e) {};
			try {st.close();} catch (SQLException e) {};
			try {con.close();} catch (SQLException e) {};
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static void processError(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		request.setAttribute("errorState", "true");
		request.setAttribute("errorMessage", message);
		dispatcher.forward(request, response);
	}

}
