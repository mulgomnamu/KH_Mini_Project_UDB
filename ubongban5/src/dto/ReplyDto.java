package dto;

/*
DROP TABLE REPLY
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_REPLY;

CREATE TABLE REPLY(
   SEQ NUMBER(8) PRIMARY KEY,
   NAME VARCHAR2(200) NOT NULL,
   CONTENT VARCHAR2(4000) NOT NULL,
   WDATE DATE NOT NULL,
   DEL NUMBER(1) NOT NULL,
   BBSSEQ NUMBER(8) NOT NULL,
   FAMILY NUMBER(8) NOT NULL,
   PARENT NUMBER(8) NOT NULL,
   DEPTH NUMBER(8) NOT NULL,
   INDENT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_REPLY
START WITH 1 INCREMENT BY 1;

SELECT SEQ, NAME, CONTENT, WDATE, DEL, BBSSEQ FROM REPLY;
*/

public class ReplyDto {
	
	private int seq;			// 글 번호
	private String name;		// 작성자
	private String content;		// 내용
	private String wdate;		// 작성 시간
	private int del;			// 삭제 여부			삭제o(1)/삭제x(0)
	private int bbsseq;			// 댓글의 게시판 구분
	private int family;			// 그룹 번호						일반 글 : seq/댓글의 경우 : 부모글의 family
	private int parent;			// 부모 seq						일반 글 : 0/댓글의 경우 : 부모글의 seq
	private int depth;			// 그룹내 최상위 글로부터 매겨지는 순서	일반 글 : 0/댓글의 경우 : (부모글의 depth) + 1
	private int indent;			// 들여쓰기 수준					일반 글 : 0/댓글의 경우 : (부모글의 indent) + 1
	
	public ReplyDto(int seq, String name, String content, String wdate, int del, int bbsseq) {
		super();
		this.seq = seq;
		this.name = name;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.bbsseq = bbsseq;
	}
	
	public ReplyDto(int seq, String name, String content, String wdate, int del, int bbsseq, int family, int parent,
			int depth, int indent) {
		super();
		this.seq = seq;
		this.name = name;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.bbsseq = bbsseq;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getBbsseq() {
		return bbsseq;
	}

	public void setBbsseq(int bbsseq) {
		this.bbsseq = bbsseq;
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
		return "ReplyDto [seq=" + seq + ", name=" + name + ", content=" + content + ", wdate=" + wdate + ", del=" + del
				+ ", bbsseq=" + bbsseq + ", family=" + family + ", parent=" + parent + ", depth=" + depth + ", indent="
				+ indent + "]";
	}
	
	

}
