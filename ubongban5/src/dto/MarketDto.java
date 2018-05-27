package dto;

/*

DROP TABLE MARKETBBS
CASCADE CONSTRAINTS;

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

*/
public class MarketDto {	// 중고 장터 게시판   
	private int seq;               // sequence
	private String title;            // 글 제목
	private String contents;         // 글 내용
	private String name;            // 글쓴이
	private String wdate;            // 작성날짜
	private int readcount;            // 조회수
	private int sold;               // 판매여부      판매o(1)/판매x(0)
	private int del;               // 삭제여부      삭제o(1)/삭제x(0)
	private String imagesrc;			// 이미지
	private String category;
	private int price;
	
	public MarketDto() {}

	public MarketDto(int seq, String title, String contents, String name, String wdate, int readcount, int sold,
			int del, String imagesrc, String category, int price) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.name = name;
		this.wdate = wdate;
		this.readcount = readcount;
		this.sold = sold;
		this.del = del;
		this.imagesrc = imagesrc;
		this.category = category;
		this.price = price;
	}
	
	public MarketDto(int seq, String title, String contents, String name, String wdate, int readcount, int sold,
			 String imagesrc, String category, int price) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.name = name;
		this.wdate = wdate;
		this.readcount = readcount;
		this.sold = sold;
		this.imagesrc = imagesrc;
		this.category = category;
		this.price = price;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getImagesrc() {
		return imagesrc;
	}

	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MarketDto [seq=" + seq + ", title=" + title + ", contents=" + contents + ", name=" + name + ", wdate="
				+ wdate + ", readcount=" + readcount + ", sold=" + sold + ", del=" + del + ", imagesrc=" + imagesrc
				+ ", category=" + category + ", price=" + price + "]";
	}
	
	
	
}
