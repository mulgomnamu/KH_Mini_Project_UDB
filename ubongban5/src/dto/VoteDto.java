package dto;

/*
 * DROP TABLE VOTERESULTBOARD
CASCADE CONSTRAINTS;

DROP TABLE VOTEBOARD
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_VOTEBOARD
CASCADE CONSTRAINTS;

CREATE TABLE VOTEBOARD (
   SEQ NUMBER(38) PRIMARY KEY,
   VOTECONTENT VARCHAR2(500) NOT NULL,
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
   VOTECONTENT VARCHAR2(500) NOT NULL,
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

 */

public class VoteDto {
	private String name;
	private int seq;
	private String votecontent;
	private String votestart;
	private String voteend;
	private int agreement;
	private int disagreement;
	private int abstain;
	private int del;
	private int votecount;
	public VoteDto() {}
	
	public VoteDto(String votecontent,int votecount, int agreement,
			int disagreement, int abstain) {
		super();
		
		this.votecontent = votecontent;
		this.votecount = votecount;
		this.agreement = agreement;
		this.disagreement = disagreement;
		this.abstain = abstain;
	}
	
	public VoteDto(int seq, String votecontent, String votestart, String voteend,int votecount, int agreement,
			int disagreement, int abstain) {
		super();
		this.seq = seq;
		this.votecontent = votecontent;
		this.votestart = votestart;
		this.voteend = voteend;
		this.votecount = votecount;
		this.agreement = agreement;
		this.disagreement = disagreement;
		this.abstain = abstain;
	}
	
	
	public VoteDto(int seq) {
		this.seq = seq;
	}
	
	public VoteDto(String voteend, int seq) {
		this.voteend = voteend;
		this.seq = seq;
	}
	
	public VoteDto(String votecontent, int agreement, int disagreement, int abstain) {
		this.votecontent = votecontent;
		this.agreement = agreement;
		this.disagreement = disagreement;
		this.abstain = abstain;
	}
	
	public VoteDto(String name, int seq, String votecontent, String votestart, String voteend, int agreement,
			int disagreement, int abstain, int del) {
		super();
		this.name = name;
		this.seq = seq;
		this.votecontent = votecontent;
		this.votestart = votestart;
		this.voteend = voteend;
		this.agreement = agreement;
		this.disagreement = disagreement;
		this.abstain = abstain;
		this.del = del;
	}


	public int getVotecount() {
		return votecount;
	}

	public void setVotecount(int votecount) {
		this.votecount = votecount;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getVotecontent() {
		return votecontent;
	}

	public void setVotecontent(String votecontent) {
		this.votecontent = votecontent;
	}

	public String getVotestart() {
		return votestart;
	}

	public void setVotestart(String votestart) {
		this.votestart = votestart;
	}

	public String getVoteend() {
		return voteend;
	}

	public void setVoteend(String voteend) {
		this.voteend = voteend;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAgreement() {
		return agreement;
	}

	public void setAgreement(int agreement) {
		this.agreement = agreement;
	}

	public int getDisagreement() {
		return disagreement;
	}

	public void setDisagreement(int disagreement) {
		this.disagreement = disagreement;
	}

	public int getAbstain() {
		return abstain;
	}

	public void setAbstain(int abstain) {
		this.abstain = abstain;
	}

	@Override
	public String toString() {
		return "VoteDto [name=" + name + ", agreement=" + agreement + ", disagreement=" + disagreement + ", abstain="
				+ abstain + "]";
	}
	
	
	
	

	
}