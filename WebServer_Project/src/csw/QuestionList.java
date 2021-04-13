package csw;

import java.sql.*;
import java.util.ArrayList;
import csw.QuestionPreview;

public class QuestionList {
	public static ArrayList<QuestionPreview> getQuestionsFromDatabase() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3000/webserverdatabase", "root", "password");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT questions.title, users.uname, questions.author, questions.id FROM questions, users WHERE users.id = questions.author;");
		
		ArrayList<QuestionPreview> list = new ArrayList<QuestionPreview>();
		
		while (rs.next()) {
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM answers WHERE question = ?;");
			ps.setInt(1, rs.getInt("id"));
			ResultSet rs2 = ps.executeQuery();
			rs2.next();
			
			list.add(new QuestionPreview(rs.getString("uname"), rs.getString("title"), rs2.getInt(1), rs.getInt("id"), rs.getInt("author")));
		}
		
		return list;
	}
}
