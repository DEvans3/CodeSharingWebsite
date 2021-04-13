package csw;

public class Answer {
	public String body;
	public String code;
	public String author;
	public int rating;
	public int id;
	public int authorId;
	
	public Answer(String body, String code, String author, int rating, int id, int authorId) {
		this.body = body;
		this.code = code;
		this.author = author;
		this.rating = rating;
		this.id = id;
		this.authorId = authorId;
	}
}
