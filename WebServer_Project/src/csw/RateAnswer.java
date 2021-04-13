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
 * Servlet implementation class RateAnswer
 */
@WebServlet("/RateAnswer")
public class RateAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected enum Rating {
		NONE,
		UP,
		DOWN
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RateAnswer() {
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
		PreparedStatement st2 = null;
		RequestDispatcher dispatcher = request.getRequestDispatcher("questionView.jsp?qid=".concat(request.getParameter("qid")));
		
		if (!SessionTracker.checkSession(request.getSession())) {
			dispatcher.forward(request, response);
			return;
		}
		
		int userId = SessionTracker.getId(request.getSession());
		int answerId = Integer.parseInt((String)request.getParameter("aid"));
		Rating rating = request.getParameter("rating").contentEquals("Like") ? Rating.UP : Rating.DOWN;
		
		Rating prevRating = checkAlreadyRated(userId, answerId);
		
		int increment = (rating == Rating.UP) ? 1 : -1;
		if (prevRating == rating) {
			dispatcher.forward(request, response);
			return;
		}
		else if (prevRating != Rating.NONE) {
			increment *= 2;
		}
		
		try {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			}
			catch(Exception asdf) {
				System.out.println(asdf);
			}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			if (prevRating == Rating.NONE) {
				st = con.prepareStatement("INSERT INTO rates (rater, answer, up) VALUES (?, ?, ?);");
				st.setInt(1, userId);
				st.setInt(2, answerId);
				st.setBoolean(3, (rating == Rating.UP));
			}
			else {
				st = con.prepareStatement("UPDATE rates SET up = ? WHERE rater = ? AND answer = ?;");
				st.setBoolean(1, (rating == Rating.UP));
				st.setInt(2, userId);
				st.setInt(3, answerId);
			}
				
			st.executeUpdate();
			
			st2 = con.prepareStatement("UPDATE answers SET rating = rating + ? WHERE id = ?;");
			
			st2.setInt(1, increment);
			st2.setInt(2, answerId);
			
			st2.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {st.close();} catch (Exception e) {};
			try {st2.close();} catch (Exception e) {};
			try {con.close();} catch (Exception e) {};
		}
		
		dispatcher.forward(request, response);
		return;
	}
	
	
	
	protected static Rating checkAlreadyRated(int userId, int answerId) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		Rating returnedValue = Rating.NONE;
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			}
			catch(Exception e) {}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			st = con.prepareStatement("SELECT up FROM rates WHERE rater = ? AND answer = ?");
			
			st.setInt(1, userId);
			st.setInt(2, answerId);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				returnedValue = rs.getBoolean(1) ? Rating.UP : Rating.DOWN;
			}
		}
		catch(SQLException e) {
			
		}
		finally {
			try {rs.close();} catch (Exception e) {};
			try {st.close();} catch (Exception e) {};
			try {con.close();} catch (Exception e) {};
		}
		
		return returnedValue;
	}
}
