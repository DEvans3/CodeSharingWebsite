package csw;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionView
 */
@WebServlet("/QuestionView")
public class QuestionView extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public QuestionView() {
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
		// TODO Auto-generated method stub
		
		try {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			}
			catch(Exception asdf) {
				System.out.println(asdf);
			}
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			PreparedStatement st = con.prepareStatement("SELECT * FROM questions");
			
			st.executeUpdate();
			
			st.close();
			con.close();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("questionView.jsp");
			dispatcher.forward(request,  response);
		}
		catch(SQLException asdf)
		{
			System.out.println(asdf);
		}
	}
	protected static void processError(String message, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
		request.setAttribute("errorState", "true");
		request.setAttribute("errorMessage", message);
		dispatcher.forward(request, response);
	
	}
	
}


