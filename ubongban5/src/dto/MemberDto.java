package dto;

import java.io.Serializable;

/*
DROP TABLE MEMBER
CASCADE CONSTRAINTS;

CREATE TABLE MEMBER(
   ID VARCHAR2(50) PRIMARY KEY,
   PWD VARCHAR2(50) NOT NULL,
   DONG VARCHAR2(50) NOT NULL,
   HO VARCHAR2(50) NOT NULL,
   NAME VARCHAR2(50) NOT NULL,
   AUTH NUMBER(1) NOT NULL
	
	INSERT INTO MEMBER(ID, PWD, NAME, DONG, HO, AUTH) VALUES('admin', 'admin', 'admin', '-', '-', 1); // 관리자
);
*/

public class MemberDto implements Serializable {
	   private String id;
	   private String pwd;
	   private String name;
	   private String dong;
	   private String ho;
	   private int auth;   // 사용자(3), 관리자(1)
	   
	   public MemberDto() {}

	   public MemberDto(String name) {
		   this.name = name;
	   }
	   
	   public MemberDto(String id, String pwd, String name, String dong, String ho, int auth) {
	      super();
	      this.id = id;
	      this.pwd = pwd;
	      this.name = name;
	      this.dong = dong;
	      this.ho = ho;
	      this.auth = auth;
	   }
	   
	   public MemberDto(String id, String pwd, String name, String dong, String ho) {
	      super();
	      this.id = id;
	      this.pwd = pwd;
	      this.name = name;
	      this.dong = dong;
	      this.ho = ho;
	      this.auth = auth;
	   }
	   
	   public MemberDto(String id, String pwd, String dong, String ho) {
	      super();
	      this.id = id;
	      this.pwd = pwd;
	      this.name = name;
	      this.dong = dong;
	      this.ho = ho;
	      this.auth = auth;
	   }

	   public String getId() {
	      return id;
	   }

	   public void setId(String id) {
	      this.id = id;
	   }

	   public String getPwd() {
	      return pwd;
	   }

	   public void setPwd(String pwd) {
	      this.pwd = pwd;
	   }

	   public String getName() {
	      return name;
	   }

	   public void setName(String name) {
	      this.name = name;
	   }

	   public String getDong() {
	      return dong;
	   }

	   public void setDong(String dong) {
	      this.dong = dong;
	   }

	   public String getHo() {
	      return ho;
	   }

	   public void setHo(String ho) {
	      this.ho = ho;
	   }

	   public int getAuth() {
	      return auth;
	   }

	   public void setAuth(int auth) {
	      this.auth = auth;
	   }

	   @Override
	   public String toString() {
	      return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", dong=" + dong + ", ho=" + ho + ", auth="
	            + auth + "]";
	   }
	   
	   
	}

/*
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


CREATE TABLE MARKETBBS(
	SEQ NUMBER(8) PRIMARY KEY,
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	WDATE DATE NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,
	SOLD NUMBER(1) NOT NULL,
	DEL NUMBER(1) NOT NULL,
	IMAGESRC VARCHAR2(500) NOT NULL,
    CATEGORY VARCHAR2(200) NOT NULL,
    PRICE NUMBER(10) NOT NULL
);

CREATE SEQUENCE SEQ_MARKETBBS
START WITH 1 INCREMENT BY 1;


CREATE TABLE MARKETREPLY(
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

CREATE SEQUENCE SEQ_MARKETREPLY
START WITH 1 INCREMENT BY 1;


CREATE TABLE MEMBER(
   ID VARCHAR2(50) PRIMARY KEY, 
   PWD VARCHAR2(50) NOT NULL,
   DONG VARCHAR2(50) NOT NULL,
   HO VARCHAR2(50) NOT NULL,
   NAME VARCHAR2(50) NOT NULL,
   AUTH NUMBER(1) NOT NULL
);


CREATE TABLE NOTICEBOARD(
   SEQ NUMBER(8) PRIMARY KEY,
   NAME VARCHAR2(200) NOT NULL,
   TITLE VARCHAR2(200) NOT NULL,
   CONTENT VARCHAR2(800) NOT NULL,
   WDATE DATE NOT NULL
   
);

CREATE SEQUENCE SEQ_NOTICEBOARD
START WITH 1 INCREMENT BY 1;


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


CREATE TABLE VOTEBOARD (
   SEQ NUMBER(38) PRIMARY KEY,
   VOTECONTENT VARCHAR2(4000) NOT NULL,
   VOTESTART VARCHAR2(100) NOT NULL,
   VOTEEND VARCHAR2(100) NOT NULL,
   VOTECOUNT NUMBER(38) NOT NULL,
   AGREEMENT NUMBER(38) NOT NULL,
   DISAGREEMENT NUMBER(38) NOT NULL,
   ABSTAIN NUMBER(38) NOT NULL,
   VOTEMEMBERLIST VARCHAR2(1000) NOT NULL,
   DEL NUMBER(38) NOT NULL
);

CREATE TABLE VOTERESULTBOARD (
   VOTECONTENT VARCHAR2(4000) NOT NULL,
   VOTECOUNT NUMBER(38) NOT NULL,
   AGREEMENT NUMBER(38) NOT NULL,
   DISAGREEMENT NUMBER(38) NOT NULL,
   ABSTAIN NUMBER(38) NOT NULL
);

CREATE TABLE BOOKINGBOARD (
   BOOKDATE VARCHAR2(50) NOT NULL,
   BOOKTIME VARCHAR2(50) NOT NULL,
   BOOKPLACE VARCHAR2(50) NOT NULL,
   NAME VARCHAR2(50) NOT NULL
);

CREATE SEQUENCE SEQ_VOTEBOARD
START WITH 1 INCREMENT BY 1;


INSERT INTO NOTICEBOARD(SEQ, NAME, TITLE, CONTENT, WDATE) 
VALUES(SEQ_NOTICEBOARD.NEXTVAL, 'admin', '다과회를 추진할까 합니다.', '투표를 통해 찬성/반대 의견을 알려주세요!', SYSDATE);

INSERT INTO MEMBER(ID, PWD, NAME, DONG, HO, AUTH) VALUES('p', 'p', '경비원', '-', '-', 2);
INSERT INTO MEMBER(ID, PWD, NAME, DONG, HO, AUTH) VALUES('o', 'o', '부녀회장', '-', '-', 2);


*/

/*
DROP TABLE MARKETREPLY
CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_MARKETREPLY;

DROP TABLE MEMBER
CASCADE CONSTRAINTS;

DROP TABLE NOTICEBOARD
CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_NOTICEBOARD;

DROP TABLE REPLY
CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_REPLY;

DROP TABLE MARKETREPLY
CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_MARKETREPLY;

DROP TABLE VOTERESULTBOARD
CASCADE CONSTRAINTS;
DROP TABLE VOTEBOARD
CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_VOTEBOARD
CASCADE CONSTRAINTS;

DROP TABLE BOOKINGBOARD
CASCADE CONSTRAINTS;

DROP TABLE BBSREPLIER
CASCADE CONSTRAINTS;

DROP TABLE MARKETBBS
CASCADE CONSTRAINTS;

DROP TABLE MEMBER_
CASCADE CONSTRAINTS;

DROP TABLE BASICBBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_BASICBBS;

DROP SEQUENCE SEQ_BBS;

DROP SEQUENCE SEQ_BBSREPLIER;

DROP SEQUENCE SEQ_MARKETBBS;

DROP SEQUENCE SEQ_VOTEBOARD;

select * from VOTEBOARD
*/


