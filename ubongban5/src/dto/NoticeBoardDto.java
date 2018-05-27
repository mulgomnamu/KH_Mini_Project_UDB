package dto;


/*
CREATE TABLE NOTICEBOARD(
   SEQ NUMBER(8) PRIMARY KEY,
   NAME VARCHAR2(200) NOT NULL,
   TITLE VARCHAR2(200) NOT NULL,
   CONTENT VARCHAR2(800) NOT NULL,
   WDATE DATE NOT NULL
   
);

CREATE SEQUENCE SEQ_NOTICEBOARD
START WITH 1 INCREMENT BY 1;

INSERT INTO NOTICEBOARD(SEQ, NAME, TITLE, CONTENT, WDATE) 
VALUES(SEQ_NOTICEBOARD.NEXTVAL, 'admin', '원전 반대!', '반대반대~~', SYSDATE);
*/
public class NoticeBoardDto {

	private int seq;
	private String name;
	private String title;
	private String content;
	private String wdate;
	
	public NoticeBoardDto() {
		// TODO Auto-generated constructor stub
	}
	
	public NoticeBoardDto(int seq, String name, String title, String content, String wdate) {
		super();
		this.seq = seq;
		this.name = name;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	@Override
	public String toString() {
		return "NoticeBoardDto [seq=" + seq + ", name=" + name + ", title=" + title + ", content=" + content
				+ ", wdate=" + wdate + "]";
	}
	
	
	
}
