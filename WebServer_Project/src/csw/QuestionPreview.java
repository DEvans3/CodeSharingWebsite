package csw;

public class QuestionPreview {
	public String author;
	public String title;
	public int answerCount;
	public int id;
	public int authorId;
	
	QuestionPreview(String author, String title, int answerCount, int id, int authorId) {
		this.author = author;
		this.title = title;
		this.answerCount = answerCount;
		this.id = id;
		this.authorId = authorId;
	}
}
