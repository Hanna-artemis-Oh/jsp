package model2.mvcboard;

public class MVCBoardDTO {
	
	private String idx;
	private String name;
	private String title;
	private String content;
	private java.sql.Date postdate;
	private String ofile;
	private String sfile;
	private int downcount;
	private String pass;
	private int visitcount;
	
	
	public String getIdx() {
		return idx;
	}
	public String getName() {
		return name;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public String getOfile() {
		return ofile;
	}
	public String getSfile() {
		return sfile;
	}
	public int getDowncount() {
		return downcount;
	}
	public String getPass() {
		return pass;
	}
	public int getVisitcount() {
		return visitcount;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	public void setOfile(String ofile) {
		this.ofile = ofile;
	}
	public void setSfile(String sfile) {
		this.sfile = sfile;
	}
	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}

}
