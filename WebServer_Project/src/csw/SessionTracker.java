package csw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

public class SessionTracker {
	public static Boolean checkSession(HttpSession session) {
		Boolean returnValue = false;
		String username = (String)session.getAttribute("username");
		String password = (String)session.getAttribute("password");
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch(Exception e) {}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			st = con.prepareStatement("SELECT id FROM users WHERE uname = ? AND passwd = BINARY ?");
			
			st.setString(1, username);
			st.setString(2, password);
			
			rs = st.executeQuery();
			if (rs.next()) {
				returnValue = true;
			}
		}
		catch(SQLException exception) {}
		finally {
			try {rs.close();} catch (SQLException e) {};
			try {st.close();} catch (SQLException e) {};
			try {con.close();} catch (SQLException e) {};
		}
		
		return returnValue;
	}
	
	public static int getId(HttpSession session) {
		int returnValue = 0;
		String username = (String)session.getAttribute("username");
		String password = (String)session.getAttribute("password");
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch(Exception e) {}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
			st = con.prepareStatement("SELECT id FROM users WHERE uname = ? AND passwd = BINARY ?");
			
			st.setString(1, username);
			st.setString(2, password);
			
			rs = st.executeQuery();
			if (rs.next()) {
				returnValue =  rs.getInt(1);
			}
		}
		catch(SQLException exception) {}
		finally {
			try {rs.close();} catch (SQLException e) {};
			try {st.close();} catch (SQLException e) {};
			try {con.close();} catch (SQLException e) {};
		}
		
		return returnValue;
	}
}
