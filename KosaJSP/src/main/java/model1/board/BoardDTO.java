package model1.board;

public class BoardDTO {
	
	private String num;
	private String title;
	private String content;
	private String id;
	private java.sql.Date postdate;
	private String visitcount;
	private String name;
	
	
	public String getNum() {
		return num;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getId() {
		return id;
	}
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public String getVisitcount() {
		return visitcount;
	}
	public String getName() {
		return name;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	public void setVisitcount(String visitcount) {
		this.visitcount = visitcount;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
