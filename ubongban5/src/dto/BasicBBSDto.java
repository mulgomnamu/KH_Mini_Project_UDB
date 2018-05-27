package dto;

/*
	
	DROP TABLE BASICBBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_BASICBBS;

CREATE TABLE BASICBBS(
   SEQ NUMBER(8) PRIMARY KEY,
   TITLE VARCHAR2(200) NOT NULL,
   CONTENT VARCHAR2(4000) NOT NULL,
   NAME VARCHAR2(50) NOT NULL,
   WDATE DATE NOT NULL,
   READCOUNT NUMBER(8) NOT NULL,
   DEL NUMBER(1) NOT NULL,
   FAMILY NUMBER(8) NOT NULL,
   PARENT NUMBER(8) NOT NULL,
   DEPTH NUMBER(8) NOT NULL,
   INDENT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_BASICBBS
START WITH 1 INCREMENT BY 1;

SELECT SEQ, ID, TITLE, CONTENT, WDATE, DEL, READCOUNT FROM BASICBBS;

*/

public class BasicBBSDto {
	
	private int seq;				// 글 번호(고유 키)
	private String title;			// 글 제목
	private String content;			// 댓글 내용
	private String name;			// 댓글 작성자
	private String wdate;			// 작성 날짜 및 시간
	private int readcount;			// 조회수
	private int del;				// 삭제 여부						삭제o(1)/삭제x(0)
	private int family;				// 그룹 번호						일반 글 : seq/댓글의 경우 : 부모글의 family
	private int parent;				// 부모 seq						일반 글 : 0/댓글의 경우 : 부모글의 seq
	private int depth;				// 그룹내 최상위 글로부터 매겨지는 순서	일반 글 : 0/댓글의 경우 : (부모글의 depth) + 1
	private int indent;				// 들여쓰기 수준					일반 글 : 0/댓글의 경우 : (부모글의 indent) + 1
	
	public BasicBBSDto(String title, String content, String name, int readcount, int del) {
		super();
		this.title = title;
		this.content = content;
		this.name = name;
		this.readcount = readcount;
		this.del = del;
	}
	
	public BasicBBSDto(int seq, String title, String content, String name, String wdate, int readcount) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.name = name;
		this.wdate = wdate;
		this.readcount = readcount;
	}
	
	public BasicBBSDto(int seq, String title, String content, String name, String wdate, int readcount, int del,
			int family, int parent, int depth, int indent) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.name = name;
		this.wdate = wdate;
		this.readcount = readcount;
		this.del = del;
		this.family = family;
		this.parent = parent;
		this.depth = depth;
		this.indent = indent;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getFamily() {
		return family;
	}

	public void setFamily(int family) {
		this.family = family;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	@Override
	public String toString() {
		return "BasicBBSDto [seq=" + seq + ", title=" + title + ", content=" + content + ", name=" + name + ", wdate="
				+ wdate + ", readcount=" + readcount + ", del=" + del + ", family=" + family + ", parent=" + parent
				+ ", depth=" + depth + ", indent=" + indent + "]";
	}
	
	
	
}
